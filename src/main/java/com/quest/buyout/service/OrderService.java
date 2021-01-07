package com.quest.buyout.service;

import com.quest.buyout.dao.*;
import com.quest.buyout.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Service
public class OrderService {


    @Autowired
    OrderRepository orderRepository;

    // update product when an order is created or destroyed
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    AddAddressRepository addAddressRepository;

    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    private String orderMsg = "";
    private String extraMsg = "Our Agents will call you shortly to confirm this order." +
            " If you do not receive a call in 10 minutes time, " +
            "kindly call them on 0544 290 691 / 0201 848 650";


    public List<Order> index(){ return orderRepository.findAll(); }


    public Order findOrderById(HashMap<String, String> body){
        String orderId = body.get("_id");
        return orderRepository.findById(orderId).orElse(null);
    }

    // for admin perhaps
    public Order findByLastSixLetters(HashMap<String, String> body){
        String lastSixLetters = body.get("six");
       return orderRepository.findAll().stream()
                .filter(order -> order.get_id().contains(lastSixLetters))
                .findFirst().orElse(null);
    }


    public List<Orde> customerOrdes(List<HashMap<String, String>> body){
        HashMap<String, String> newBody = body.get(0);
        String customerId = newBody.get("customerId");
        ArrayList<Orde> list = new ArrayList<>();

        // interested in pending orders
        for(int j=0; j<orderRepository.count(); j++){
            Order order = orderRepository.findAll().get(j);
            if(order.getStatus().equals("pending") && order.getCustomerId().equals(customerId)){

                Product product = productRepository.findById(order.getProductId()).orElse(null);
                Customer customer = customerRepository.findById(order.getCustomerId()).orElse(null);
                AddAddress addAddress = addAddressRepository.findById(order.getAddressId()).orElse(null);
                ProductShow productShow = new ProductShow();
                CustomerShow customerShow = new CustomerShow();

                if(product != null && customer != null && addAddress != null){
                    productShow.setProductId(product.get_id());
                    productShow.setName(product.getName());
                    productShow.setPrice(product.getPrice());
                    productShow.setStock(product.getStock());
                    productShow.setCategory(product.getCategory());
                    productShow.setDiscount(product.getDiscount());
                    productShow.setStatus(product.getStatus());
                    productShow.setImages(product.getImages());

                    customerShow.setCustomerId(customer.get_id());
                    customerShow.setFirstName(customer.getFirstName());
                    customerShow.setLastName(customer.getLastName());
                    customerShow.setPhoneNumber(customer.getPhoneNumber());
                    customerShow.setRating(customer.getRating());

                    list.add(new Orde(customerShow, order, addAddress, productShow));
                }
            }
        }
        if(list.size() > 10){
            return list.subList(0, 10);
        }
        return list;
    }


    public List<Orde> ordes(){
        ArrayList<Orde> list = new ArrayList<>();

        // interested in pending orders
        for(int j=0; j<orderRepository.count(); j++){
            Order order = orderRepository.findAll().get(j);
            if(order.getStatus().equals("pending")){

                Product product = productRepository.findById(order.getProductId()).orElse(null);
                Customer customer = customerRepository.findById(order.getCustomerId()).orElse(null);
                AddAddress addAddress = addAddressRepository.findById(order.getAddressId()).orElse(null);
                ProductShow productShow = new ProductShow();
                CustomerShow customerShow = new CustomerShow();

                if(product != null && customer != null && addAddress != null){
                    productShow.setProductId(product.get_id());
                    productShow.setName(product.getName());
                    productShow.setPrice(product.getPrice());
                    productShow.setStock(product.getStock());
                    productShow.setCategory(product.getCategory());
                    productShow.setDiscount(product.getDiscount());
                    productShow.setStatus(product.getStatus());
                    productShow.setImages(product.getImages());

                    customerShow.setCustomerId(customer.get_id());
                    customerShow.setFirstName(customer.getFirstName());
                    customerShow.setLastName(customer.getLastName());
                    customerShow.setPhoneNumber(customer.getPhoneNumber());
                    customerShow.setRating(customer.getRating());

                    list.add(new Orde(customerShow, order, addAddress, productShow));
                }
            }
        }
        if(list.size() > 10){
            return list.subList(0, 10);
        }
        return list;
    }



