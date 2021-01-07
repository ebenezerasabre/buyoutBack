package com.quest.buyout.service;

import com.quest.buyout.dao.CustomerRepository;
import com.quest.buyout.dao.ProductRepository;
import com.quest.buyout.dao.ThirdPartyRepository;
import com.quest.buyout.dao.WishListRepository;
import com.quest.buyout.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

@Service
public class WishListService {


    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    private int increase = 5;


    public ArrayList<WishList> indexWishList(){
        ArrayList<WishList> listWish = new ArrayList<>();
        for(int j=0; j<wishListRepository.count(); j++){
            WishList wishList = wishListRepository.findAll().get(j);
            if( null != wishList.getProductId()){
                listWish.add(wishList);
            }
        }
        return listWish;
    }

    public ArrayList<Wish> index(){
        ArrayList<Wish> listWish = new ArrayList<>();
        Wish wish = new Wish();

        for(int j=0; j<wishListRepository.count(); j++){
            WishList wishList = wishListRepository.findAll().get(j);
            if( null != wishList.getProductId()){
                Product product = productRepository.findById(wishList.getProductId()).orElse(null);
                if(product != null){
                    wish = new Wish(
                            wishList.get_id(),
                            wishList.getProductId(),
                            wishList.getCustomerId(),
                            product.getName(),
                            product.getPrice(),
                            product.getMaterial(),
                            product.getImages()[0]
                    );
                }
                listWish.add(wish);

            }


        }
        return listWish;
    }

    public ArrayList<Wish> customerWishList(List<HashMap<String, String>> newBody){
        HashMap<String, String> body = newBody.get(0);
        String customerId = body.get("customerId");

        ArrayList<Wish> listWish = new ArrayList<>();
        Wish wish = new Wish();

        for(int p=0; p<wishListRepository.count(); p++){

            if(null != wishListRepository.findAll().get(p).getCustomerId() &&
                    wishListRepository.findAll().get(p).getCustomerId().equals(customerId)){
                WishList wishList = wishListRepository.findAll().get(p);
                Product product = productRepository.findById(wishList.getProductId()).orElse(null);
                if(product != null){
                    wish = new Wish(
                            wishList.get_id(),
                            wishList.getProductId(),
                            wishList.getCustomerId(),
                            product.getName(),
                            product.getPrice(),
                            product.getMaterial(),
                            product.getImages()[0]
                    );
                }
            }
            if (!wish.get_id().isEmpty()){ listWish.add(wish); }
        }
        if(listWish.size() >= 1){ return listWish; }
        return null;
    }

    // customers wishList should return products instead of wishList literals
    public List<Product> wishProductsInParts(List<HashMap<String, String>> body){
        HashMap<String, String> newBody = body.get(0);
        String customerId = newBody.get("customerId");
        String part = newBody.get("part"); // 1

        List<Product> repository = new ArrayList<>();
        List<Product> list = new ArrayList<>();
        int numberPart = Integer.parseInt(part);
        int mult = numberPart * increase; // 5

        for(int p=0; p<wishListRepository.count(); p++){
            if(wishListRepository.findAll().get(p).getCustomerId().equals(customerId)){
                WishList wishList = wishListRepository.findAll().get(p);
                Product product = productRepository.findById(wishList.getProductId()).orElse(null);
                if(product != null){
                   repository.add(product);
                }
            }
        }

        int repositoryLength = repository.size();
        int start = mult - increase; // 0
        int end = repositoryLength > mult ? mult : repositoryLength;

        if(repositoryLength > start){
            list = repository.subList(start, end);
        }
        return list;
    }


    public String countWishList(){
        return String.valueOf(wishListRepository.count());
    }

    public OutLaw countCustomerWishList(HashMap<String, String> body){
        String customerId = body.get("customerId");
        int count = (int) wishListRepository.findAll()
                .stream()
                .filter(wishList -> wishList.getCustomerId().equals(customerId)).count();

        return new OutLaw(count + "");
    }


    // A product can be added once to the wishList
    public Product createWishList(HashMap<String, String> body){
        WishList wishList = mkWishList(body);
        String productId = "";
        if(null != wishList){
             productId = wishListRepository.save(wishList).getProductId();
        }
        return productRepository.findById(productId).orElse(null);
    }

//    public String[] createWishListss(List<HashMap<String, String>> body){
//        for(HashMap<String, String> newBody : body){
//            wishListRepository.save(mkWishList(newBody));
//        }
//        return new String[] {"Multiple wishList records created"};
//    }

    // A product can be added once to the wishList
    public List<Product> createWishLists(List<HashMap<String, String>> body){
        List<WishList> wishes = new ArrayList<>();

        for(HashMap<String, String> newBody : body){
            WishList wishList = mkWishList(newBody);
            if(null != wishList){
                wishes.add(wishListRepository.save(wishList));
            }
        }

        List<Product> list = new ArrayList<>();
        for(WishList w : wishes){
                Product product = productRepository.findById(w.getProductId()).orElse(new Product());
                list.add(product);
        }
        return list;
    }




    public OutLaw deleteWishListById(HashMap<String, String> body){
        wishListDeleter(body);
        return new OutLaw("WishList record created");
    }

