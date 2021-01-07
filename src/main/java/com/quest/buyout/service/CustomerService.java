package com.quest.buyout.service;

import com.quest.buyout.dao.*;
import com.quest.buyout.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

@Service
public class CustomerService {


    private String customerCreateMsg = "";
    private String customerId = "";

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    AddAddressRepository addAddressRepository;



    public String setCustomerImage(MultipartFile file, String customerId)
            throws IllegalStateException, IOException {

        String str = "";

        String fileContentType = file.getContentType();
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(fileContentType == null){ return null; }

        if(customer != null && fileContentType.contains("image")){
            String fileName = file.getOriginalFilename();
            file.transferTo(new File("/opt/tomcat/webapps/ROOT/quest/users/" + fileName));
            str = "https://quest.asabre.com/quest/users/" + fileName;
            customer.setUserImage(str);
            customerRepository.save(customer);
        }
        return str;
    }

    public List<Customer> index(){
        return customerRepository.findAll();

    }

    public Optional<Customer> findCustomerById(HashMap<String, String> body){
        String _id = body.get("_id");
        return customerRepository.findById(_id);
    }

    public ArrayList<Customer> findCustomersByIds(List<HashMap<String, String>> body){
        ArrayList<Customer> customerArrayList = new ArrayList<>();

        for(HashMap<String, String> newBody : body){
            Customer customer = customerRepository.findById(newBody.get("_id")).orElse(null);
            customerArrayList.add(customer);
        }
        return customerArrayList;
    }

    public Customer findCustomerByPhoneAndPwd(HashMap<String, String> body){
        String pwd = body.get("password");
        String phone = body.get("phoneNumber");
        return customerRepository.findAll().stream()
                .filter(customer -> customer.getPassword().equals(pwd) &&
                        customer.getPhoneNumber().equals(phone)).findFirst().orElse(new Customer());
    }

    public Customer findCustomerByPassnm(HashMap<String, String> body){
        String lastName = body.get("lastName");
        String phone = body.get("phoneNumber");
        return customerRepository.findAll().stream()
                .filter(customer -> customer.getLastName().equals(lastName) && // fit this to getName
                        customer.getPhoneNumber().equals(phone)).findFirst().orElse(null);
    }

    public Stream<Customer> findCustomersByRating(List<HashMap<String, String>> newBody){
        HashMap<String, String> body = newBody.get(0);
        int rating = Integer.parseInt(body.get("rating"));
        return customerRepository.findAll().stream()
                .filter(customer -> customer.getRating() >= rating);
    }

    public String countCustomers(){
        return String.valueOf(customerRepository.count());
    }


    public OutLaw createCustomer(HashMap<String, String> body){
        if(checkCustomerCreating(body)){
            mkCustomer(body);
        }
        return new OutLaw(customerCreateMsg, customerId);
    }

    public String[] createCustomers(List<HashMap<String, String>> body){
        for(HashMap<String, String> newBody : body){
            if(checkCustomerCreating(newBody)){
                mkCustomer(newBody);
            }
        }
        return new String[] {"Multiple customers created"};
    }

    public OutLaw UpdateCustomer(HashMap<String, String> body){
        return updateCustomer(body);
    }

    public Customer updateCustomerById(HashMap<String, String> body){
        return customerUpdater(body);
    }

    public String[] updateCustomersByIds(List<HashMap<String, String>> body){
        for(HashMap<String, String> newBody : body){
            customerUpdater(newBody);
        }
        return new String[]{"Multiple customer records updated"};
    }

    public OutLaw resetPassword(HashMap<String, String> body){
        String oldPassword = body.get("oldPassword");
        String newPassword = body.get("newPassword");

        // get customer by oldPassword
        Customer customer = customerRepository
                .findAll()
                .stream()
                .filter(customer1 -> customer1.getPassword().equals(oldPassword))
                .findFirst()
                .orElse(null);
        if(null != customer){
            customer.setPassword(newPassword != null && !newPassword.isEmpty() ?
                    newPassword : customer.getPassword());
            customerRepository.save(customer);
            return new OutLaw("Password reset successful");
        }
        return new OutLaw("Password reset failed");
    }


    public OutLaw deleteCustomer(HashMap<String, String> body){
        String _id = body.get("_id");
        customerRepository.deleteById(_id);
        clearCustomerRecords(_id);
        return new OutLaw("Customer record deleted");
    }



    public String[] deleteCustomers(List<HashMap<String, String>> body){
        for(HashMap<String, String> newBody : body){
            String _id = newBody.get("_id");
            customerRepository.deleteById(_id);
            clearCustomerRecords(_id);
        }
        return new String[]{"Multiple customer records deleted"};
    }

    public String deleteAll(){
        customerRepository.deleteAll();
        return "All customer records deleted";
    }


    private boolean checkCustomerCreating(HashMap<String, String> body){

        String firstName = body.get("firstName");
        String lastName = body.get("lastName");
        String phoneNumber = body.get("phoneNumber");
        String password = body.get("password");

        // check if user phone number exists
        Customer checkCustomer = customerRepository
                .findAll()
                .stream()
                .filter(customer -> customer.getPhoneNumber().equals(phoneNumber))
                .findFirst()
                .orElse(null);

        if(checkCustomer != null){
            customerCreateMsg = "Phone number already exists!";
            return false;
        }

        if(firstName != null && !firstName.isEmpty() &&
                lastName != null && !lastName.isEmpty() &&
                phoneNumber != null && !phoneNumber.isEmpty() &&
                password != null && !password.isEmpty()){
            return true;
        }
        customerCreateMsg = "Field can't be empty";
        return false;
    }