    public ArrayList<Order> findJunk(List<HashMap<String, String>> newBody){
        HashMap<String, String> body = newBody.get(0);
        String sub = body.get("sub");
        String subId = body.get("subId");

        switch (sub){
            case "customer":   // app
                return customerOrders(subId);
            case "status":
                return ordersByStatus(subId);
            case "rating":
                return ordersOfAParticularRating(subId);
            default:
                // do nothing;
                break;
        }
        return null;
    }

    private ArrayList<Order> ordersOfAParticularRating(String rating){
        ArrayList<Order> list = new ArrayList<>();
        int rate = Integer.parseInt(rating);
        for(int j=0; j<orderRepository.count(); j++){
            Order order = orderRepository.findAll().get(j);
            Customer customer = customerRepository.findById(order.getCustomerId()).orElse(null);
            if(customer != null && customer.getRating() >= rate){
                list.add(order);
            }
        }
        return list;
    }

    private ArrayList<Order> customerOrders(String customerId){
        ArrayList<Order> list = new ArrayList<>();
        for(int j=0; j<orderRepository.count(); j++){
            Order order = orderRepository.findAll().get(j);
            if(order.getCustomerId().equals(customerId)){
                list.add(order);
            }
        }
        return list;
    }

    private ArrayList<Order> ordersByStatus(String status){
        ArrayList<Order> list = new ArrayList<>();
        for(int j=0; j<orderRepository.count(); j++){
            Order order = orderRepository.findAll().get(j);
            if(order.getStatus().equals(status)){
                list.add(order);
            }
        }
        return list;
    }


    public OutLaw countJunk(HashMap<String, String> body){
        String sub = body.get("sub");
        String subId = body.get("subId");
        String mg = "";

        switch (sub){
            case "all":
                mg = String.valueOf(orderRepository.count());
                break;
            case "customer":          // app
                mg =  String.valueOf(countCustomerOrders(subId));
                break;
            case "status":
                mg =  String.valueOf(ordersCountByStatus(subId));
                break;
            default:
                // do nothing
                break;
        }
        return new OutLaw(mg);
    }

    private int countCustomerOrders(String customerId){
        int count = 0;
        for(int j=0; j<orderRepository.count(); j++){
            if(orderRepository.findAll().get(j).getCustomerId().equals(customerId)){
                count += 1;
            }
        }
        return count;
    }
    private int ordersCountByStatus(String status){
        int count = 0;
        for(int j=0; j<orderRepository.count(); j++){
            if(orderRepository.findAll().get(j).getStatus().equals(status)){
                count += 1;
            }
        }
        return count;
    }

    public OutLaw orderValueJunk(HashMap<String, String> body){
        String sub = body.get("sub");
        String subId = body.get("subId");
        String mg = "";

        switch (sub){
            case "status":
                mg = String.valueOf(StatusOrderValue(subId));             // sort by pending, transit or delivered
                break;
            case "customer":        // app
                mg =  String.valueOf(CustomerOrderValue(subId));            // amount customer's expenditure
                break;
            default: break;
        }
        return new OutLaw(mg);
    }

    private double CustomerOrderValue(String customerId){
        double count = 0.0;
        for(int j=0; j<orderRepository.count(); j++){
            Order order = orderRepository.findAll().get(j);
            if(order.getCustomerId().equals(customerId)){
                count += Double.parseDouble(order.getOrderValue());
            }
        }
        return count;
    }
    private double StatusOrderValue(String status){
        double count = 0.0;
        for(int j=0; j<orderRepository.count(); j++){
            Order order = orderRepository.findAll().get(j);
            if(order.getStatus().equals(status)){
                count += Double.parseDouble(order.getOrderValue());
            }
        }
        return count;
    }


    public Order updateOrder(HashMap<String, String> body){ return updater(body); }

    public String[] updateOrders(List<HashMap<String, String>> body){
        for(HashMap<String, String> newBody : body){ updater(newBody); }
        return new String[]{"Multiple order records updated"};
    }


    public OutLaw deleteOrder(HashMap<String, String> body){
        deleter(body);
        return new OutLaw("order records deleted");
    }

    public String[] deleteOrders(List<HashMap<String, String>> body){
        for(HashMap<String, String> newBody : body){
            deleter(newBody);
        }
        return new String[]{"Multiple order records deleted"};
    }

    public String deleteAll(){
        orderRepository.deleteAll();
        return "All order records deleted";
    }

