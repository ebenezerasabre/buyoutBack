package com.quest.buyout.service;

import com.quest.buyout.dao.CartRepository;
import com.quest.buyout.dao.CustomerRepository;
import com.quest.buyout.dao.ProductRepository;
import com.quest.buyout.dao.ThirdPartyRepository;
import com.quest.buyout.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Service
public class CartService {


    @Autowired
    CartRepository cartRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ThirdPartyRepository thirdPartyRepository;


    public List<Cart> index(){ return cartRepository.findAll(); }

    public Cart findCartById(HashMap<String, String> body){
        String _id = body.get("_id");
        return cartRepository.findById(_id).orElse(null);
    }

    public Stream<Cart> customerCart(List<HashMap<String, String>> newBody){
        HashMap<String, String> body = newBody.get(0);
        String customerId = body.get("customerId");
        return cartRepository.findAll().stream()
                .filter(cart -> cart.getCustomerId().equals(customerId));
    }

    public Cart createCart(HashMap<String, String> body){
        return cartRepository.save(mkCart(body));
    }

    public List<Cart> createCarts(List<HashMap<String, String>> body){
        List<Cart> list = new ArrayList<>();
        for(HashMap<String, String> obj : body){
            list.add(cartRepository.save(mkCart(obj)));
        }
        return list;
    }

    public OutLaw thumbs(HashMap<String, String> body){
        String sub = body.get("sub");
        String cartId = body.get("cartId");
        String customerId = body.get("customerId");
        String msg = "Couldn't change cart quantity quantity";

        Cart cart = cartRepository.findAll().stream()
                .filter(cart1 -> cart1.get_id().equals(cartId) && cart1.getCustomerId().equals(customerId))
                .findFirst()
                .orElse(null);

        // vars
        double dPrice;
        double unitPrice;
        String newPrice;
        int prevQuantity;

        if(cart != null){
            int quantity = Integer.parseInt(cart.getQuantity());
            prevQuantity = quantity;
            switch (sub){
                case "thumbsUp":
                    quantity += 1;
                    cart.setQuantity(String.valueOf(quantity));
                    msg = String.valueOf(quantity);

                    // increase orderValue
                    dPrice = Double.parseDouble(cart.getOrderValue());
                    unitPrice = dPrice / prevQuantity;
                    newPrice = String.format(Locale.US, "%.2f", unitPrice * quantity);
                    cart.setOrderValue(newPrice);

                    break;
                case "thumbsDown":
                    if(quantity >= 1){
                        quantity -= 1;
                        cart.setQuantity(String.valueOf(quantity));
                        msg = String.valueOf(quantity);

                        // decrease orderValue
                        dPrice = Double.parseDouble(cart.getOrderValue());
                        unitPrice = dPrice / prevQuantity;
                        newPrice = String.format(Locale.US, "%.2f", unitPrice * quantity);
                        cart.setOrderValue(newPrice);
                    }
                    break;
                default:
                    // do nothing
                    break;
            }
            cartRepository.save(cart);
            return new OutLaw(msg);
        }
        return new OutLaw(msg);
    }

    public String cartCount(){
        return String.valueOf(cartRepository.count());
    }

    public OutLaw customerCartCount(HashMap<String, String> body){

        String customerId = body.get("customerId");
        int count = (int) cartRepository.findAll().stream()
                .filter(cart -> cart.getCustomerId().equals(customerId)).count();
        return new OutLaw(String.valueOf(count));
    }

    public OutLaw deleteCartById(HashMap<String, String> body){
        deleter(body);
        return new OutLaw("Cart record deleted");
    }

    public OutLaw deleteCartsByIds(List<HashMap<String, String>> body){
        for(HashMap<String, String> newBody : body){
            deleter(newBody);
        }
        return new OutLaw("Cart records deleted");
    }

    public String deleteAll(){
        cartRepository.deleteAll();
        return "All cart records deleted";
    }

    public String[] customerCartDelete(List<HashMap<String, String>> newBody){
        for(HashMap<String, String> body : newBody){
            String customerId = body.get("customerId");
            String _id = body.get("_id");

            Cart crt = cartRepository.findAll().stream()
                    .filter(cart -> cart.getCustomerId().equals(customerId) &&
                            cart.get_id().equals(_id)).findFirst().orElse(null);
            if(null != crt){
                cartRepository.delete(crt);
                return new String[]{"cart deleted"};
            }
        }
        return new String[]{"Couldn't delete customer cart"};
    }

    public Stream<Cart> targetCustomerCart(HashMap<String, String> body){
        return targetCus(body);
    }


