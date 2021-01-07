
package com.quest.buyout.Controllers;

import com.quest.buyout.model.*;
import com.quest.buyout.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

// Cart can't be updated

@RestController
public class CartController {

    @Autowired
    CartService cartService;


    // TODO FIND
    //true
    // find all carts
    @GetMapping("/v1/cart")
    public List<Cart> index(){ return cartService.index(); }

    // true
    // find a cart by id
    @PostMapping("/v1/cart/_id")
    public Cart findCartById(@RequestBody HashMap<String, String> body){
        return cartService.findCartById(body);
    }

    // true
    // find items in a customer's cart
//    @PostMapping("/v1/cart/customerId")
    @PostMapping("/v1/cart/customer")
    public Stream<Cart> customerCart(@RequestBody List<HashMap<String, String>> newBody){
        return cartService.customerCart(newBody);
    }


    // TODO CREATE

    // true // app
    // create a cart record
    @PostMapping("/v1/cart")
    public Cart createCart(@RequestBody HashMap<String, String> body){
        return cartService.createCart(body);
    }


//    // true
//    // create multiple carts old
//    @PostMapping("/v1/cartss")
//    public String[] createCartss(@RequestBody List<HashMap<String, String>> body){
//        return cartService.createCartss(body);
//    }

    // create multiple carts new
    @PostMapping("/v1/carts")
    public List<Cart> createCarts(@RequestBody List<HashMap<String, String>> body){
        return  cartService.createCarts(body);
    }

    // increase cart quantity
    @PostMapping("/v1/cart/thumbs")
    public OutLaw thumbs(@RequestBody HashMap<String, String> body){
        return cartService.thumbs(body);
    }


    // TODO COUNT

    //true
    // count all carts
    @GetMapping("/v1/cart/count")
    public String cartCount(){
        return cartService.cartCount();
    }

    // true
    // cart records of a customer  // app
    @PostMapping("/v1/customer/cart/count")
    public OutLaw customerCartCount(@RequestBody HashMap<String, String> body){
        return cartService.customerCartCount(body);
    }

    // TODO DELETE

    // delete a cart record  // app
    @PostMapping("/v1/cart/delete")
    public OutLaw deleteCartById(@RequestBody HashMap<String, String> body){
        return cartService.deleteCartById(body);
    }

    // delete multiple cart records
    @PostMapping("/v1/cart/deletes")
    public OutLaw deleteCartsByIds(@RequestBody List<HashMap<String, String>> body){
        return cartService.deleteCartsByIds(body);
    }

    // delete all cart records
    // only used in development builds
    @GetMapping("/v1/cart/del_all_admin")
    public String deleteAll(){
        return cartService.deleteAll();
    }

    // delete cart record of a customer
    @PostMapping("/v1/customer/cart/delete")
    public String[] customerCartDelete(@RequestBody List<HashMap<String, String>> newBody){
        return cartService.customerCartDelete(newBody);
    }

    // TODO THIRD PARTY

    // true     // app
    @PostMapping("/v1/cart/targetCustomer")
    public Stream<Cart> targetCustomerCart(@RequestBody HashMap<String, String> body){
        return cartService.targetCustomerCart(body);
    }


}
















