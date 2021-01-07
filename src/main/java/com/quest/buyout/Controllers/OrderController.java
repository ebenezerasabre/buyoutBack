
package com.quest.buyout.Controllers;

import com.quest.buyout.model.*;

import com.quest.buyout.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Stream;

/**
 * After placing an order a customer has no control over the order
 * Customer control over order;
 * Place an order
 * Check orders, cancel
 * Know the state of their orders (Pending, transit, delivered)
 */

/**
 * important !
 * Ensuring that a product is in stock during purchase
 * All  orders can only be placed from cart's page
 *
 * A click on an item should take user to the product details page
 * update a product when an order is placed or deleted (except when delivered)
 */


@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    // TODO FIND

    // true
    // find all orders
    @GetMapping("/v1/order")
    public List<Order> index(){ return orderService.index(); }

    // true
    // find an order by id
    @PostMapping("/v1/order/_id")
    public Order findOrderById(@RequestBody HashMap<String, String> body){
        return orderService.findOrderById(body);
    }

    // get order by last 6 id
    @PostMapping("/v1/order/short")
    public Order findByLastSixLetters(@RequestBody HashMap<String, String> body){
        return orderService.findByLastSixLetters(body);
    }

    // find stream starts ****

    // get the detailed orders of a customer
    // eg for special customers vip
    @PostMapping("/v1/order/admin")
    public List<Orde> customerOrdes(@RequestBody List<HashMap<String, String>> body){
        return orderService.customerOrdes(body);
    }


    // find the detailed orders of pending orders ( first 10)
    @GetMapping("/v1/orders/admin")
    public List<Orde> ordes(){
        return orderService.ordes();
    }


    // find customer orders
    // true
    @PostMapping("/v1/order/sub")         // app
    public ArrayList<Order> findJunk(@RequestBody List<HashMap<String, String>> newBody){
        return orderService.findJunk(newBody);
    }



    // TODO COUNT

    // true
    // count stream starts ****
    @PostMapping("/v1/orderCount/sub")
    public OutLaw countJunk(@RequestBody HashMap<String, String> body){
        return orderService.countJunk(body);
    }



    // TODO ORDER_VALUE

    // true
    @PostMapping("/v1/orderValue/sub")
    public OutLaw orderValueJunk(@RequestBody HashMap<String, String> body){
        return orderService.orderValueJunk(body);
    }



    // TODO UPDATE

    // true
    // update a single order record
    @PostMapping("/v1/order/update")
    public Order updateOrder(@RequestBody HashMap<String, String> body){ return orderService.updateOrder(body); }

    //update
    // update multiple order records
    @PostMapping("/v1/order/updates")
    public String[] updateOrders(@RequestBody List<HashMap<String, String>> body){
        return orderService.updateOrders(body);
    }


    // TODO DELETE

    // true
    // delete a single order record
    @PostMapping("/v1/order/delete")
    public OutLaw deleteOrder(@RequestBody HashMap<String, String> body){
        return orderService.deleteOrder(body);
    }

    // true
    // delete multiple order records
    @PostMapping("/v1/order/deletes")
    public String[] deleteOrders(@RequestBody List<HashMap<String, String>> body){
        return orderService.deleteOrders(body);
    }

    // true
    // delete all
    // only used in production builds
    @GetMapping("/v1/order/del_all_admin")
    public String deleteAll(){
        return orderService.deleteAll();
    }

    // delete all the orders of a customer
    @PostMapping("/v1/order/customer/deletes")
    public OutLaw deleteOrdersOfMarkedCustomer(@RequestBody HashMap<String, String> body){
        return orderService.deleteOrdersOfMarkedCustomer(body);
    }

    // delete order of a customer
    @PostMapping("/v1/order/customer/delete")
    public OutLaw customerOrderDelete(@RequestBody HashMap<String, String> body){
        return orderService.customerOrderDelete(body);
    }



    // TODO CREATE

    // true
    // create a single order record
    @PostMapping("/v1/order")         // app
    public OutLaw createOrder(@RequestBody HashMap<String, String> body){
        return orderService.createOrder(body);
    }

    // true
    // create multiple order records
    @PostMapping("/v1/orders")
    public String[] createOrders(@RequestBody List<HashMap<String, String>> body){
        return orderService.createOrders(body);
    }


    // TODO THIRD PARTY

    // true
    // return orders of a target customer
    @PostMapping("/v1/order/targetCustomer")
    public Stream<Order> targetCustomerOrder(@RequestBody List<HashMap<String, String>> newBody){
        return orderService.targetCustomerOrder(newBody);
    }





}