    public OutLaw deleteOrdersOfMarkedCustomer(HashMap<String, String> body){
        String customerId = body.get("customerId");
        for(int z=0; z<orderRepository.count(); z++){
            Order order = orderRepository.findAll().get(z);
            if(order.getCustomerId().equals(customerId)){
                orderRepository.delete(order);
            }
        }
        return new OutLaw("Customer's orders deleted");
    }

    public OutLaw customerOrderDelete(HashMap<String, String> body){
        String customerId = body.get("customerId");
        String _id = body.get("_id");

        orderRepository.delete(orderRepository.findAll().stream()
                .filter(order -> order.getCustomerId().equals(customerId) &&
                        order.get_id().equals(_id)).findFirst().orElse(null));
        return new OutLaw("order record deleted");
    }


    public OutLaw createOrder(HashMap<String, String> body){
        String _id = body.get("cartId"); // get cartId of order
        if(_id == null || _id.isEmpty()){
            return new OutLaw("Cart id is null or empty");
        }

        Order order = mkOrder(body);
        String customerId = orderRepository.save(order).getCustomerId();
        cartRepository.deleteById(_id);   // clear customers cart
        return new OutLaw(orderMsg, customerId);
    }


    public String[] createOrders(List<HashMap<String, String>> body){
        String customerId = "";
        for(HashMap<String, String> newBody : body){
            String _id = newBody.get("cartId"); // get cartId of order
            if(_id == null || _id.isEmpty()){
                return new String[]{"Card id is null or empty"};
            }

            Order order = mkOrder(newBody);       // add to customer's orders

            if(order != null){
                cartRepository.deleteById(_id);   // clear customers cart
                orderMsg = "Order placed successfully";
                customerId = orderRepository.save(order).getCustomerId();
            }
        }
        return new String[] {orderMsg, extraMsg, customerId};
    }

    public Stream<Order> targetCustomerOrder(List<HashMap<String, String>> newBody){
        HashMap<String, String> body = newBody.get(0);
        return targetCus(body);
    }

    private Stream<Order> targetCus(HashMap<String, String> body){
        String password = body.get("password");
        String phoneNumber = body.get("phoneNumber");
        String action = body.get("action");
        Customer customer = customerRepository.findAll().stream()
                .filter(customer1 -> customer1.getPhoneNumber().equals(phoneNumber) &&
                        customer1.getPassword().equals(password))
                .findFirst().orElse(null);
        if(customer == null){ return null; }
        String customerId = customer.get_id();
        if(customerId != null && !customerId.isEmpty() && action.equals("readOrder")){
            return orderRepository.findAll().stream()
                    .filter(order -> order.getCustomerId().equals(customerId));
        }
        return null;
    }