    // utility functions

    private Stream<Cart> targetCus(HashMap<String, String> body){
        String password = body.get("password");
        String phoneNumber = body.get("phoneNumber");
        String action = body.get("action");

        Customer customer = customerRepository.findAll().stream()
                .filter(customer1 -> customer1.getPhoneNumber().equals(phoneNumber) &&
                        customer1.getPassword().equals(password))
                .findFirst().orElse(null);

        if(customer != null){
            String customerId = customer.get_id();

            if(customerId != null && customerId != "" && action.equals("readCart")){
                // return customer cart
                return cartRepository.findAll().stream()
                        .filter(cart -> cart.getCustomerId().equals(customerId));
            }
        }
        return null;
    }


    private Cart mkCart(HashMap<String, String> body){

        String productId = body.get("productId");
        String customerId = body.get("customerId");
        String quantity = body.get("quantity");
        String size =  body.get("size");
        String color = body.get("color");
        String userGpsLocation = body.get("userGpsLocation");
        String thirdPartyId = body.get("thirdPartyId");

        Customer customer = customerRepository.findById(customerId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);

        if(product != null && customer != null){

            // work new price with the given discount
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(product.getDiscount());
            String num = "";
            while(m.find()){ num = m.group(); }

            double oldPrice = Double.parseDouble(product.getPrice());
            double disD = Double.parseDouble(num);
            double newPrice = (100.00 - disD) * oldPrice / 100;
            double valueD = newPrice * Double.parseDouble(quantity);

            String orderValue = String.format(Locale.US, "%.2f", valueD);

            return new Cart(productId,
                    product.getName(),
                    customerId,
                    product.getMaterial(),
                    quantity,
                    orderValue,
                    size,
                    color,
                    userGpsLocation,
                    product.getSellerId(),
                    thirdPartyId,
                    product.getImages());
        }
        return new Cart();
    }

    private void deleter(HashMap<String, String> body){ cartRepository.deleteById(body.get("_id")); }



    // TODO THIRD PARTY


    private boolean authenticate(HashMap<String, String> body){
        String thirdPartyId = body.get("thirdPartyId");
        List<ThirdParty> authorized = thirdPartyRepository.findAll();
        boolean auth = false;

        for(ThirdParty tp : authorized){
            if(tp.getId().contains(thirdPartyId)){
                auth = true;
                break;
            }
        }
        return auth;
    }


    // find all cart of a thirdParty
    public List<Cart> thirdIndex(List<HashMap<String, String>> body){
        HashMap<String, String> hashMap = body.get(0);
        String thirdPartyId = hashMap.get("thirdPartyId");
        List<Cart> list = new ArrayList<>();

        if(authenticate(hashMap)){
            for(int z=0; z<cartRepository.count(); z++){
                Cart cart = cartRepository.findAll().get(z);
                if(cart.getThirdPartyId().contains(thirdPartyId)){
                    list.add(cart);
                }
            }
        }
        return list;
    }

    public Cart createCartThirdParty(HashMap<String, String> body){
        if(authenticate(body)){
            Cart cart = mkCart(body);
            if(null != cart.get_id()){
                return cartRepository.save(cart);
            }
        }
        return new Cart();
    }

    public List<Cart> createCartsThirdParty(List<HashMap<String, String>> body){
        List<Cart> list = new ArrayList<>();
        for(HashMap<String, String> hs : body){
            if(authenticate(hs)){
                Cart cart = mkCart(hs);
                if(null != cart.get_id()){
                    list.add(cartRepository.save(cart));
                }
            }
        }
        return list;
    }

    // delete wishList of a customer
    public OutLaw deleteCustomerWishListThirdParty(HashMap<String, String> body){
        if(authenticate(body)){
            String customerId = body.get("customerId");
            String productId = body.get("productId");
            String thirdPartyId = body.get("thirdPartyId");

            Cart cart = cartRepository.findAll().stream()
                    .filter(cart1 -> cart1.getProductId().equals(productId) &&
                            cart1.getCustomerId().equals(customerId) &&
                            cart1.getThirdPartyId().equals(thirdPartyId)).findFirst().orElse(null);
            if(cart != null){
                cartRepository.delete(cart);
                return new OutLaw("Cart record deleted");
            }
        }
        return new OutLaw("Couldn't delete cart record");
    }

    public OutLaw deleteCustomerWishListsThirdParty(HashMap<String, String> body){

        return null;
    }


    public OutLaw deleteAllThirdPartyCart(HashMap<String, String> body){

        return null;
    }

























}