    public String[] deleteWishListsByIds(List<HashMap<String, String>> body){
        for(HashMap<String, String> newBody : body){
            wishListDeleter(newBody);
        }
        return new String[] {"Multiple wishList records deleted"};
    }

    public String deleteALL(){
        wishListRepository.deleteAll();
        return "All wishList records deleted";
    }

    public OutLaw deleteCustomerWishListById(HashMap<String, String> body){

        String customerId = body.get("customerId");
        String productId = body.get("productId");

        WishList wishList = wishListRepository.findAll().stream()
                .filter(wishList1 -> wishList1.getProductId().equals(productId) &&
                        wishList1.getCustomerId().equals(customerId)).findAny().orElse(null);

        if(wishList != null){
            wishListRepository.delete(wishList);
            return new OutLaw("Cart deleted");
        }
        return new OutLaw("Couldn't delete cart");
    }


    public Stream<WishList> wishListGetTargetCustomer(List<HashMap<String, String>> newBody){
        HashMap<String, String> body = newBody.get(0);
        String password = body.get("password");
        String phoneNumber = body.get("phoneNumber");
        String action = body.get("action");

        Customer customer = customerRepository.findAll().stream()
                .filter(customer1 -> customer1.getPhoneNumber().equals(phoneNumber) &&
                        customer1.getPassword().equals(password))
                .findFirst().orElse(null);

        String customerId = customer.get_id();

        if(customerId != null && customerId != "" && action.equals("readOrder")){
            // return customer cart
            return wishListRepository.findAll().stream()
                    .filter(wishList -> wishList.getCustomerId().equals(customerId));
        }
        return null;
    }


    private WishList mkWishList(HashMap<String, String> body){
        String productId = body.get("productId");
        String customerId = body.get("customerId");
        String thirdPartyId = body.get("thirdPartyId");


        // check if product is not already part of users wishList

        WishList wishList = wishListRepository
                .findAll()
                .stream()
                .filter(wishList1 -> wishList1.getProductId().equals(productId))
                .findFirst().orElse(null);

        if(null == wishList){
            Product product = productRepository.findById(productId).orElse(null);
            Customer customer = customerRepository.findById(customerId).orElse(null);
            if(product != null && customer != null){
                return new WishList(productId, customerId, thirdPartyId);
            }
        }
        return null;
    }

    private void wishListDeleter(HashMap<String, String> body){
        wishListRepository.deleteById(body.get("_id"));
    }


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


    // find all wishList of a thirdParty
    public List<WishList> thirdIndex(List<HashMap<String, String>> body){
          HashMap<String, String> hashMap = body.get(0);
          String thirdPartyId = hashMap.get("thirdPartyId");;
          List<WishList> list = new ArrayList<>();

        if(authenticate(hashMap)){
            for(int z=0; z<wishListRepository.count(); z++){
                WishList wishList = wishListRepository.findAll().get(z);
                if(wishList.getThirdPartyId().contains(thirdPartyId)){
                    list.add(wishList);
                }
            }
        }
        return list;
    }

    // create wishList for a thirdParty
    public Product createWishListThirdParty(HashMap<String, String> body){
        String productId = "";
        if(authenticate(body)){
            WishList wishList = mkWishList(body);
            if(null != wishList){
                productId = wishListRepository.save(wishList).getProductId();
            }
        }
      return productRepository.findById(productId).orElse(null);
    }

    // create multiple wishList for a thirdParty
    public List<Product> createWishListThirdParties(List<HashMap<String, String>> body){
        List<Product> list = new ArrayList<>();
        for(HashMap<String, String> newBody : body){
            String productId;
            if(authenticate(newBody)){
                WishList wishList = mkWishList(newBody);
                if(null != wishList){
                    productId = wishListRepository.save(wishList).getProductId();
                    Product product = productRepository.findById(productId).orElse(null);
                    list.add(product);
                }
            }
        }
        return list;
    }

    // delete wishLIst of a customer of a thirdParty
    public OutLaw deleteCustomerWishListThirdParty(HashMap<String, String> body){
        if(authenticate(body)){
            String customerId = body.get("customerId");
            String productId = body.get("productId");
            String thirdPartyId = body.get("thirdPartyId");

            WishList wishList = wishListRepository.findAll().stream()
                    .filter(wishList1 -> wishList1.getProductId().equals(productId) &&
                            wishList1.getCustomerId().equals(customerId) &&
                            wishList1.getThirdPartyId().equals(thirdPartyId)).findAny().orElse(null);
            if(wishList != null){
                wishListRepository.delete(wishList);
                return new OutLaw("Wish list deleted");
            }
        }
        return new OutLaw("Couldn't delete wish list");
    }

    public OutLaw deleteCustomerWishListsThirdParty(HashMap<String, String> body){

        return null;
    }

    // delete all the wishList records of a thirdParty
    public String[] deleteAllThirdPartyWishList(List<HashMap<String, String>> body){
        int count = 0;
        String thirdPartyId = body.get(0).get("thirdPartyId");

        for(int j=0; j<wishListRepository.count(); j++){
            WishList wishList = wishListRepository.findAll().get(j);
            if(wishList.getThirdPartyId().equals(thirdPartyId)){
                count += 1;
                wishListRepository.delete(wishList);
            }
        }
        return new String[]{String.format(Locale.US,"%d messages deleted", count)};
    }




}


