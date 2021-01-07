
package com.quest.buyout.Controllers;

import com.quest.buyout.model.OutLaw;
import com.quest.buyout.model.Product;
import com.quest.buyout.model.History;
import com.quest.buyout.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//    History became Legend
//    And legend became Myth


@RestController
public class HistoryController {

    @Autowired
    HistoryService historyService;

    // TODO FIND


    // find history
    @GetMapping("/v1/history")
    public List<History> index(){ return historyService.index(); }

    // history for home fragment, first 7
    // return products previously bought by customer      // app
    @PostMapping("/v1/history/featured/customer")
    public List<Product> findCustomerHistoryIndex(@RequestBody List<HashMap<String, String>> newBody){
        return historyService.findCustomerHistoryIndex(newBody);
    }


    // return products previously bought by customer      // app
    @PostMapping("/v1/history/customer")
    public ArrayList<Product> findCustomerHistory(@RequestBody List<HashMap<String, String>> newBody){
        return historyService.findCustomerHistory(newBody);
    }

    @PostMapping("/v1/history/customer/parts")
    List<Product> customerHistoryInParts(@RequestBody List<HashMap<String, String>> body){
        return historyService.customerHistoryInParts(body);
    }


    // I don't think history has to be updated

    // TODO DELETE

    //delete all the history of a customer        // app
    @PostMapping("/v1/history/deleteAll/customer")
    public OutLaw deleteAllCustomerHistory(@RequestBody HashMap<String, String> body ){
        return historyService.deleteAllCustomerHistory(body);
    }


    // delete
    //delete a the history of a customer        // app
    @PostMapping("/v1/customer/history/delete")
    public OutLaw deleteCustomerHistoryById(@RequestBody HashMap<String, String> body){
        return historyService.deleteCustomerHistoryById(body);
    }

    // clear historyRepository
    @GetMapping("/v1/history/del_all_admin")
    public OutLaw deleteAll(){
        return historyService.deleteAll();
    }

    //TODO COUNT

    //count history
    @GetMapping("/v1/history/count")
    public String count(){ return historyService.count(); }

    // count history of a customer
    @PostMapping("/v1/customer/history/count")
    public OutLaw countCustomerHsitory(@RequestBody  HashMap<String, String> body){
        return historyService.countCustomerHsitory(body);
    }



}
