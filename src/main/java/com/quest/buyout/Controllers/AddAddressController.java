package com.quest.buyout.Controllers;

import com.quest.buyout.model.AddAddress;
import com.quest.buyout.model.OutLaw;
import com.quest.buyout.service.AddAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Stream;

@RestController
public class AddAddressController {

//    "5f0a64be96861e0c35fce88f"

//      "addressId": "5f0d8e2283782f524ab3c304",

    @Autowired
    AddAddressService addAddressService;

    // TODO FIND

    // find all customer addresses
    @GetMapping("/v1/address")
    public List<AddAddress> index(){ return addAddressService.index(); }

    // find address by id
    @PostMapping("/v1/address/_id")
    public Optional<AddAddress> findAddressById(@RequestBody HashMap<String, String> body){
        return addAddressService.findAddressById(body);
    }

    // find addresses by ids
    @PostMapping("/v1/address/_ids")
    public ArrayList<AddAddress> findAddressesByIds(@RequestBody List<HashMap<String, String>> body){
        return addAddressService.findAddressesByIds(body);
    }

    // find all the address of a customer
    @PostMapping("/v1/address/customer")
    public Stream<AddAddress> allCustomerAddress(@RequestBody List<HashMap<String, String>> body){
        return addAddressService.allCustomerAddress(body);
    }

    // TODO COUNT

    // count all customer addresses
    @GetMapping("/v1/address/count")
    public String countAddress(){ return addAddressService.countAddress();}

    // TODO CREATE

    @PostMapping("/v1/address")
    public AddAddress createAddressRecord(@RequestBody HashMap<String, String> body){
        return addAddressService.createAddressRecord(body);
    }

    // create multiple address records
    @PostMapping("/v1/addresses")
    public String[] createMultipleAddresses(@RequestBody List<HashMap<String, String>> body){
        return addAddressService.createMultipleAddresses(body);
    }




    // TODO UPDATE

    // update one record
    @PostMapping("/v1/address/update")
    public OutLaw updateAddressById(@RequestBody HashMap<String, String> body){
        return addAddressService.updateAddressById(body);
    }

    // update multiple records
    @PostMapping("/v1/address/updates")
    public String[] udpateAddressesByIds(@RequestBody List<HashMap<String, String>> newBody){
        return addAddressService.updateAddressesByIds(newBody);
    }


    // TODO DELETE

    // delete one record
    @PostMapping("/v1/address/delete")
    public OutLaw deleteAddressById(@RequestBody HashMap<String, String> body) {
        return addAddressService.deleteAddressById(body);
    }

    // delete multiple records
    @PostMapping("/v1/address/deletes")
    public String[] deleteMore(@RequestBody List<HashMap<String, String>> body) {
        return addAddressService.deleteAddressesByIds(body);
    }

    // delete address of a customer
    @PostMapping("/v1/customer/address/delete")
    public OutLaw deleteCustomerAddressById(@RequestBody HashMap<String, String> body){
        return addAddressService.deleteCustomerAddressById(body);
    }

    // delete all records
    // to be used only in development builds only!
    @GetMapping("/v1/address/del_all_admin")
    public String deleteAll(){
        return addAddressService.deleteAll();
    }



}
