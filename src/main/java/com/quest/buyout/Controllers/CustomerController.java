
package com.quest.buyout.Controllers;

import com.quest.buyout.model.*;
import com.quest.buyout.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;


    // when sending a raw image file
    @PostMapping("/v1/customer/image")
    public String setCustomerImage(@RequestParam("file") MultipartFile file,
                                   @RequestParam("customerId") String customerId)
            throws IllegalStateException, IOException {
        return customerService.setCustomerImage(file, customerId);
    }


    // TODO FIND

    // find all customers
    // true
    @GetMapping("/v1/customer")
    public List<Customer> index(){
        return customerService.index();
    }

    //find one customer
    // true
    @PostMapping("/v1/customer/_id")
    public Optional<Customer> findCustomerById(@RequestBody HashMap<String, String> body){
        return customerService.findCustomerById(body);
    }

    // find multiple customers
    // true
    @PostMapping("/v1/customer/_ids")
    public ArrayList<Customer> findCustomersByIds(@RequestBody List<HashMap<String, String>> body){
        return customerService.findCustomersByIds(body);
    }

    // sign in customer
    // find a customer by phoneNumber and password      // app
    @PostMapping("/v1/customer/pwd")
    public Customer findCustomerByPhoneAndPwd(@RequestBody HashMap<String, String> body){
        return customerService.findCustomerByPhoneAndPwd(body);
    }

    // document
    // find a customer by phoneNumber and name      // app
    @PostMapping("/v1/customer/name")
    public Customer findCustomerByPassnm(@RequestBody HashMap<String, String> body){
        return customerService.findCustomerByPassnm(body);
    }

    // document
    // find customers by rating
    @PostMapping("/v1/customer/rating")
    public Stream<Customer> findCustomersByRating(@RequestBody List<HashMap<String, String>> newBody){
        return customerService.findCustomersByRating(newBody);
    }

    // create administrator
//    @PostMapping("/v1/create/admin")
//    public

    // sign in administrator


    // TODO COUNT
    // count all customers
    // true
    @GetMapping("/v1/customer/count")
    public String countCustomers(){
        return customerService.countCustomers();
    }


    // TODO CREATE


    // true
    // create a customer record     // app
    @PostMapping("/v1/customer")
    public OutLaw createCustomer(@RequestBody HashMap<String, String> body){
        return customerService.createCustomer(body);
    }


    // create multiple customers
    // true
    @PostMapping("/v1/customers")
    public String[] createCustomers(@RequestBody List<HashMap<String, String>> body){
        return customerService.createCustomers(body);
    }


    // TODO UPDATE

    /**
     * updating a customer , fields that can't be updated
     * firstName, lastName, ,  password
     * The rest can be updated
     * @param body
     * @return
     */

    // update customer
    @PostMapping("/v1/customer/update")
    public OutLaw updateCustomer(@RequestBody HashMap<String, String> body){
        return customerService.UpdateCustomer(body);
    }

    //update multiple customer records
    // true
    @PostMapping("/v1/customer/updates")
    public String[] updateCustomersByIds(@RequestBody List<HashMap<String, String>> body){
        return customerService.updateCustomersByIds(body);
    }

    @PostMapping("/v1/customer/reset")
    public OutLaw resetPassword(@RequestBody HashMap<String, String> body){
        return customerService.resetPassword(body);
    }


    // TODO DELETE

    // delete a customer          // app
    @PostMapping("/v1/customer/delete")
    public OutLaw deleteCustomer(@RequestBody HashMap<String, String> body){
        return customerService.deleteCustomer(body);
    }

    // delete multiple customer records
    // true
    @PostMapping("/v1/customer/deletes")
    public String[] deleteCustomers(@RequestBody List<HashMap<String, String>> body){
        return customerService.deleteCustomers(body);
    }

    // delete all customer records
    // only used in development builds
    // true
    @GetMapping("/v1/customer/del_all_admin")
    public String deleteAll(){
        return customerService.deleteAll();
    }


}