    private Order mkOrder(HashMap<String, String> body){

        String productId;
        String customerId;
        String quantity;
        String size;
        String color;
        String userGpsLocation;
        String thirdPartyId;
        String addressId = body.get("addressId");
        String cartId = body.get("cartId");


        Cart cart = cartRepository.findById(cartId).orElse(null);
        if(cart != null){
            customerId = cart.getCustomerId();
            productId = cart.getProductId();
            quantity = cart.getQuantity();
            size = cart.getSize();
            color = cart.getColor();
            userGpsLocation = cart.getGpsLocation();
            thirdPartyId = cart.getThirdPartyId();
        } else { return null; }

        Order order = new Order(
                productId,
                customerId,
                addressId,
                cartId,
                quantity,
                size,
                color,
                userGpsLocation
        );

        Product product = productRepository.findById(productId).orElse(null);
        Customer customer = customerRepository.findById(cart.getCustomerId()).orElse(null);
        AddAddress addAddress = addAddressRepository.findById(addressId).orElse(null);

        if(product == null || customer == null || addAddress == null){orderMsg = "Order failed"; return null; }

        // calculate orderValue
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(product.getDiscount());
        String num = "";
        while (m.find()){ num = m.group(); }

        double oldPrice = Double.parseDouble(product.getPrice());
        double disD = Double.parseDouble(num);
        double newPrice = (100.00 - disD) * oldPrice / 100.00;
        double valueD = newPrice * Double.parseDouble(quantity);
        String orderValue = String.format(Locale.US, "%.2f", valueD);

        order.setOrderValue(orderValue);
        order.setOrderTime(timeConverter());
        order.setDelivery(setDeliveryTime());
        order.setStatus("pending");
        order.setProductGroup(product.getProductGroup());
        order.setImages(product.getImages());
        order.setProductName(product.getName());

        order.setCustomerName(customer.getFirstName() + " " + customer.getLastName());
        order.setCustomerPhoneNumber(customer.getPhoneNumber());
        order.setCustomerLocation(addAddress.getLocation());

        // if a third party is accessing our api
        if(thirdPartyId.length() != 0){
            ThirdParty thirdParty = thirdPartyRepository.findById(thirdPartyId).orElse(new ThirdParty());
            if(null != thirdParty.getId()){
                // ThirdParty is an authorized member
                double percentageIncrease = Double.parseDouble(thirdParty.getPercentageIncrease());
                double TPOrderValue = valueD * percentageIncrease;

                order.setThirdPartyId(thirdParty.getId());
                order.setThirdPartyIncrease(thirdParty.getPercentageIncrease());
                order.setThirdPartyOrderValue(String.format(Locale.US, "%.2f", TPOrderValue));
            }
        }

        orderMsg = "Order placed successfully.";
        updateProduct(productId, quantity, "create"); // update product stock
        return order;
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

    private String setDeliveryTime(){
        Date date = new Date();
        long millis = date.getTime() + 259200000;
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        return String.format("%s-%s-%s", day, month, year);
    }

//    if ama go to work i do my homework

    private Order updater(HashMap<String, String> body){
        // what can updated status
        String _id = body.get("_id");
        String status = body.get("status");

        // get old order object
        Order oldOrder = orderRepository.findById(_id).orElse(null);
        if(oldOrder != null){

            oldOrder.setStatus(status != null && !status.isEmpty() ?
                    status : oldOrder.getStatus());

            if(oldOrder.getStatus().equals("delivered")){
                updateCustomerRating(oldOrder.getCustomerId(), "update"); // update customer rating

                // update seller totalAmountMade
                String sellerId = productRepository.findById(oldOrder.getProductId()).orElse(null).getSellerId();
                Seller seller = sellerRepository.findById(sellerId).orElse(null);

                if( seller != null){
                    seller.setTotalAmountMade(seller.getTotalAmountMade() + Double.parseDouble(oldOrder.getOrderValue()));
                    seller.setDateCurrentPurchase(timeConverter()); // seller's date of current purchase
                }

                sellerRepository.save(seller);                      // save update seller

                // update product sales
                updateProduct(oldOrder);

                // set history
                History history = new History(oldOrder.getProductId(), oldOrder.getCustomerId());
                history.setSetDate(timeConverter());
                historyRepository.save(history);

            }
        }

        return orderRepository.save(oldOrder);    // save updated order
    }

    private Product updateProduct(String productId, String quantity, String userCase){

        Product product = productRepository.findById(productId).orElse(null);   // product details
        if(product != null){
            product.setStock( userCase.equals("create") ? String.valueOf(Integer.parseInt(product.getStock()) - Integer.parseInt(quantity))
                    :  String.valueOf(Integer.parseInt(product.getStock()) + Integer.parseInt(quantity)) );   // update product
        }

        productRepository.save(product);            // save updated product
        return product;
    }

    private void updateProduct(Order order){
        Product product = productRepository.findById(order.getProductId()).orElse(null);
        double dQuan = Double.parseDouble(order.getQuantity()) + product.getSales()[0];
        double dOrder = Double.parseDouble(order.getOrderValue()) + product.getSales()[1];

        double[] sales = {dQuan, dOrder};
        product.setSales(sales);
        productRepository.save(product);
    }

    private void updateCustomerRating(String _id, String userCase){
        Customer customer = customerRepository.findById(_id).orElse(null);
        if(customer != null){
            customer.setRating(userCase.equals("delete") ? customer.getRating() - 1 : customer.getRating() + 1);
        }
        customerRepository.save(customer);  // save new
    }

    private void deleter(HashMap<String, String> body) {
        String _id = body.get("_id");
        Order order = orderRepository.findById(_id).orElse(null);

        if(order != null){
            String productId = order.getProductId();
            String quantity = order.getQuantity();
            if(!order.getStatus().equals("delivered")){
                updateProduct(productId, quantity, "delete");           // update product stock
                updateCustomerRating(order.getCustomerId(), "delete"); // update customer rating
            }
        }
        orderRepository.deleteById(_id);
        //        return true;
    }



}