    private OutLaw updateCustomer(HashMap<String, String> body){
        OutLaw outLaw = new OutLaw();
        String password = body.get("password");

        // find customer by password
        Customer customer = customerRepository
                .findAll()
                .stream()
                .filter(customer1 -> customer1.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        // if customer exists
        if(customer != null){
            // create new object of the incoming properties
            Customer newCustomer = mkObjectCus(body);

            // check if new phone number is different from old
            if(!(customer.getPhoneNumber().equals(newCustomer.getPhoneNumber()))){

                // check if new phone number already exists
                Customer checkPhone = customerRepository
                        .findAll()
                        .stream()
                        .filter(customer2 -> customer2.getPhoneNumber().equals(newCustomer.getPhoneNumber()))
                        .findFirst()
                        .orElse(null);

                // if customer with phoneNumber exists
                if(checkPhone != null){
                    customerCreateMsg = "New number already exists";
                    outLaw.setMsg(customerCreateMsg);
                    return outLaw;
                }
            }
            // save changes
             customerRepository.save(compareCustomer(customer, newCustomer));
             customerCreateMsg = "Profile updated";
             outLaw.setMsg(customerCreateMsg);
             return outLaw;
        } else {
            customerCreateMsg = "Incorrect password";
            outLaw.setMsg(customerCreateMsg);
        }
        return outLaw;
    }



    private Customer customerUpdater(HashMap<String, String> body){

        // use the _id to get the old object
        Customer oldCustomer = customerRepository.findById(body.get("_id")).orElse(null);
        Customer updatedOldCustomer = new Customer();
        if(oldCustomer != null){
            // create a new object of the incoming properties
            Customer newCustomer = mkObjectCus(body);
            // compareCustomer old to new and update oldObject accordingly
            updatedOldCustomer = compareCustomer(oldCustomer, newCustomer);
            // save new customer
        }
        return customerRepository.save(updatedOldCustomer);
    }

    private void mkCustomer(HashMap<String, String> body){
        Customer customer = mkObjectCus(body);
        customerId = customerRepository.save(customer).get_id();
        customerCreateMsg = "User account created successfully";
        // in the client side store the user data in sql database if the http response equals
        // "User account created successfully"
    }


    private Customer mkObjectCus(HashMap<String, String> body){
        Customer customer = new Customer(
                body.get("firstName"),
                body.get("lastName"),
                body.get("phoneNumber"),
                body.get("password")

        );
        String email = body.get("email");
        customer.setDateAccountWasCreated(timeConverter());
        customer.setEmail(email);
        return customer;
    }


    private Customer compareCustomer(Customer oldCustomer, Customer newCustomer){

        oldCustomer.setFirstName((newCustomer.getFirstName() != null && !newCustomer.getFirstName().isEmpty()) ?
                newCustomer.getFirstName() : oldCustomer.getFirstName());

        oldCustomer.setLastName((newCustomer.getLastName() != null && !newCustomer.getLastName().isEmpty()) ?
                newCustomer.getLastName() : oldCustomer.getLastName());

        oldCustomer.setPhoneNumber((newCustomer.getPhoneNumber() != null && !newCustomer.getPhoneNumber().isEmpty()) ?
                newCustomer.getPhoneNumber() : oldCustomer.getPhoneNumber());

        oldCustomer.setEmail((newCustomer.getEmail() != null && !newCustomer.getEmail().isEmpty()) ?
                newCustomer.getEmail() : oldCustomer.getEmail());

//        oldCustomer.setUserImage(newCustomer.getUserImage() != null && !newCustomer.getFirstName().isEmpty() ?
//                newCustomer.getUserImage() : oldCustomer.getUserImage());

//        oldCustomer.setTrackUserAddress((newCustomer.getTrackUserAddress() != null && !newCustomer.getTrackUserAddress().isEmpty()) ?
//                newCustomer.getTrackUserAddress() : oldCustomer.getTrackUserAddress() );

        return oldCustomer;
    }


    private String timeConverter(){
        Date date = new Date();
        long millis = date.getTime();

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);

        return String.format("%s-%s-%s", day, month, year);
    }


    private void clearCustomerRecords(String customerId){
        // delete all his wishList
        for(int j=0;j<wishListRepository.count();j++){
            WishList wishList = wishListRepository.findAll().get(j);
            if(wishList.getCustomerId().equals(customerId)){
                wishListRepository.delete(wishList);
            }
        }

        // delete all his cart
        for(int j=0;j<cartRepository.count();j++){
            Cart cart = cartRepository.findAll().get(j);
            if(cart.getCustomerId().equals(customerId)){
                cartRepository.delete(cart);
            }
        }

        // delete all his orders
        for(int z=0;z<orderRepository.count();z++){
            Order order = orderRepository.findAll().get(z);
            if(order.getCustomerId().equals(customerId)){
                orderRepository.delete(order);
            }
        }

        // delete his addresses of customer
        for(int j=0;j<addAddressRepository.count();j++){
            AddAddress addAddress = addAddressRepository.findAll().get(j);
            if(addAddress.getCustomerId().equals(customerId)){
                addAddressRepository.delete(addAddress);
            }
        }
    }




}
