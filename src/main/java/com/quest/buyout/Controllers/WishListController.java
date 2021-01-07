
package com.quest.buyout.Controllers;

import com.quest.buyout.dao.ThirdPartyRepository;
import com.quest.buyout.model.*;
import com.quest.buyout.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

@RestController
public class WishListController {


    @Autowired
    WishListService wishListService;

    @Autowired
    ThirdPartyRepository thirdPartyRepository;


    // TODO FIND

    // true
    //find all wishLists

    @GetMapping("/v1/wishList")
    public ArrayList<WishList> indexWishList(){
        return wishListService.indexWishList();
    }

    @GetMapping("/v1/wish")
    public ArrayList<Wish> index(){
       return wishListService.index();
    }

    // true
    // find items in customer's wish list
    @PostMapping("/v1/wishList/customer")
//    public Stream<WishList> cusWish(@RequestBody List<HashMap<String, String>> newBody){
    public ArrayList<Wish> customerWishList(@RequestBody List<HashMap<String, String>> newBody){
        return wishListService.customerWishList(newBody);
    }

    @PostMapping("/v1/wishList/customer/parts")
    public List<Product> wishProductsInParts(@RequestBody List<HashMap<String, String>> body){
        return wishListService.wishProductsInParts(body);
    }

    // TODO COUNT

    // true
    // count all wishList
    @GetMapping("/v1/wishList/count")
    public String countWishList(){
        return wishListService.countWishList();
    }

    // count wishList of a customer
    // true
    @PostMapping("/v1/customer/wishList/count")
    public OutLaw countCustomerWishList(@RequestBody  HashMap<String, String> body){
        return wishListService.countCustomerWishList(body);
    }


    // TODO CREATE

    // true
    // create wishList record       // app post a string, get a string back
    @PostMapping("/v1/wishList")
    public Product createWishList(@RequestBody HashMap<String, String> body){
        return wishListService.createWishList(body);
    }

    // new
    @PostMapping("/v1/wishLists")
    public List<Product> createWishLists(@RequestBody List<HashMap<String, String>> body){
        return wishListService.createWishLists(body);
    }


    // TODO DELETE

    // true
    // delete a wish record
    @PostMapping("/v1/wishList/delete")
    public OutLaw deleteWishListById(@RequestBody HashMap<String, String> body){
        return wishListService.deleteWishListById(body);
    }

    // true
    // delete multiple wish records
    @PostMapping("/v1/wishList/deletes")
    public String[] deleteWishListsByIds(@RequestBody List<HashMap<String, String>> body){
        return wishListService.deleteWishListsByIds(body);
    }


    // delete all records
    // only used in development builds
    @GetMapping("/v1/wishList/del_all_admin")
    public String deleteALL(){
        return wishListService.deleteALL();
    }


    // delete wishList record of a customer
    @PostMapping("/v1/customer/wishList/delete")
    public OutLaw deleteCustomerWishListById(@RequestBody HashMap<String, String> body){
        return wishListService.deleteCustomerWishListById(body);
    }


    // TODO THIRD PARTY

    // true
    //  third party clients to communicate with our api
    // they can ask for users password and the action they want to perform
    @PostMapping("/v1/wishList/targetCustomer")
    public Stream<WishList> wishListGetTargetCustomer(@RequestBody List<HashMap<String, String>> newBody){
        return wishListService.wishListGetTargetCustomer(newBody);
    }


    //  FIND WISH LIST
    @PostMapping("v1/third/wishList")
    public List<WishList> thirdIndex(@RequestBody List<HashMap<String, String>> body){
        return wishListService.thirdIndex(body);
    }

    //  CREATE WISH LIST

    @PostMapping("/v1/third/wishList/create")
    public Product createWishListThirdParty(@RequestBody HashMap<String, String> body) {
        return wishListService.createWishListThirdParty(body);
    }

    @PostMapping("/v1/third/wishList/creates")
    public List<Product> createWishListThirdParties(@RequestBody List<HashMap<String, String>> body) {
        return wishListService.createWishListThirdParties(body);
    }


    // DELETE WISH LIST

    // delete wishList record of a customer
    @PostMapping("/v1/third/wishList/delete")
    public OutLaw deleteCustomerWishListThird(@RequestBody HashMap<String, String> body){
        return wishListService.deleteCustomerWishListThirdParty(body);
    }

    // delete all wish lists
    @PostMapping("/v1/third/wishList/all/delete")
    public String[] deleteAllThirdPartyWishList(@RequestBody List<HashMap<String, String>> body){
        return wishListService.deleteAllThirdPartyWishList(body);
    }



}

