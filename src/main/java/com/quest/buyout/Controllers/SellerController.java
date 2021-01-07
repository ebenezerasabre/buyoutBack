
package com.quest.buyout.Controllers;


import com.quest.buyout.model.OutLaw;
import com.quest.buyout.model.Seller;
import com.quest.buyout.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class SellerController {

    @Autowired
    SellerService sellerService;

    // TODO FIND

    // find all sellers
    @GetMapping("/v1/seller")
    public List<Seller> index(){
        return sellerService.index();
    }

    // find seller by id
    @PostMapping("/v1/seller/_id")
    public Seller findSellerById(@RequestBody HashMap<String, String> body){
        return sellerService.findSellerById(body);
    }


    // TODO CREATE

    // create seller record     //  app
    @PostMapping("/v1/seller")
    public OutLaw createSeller(@RequestBody  HashMap<String, String> body ){
        return sellerService.createSeller(body);
    }

    // create multiple seller records
    @PostMapping("/v1/sellers")
    public String[] createSellers(@RequestBody List<HashMap<String, String>> body){
        return sellerService.createSellers(body);
    }



    // TODO UPDATE

    @PostMapping("/v1/sell er/update")
    public OutLaw updateSeller(@RequestBody  HashMap<String, String> body){
        return sellerService.updateSeller(body);
    }

    // update multiple seller records
    @PostMapping("/v1/seller/updates")
    public String[] updateSellers(@RequestBody List<HashMap<String, String>> body){
        return sellerService.updateSellers(body);
    }


    // TODO DELETE

    // delete a seller record
    @PostMapping("/v1/seller/delete")
    public OutLaw deleteSellerById(@RequestBody HashMap<String, String> body){
        return sellerService.deleteSellerById(body);
    }

    // delete multiple seller record
    @PostMapping("/v1/seller/deletes")
    public String[] deleteSellersByIds(@RequestBody List<HashMap<String, String>> body){
        return sellerService.deleteSellersByIds(body);
    }

    // delete all seller records
    // only used in development builds
    @GetMapping("/v1/seller/del_all_admin")
    public String deleteAll(){
        return sellerService.deleteAll();
    }



}
