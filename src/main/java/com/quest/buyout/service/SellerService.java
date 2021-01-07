package com.quest.buyout.service;

import com.quest.buyout.dao.SellerRepository;
import com.quest.buyout.model.OutLaw;
import com.quest.buyout.model.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    public List<Seller> index(){
        return sellerRepository.findAll();
    }

    public Seller findSellerById(HashMap<String, String> body){
        return sellerRepository.findById(body.get("_id")).orElse(null);
    }

    public OutLaw createSeller(HashMap<String, String> body ){
        sellerRepository.save(mkSeller(body));
        return new OutLaw( "Seller created");
    }

    public String[] createSellers(List<HashMap<String, String>> body){
        for(HashMap<String, String> newBody : body){
            Seller seller = mkSeller(newBody);
            sellerRepository.save(seller);
        }
        return new String[]{"Sellers created"};
    }

    public OutLaw updateSeller(HashMap<String, String> body){
        sellerUpdater(body);
        return new OutLaw("Seller updated");
    }

    public String[] updateSellers(List<HashMap<String, String>> body){
        for(HashMap<String, String> newBody : body){
            sellerUpdater(newBody);
        }
        return new String[]{"Multiple seller records updated"};
    }

    public OutLaw deleteSellerById(HashMap<String, String> body){
        sellerDeleter(body);
        return new OutLaw("A seller record deleted");
    }

    public String[] deleteSellersByIds(List<HashMap<String, String>> body){
        for(HashMap<String, String> newBody : body){
            sellerDeleter(newBody);
        }
        return new String[]{"Multiple seller records deleted"};
    }

    public String deleteAll(){
        sellerRepository.deleteAll();
        return "All seller records deleted";
    }

    // utility functions
    private Seller mkSeller(HashMap<String, String> body){
        Seller seller = new Seller(
                body.get("brandName"),
                body.get("phoneNumber"),
                body.get("rights"),
                body.get("profilePic")
        );

        seller.setDateRegistered(timeConverter());

        return seller;
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

    private void sellerDeleter(HashMap<String, String> body){
        sellerRepository.deleteById(body.get("_id"));
    }

    private void sellerUpdater(HashMap<String, String> body){
        String _id = body.get("_id");
        Seller oldSeller = sellerRepository.findById(_id).orElse(null);

        if(oldSeller != null){
            Seller newSeller = mkSeller(body);
            Seller updatedOldSeller = compareSeller(oldSeller, newSeller);
//            sellerRepository.deleteById(_id);
            sellerRepository.save(updatedOldSeller);
        }
    }


    private Seller compareSeller(Seller oldSeller, Seller newSeller){
        oldSeller.setBrandName(newSeller.getBrandName() != null && newSeller.getBrandName() != ""
                ? newSeller.getBrandName() : oldSeller.getBrandName());

        oldSeller.setPhoneNumber(newSeller.getPhoneNumber() != null && newSeller.getPhoneNumber() != ""
                ? newSeller.getPhoneNumber() : oldSeller.getPhoneNumber());

        oldSeller.setRights(newSeller.getRights() != null && newSeller.getRights() != ""
                ? newSeller.getRights() : oldSeller.getRights());

        oldSeller.setProfilePic(newSeller.getProfilePic() != null && newSeller.getProfilePic() != ""
                ? newSeller.getProfilePic() : oldSeller.getProfilePic());
        return oldSeller;
    }



}
