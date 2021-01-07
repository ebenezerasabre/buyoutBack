package com.quest.buyout.service;

import com.quest.buyout.dao.AddAddressRepository;
import com.quest.buyout.model.AddAddress;
import com.quest.buyout.model.OutLaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class AddAddressService {


    @Autowired
    AddAddressRepository addAddressRepository;


    public List<AddAddress> index(){
        return addAddressRepository.findAll();
    }


    public Optional<AddAddress> findAddressById( HashMap<String, String> body){
        return addAddressRepository.findById(body.get("_id"));
    }

    public ArrayList<AddAddress> findAddressesByIds(List<HashMap<String, String>> body){
        ArrayList<AddAddress> list = new ArrayList<>();

        for(HashMap<String, String> newBody : body){
            AddAddress addAddress = addAddressRepository.findById(newBody.get("_id")).orElse(null);
            list.add(addAddress);
        }
        return list;
    }

    public Stream<AddAddress> allCustomerAddress( List<HashMap<String, String>> body){
        HashMap<String, String> newBody = body.get(0);
        String customerId = newBody.get("customerId");

        return addAddressRepository.findAll().stream()
                .filter(addAddress -> addAddress.getCustomerId().equals(customerId));
    }

    public String countAddress(){
        return String.valueOf(addAddressRepository.count());
    }

    public AddAddress createAddressRecord(HashMap<String, String> body){
        if( body.get("customerId") != null && !body.get("customerId").isEmpty()){
           return addAddressRepository.save(mkAddress(body));
        }
        return new AddAddress();
    }

    public String[] createMultipleAddresses(List<HashMap<String, String>> body){
        for(HashMap<String, String> newBody : body){
            if( newBody.get("customerId") != null && !newBody.get("customerId").isEmpty()){
                addAddressRepository.save(mkAddress(newBody));
            }
        }
        return new String[]{"All address created"};
    }

    public OutLaw updateAddressById(HashMap<String, String> body){

        // make addAddress of the incoming properties
        AddAddress newAddress = mkAddress(body);

        // find the old AddAddress object
        AddAddress oldAddress = addAddressRepository.findById(body.get("_id")).orElse(null);

        if(oldAddress != null){
            addAddressRepository.save(compareAddress(oldAddress, newAddress));
            return new OutLaw("Address updated");
        }
        return new OutLaw("Couldn't update address");
    }

    public String[] updateAddressesByIds(List<HashMap<String, String>> newBody){
        for(HashMap<String, String> body : newBody){

            // make addAddress of the incoming properties
            AddAddress newAddress = mkAddress(body);

            // find the old AddAddress object
            AddAddress oldAddress = addAddressRepository.findById(body.get("_id")).orElse(null);

            if(oldAddress != null){
                addAddressRepository.save(compareAddress(oldAddress, newAddress));
            }
        }
        return new String[]{"address updated"};
    }


    public OutLaw deleteAddressById(HashMap<String, String> body) {
        addAddressRepository.deleteById(body.get("_id"));
        return new OutLaw("address deleted");
    }

    public String[] deleteAddressesByIds(List<HashMap<String, String>> body) {
        for(HashMap<String, String> newBody : body){
            addAddressRepository.deleteById(newBody.get("_id"));
        }
        return new String[]{"Multiple address deleted"};
    }

    public OutLaw deleteCustomerAddressById(HashMap<String, String> body){
        String customerId = body.get("customerId");
        String addressId = body.get("addressId");

        AddAddress addAddress1 = addAddressRepository.findAll().stream()
                .filter(addAddress -> addAddress.getCustomerId().equals(customerId) &&
                        addAddress.get_id().equals(addressId))
                .findFirst().orElse(null);

        addAddressRepository.delete(addAddress1);

        return new OutLaw("address deleted");
    }

    public String deleteAll(){
        addAddressRepository.deleteAll();
        return "All address records delete";
    }

    private AddAddress mkAddress(HashMap<String, String> body) {
        AddAddress addAddress = new AddAddress(
                body.get("customerId"),
                body.get("location"),
                body.get("buildingNo"),
                body.get("landmark"),
                body.get("nickName"),
                body.get("tag")
        );
        addAddress.setDateCreated(timeConverter());
        return addAddress;
    }

    private AddAddress compareAddress(AddAddress oldAddress, AddAddress newAddress){

        oldAddress.setLocation(newAddress.getLocation() != null && !newAddress.getLocation().isEmpty() ?
                newAddress.getLocation() : oldAddress.getLocation());

        oldAddress.setBuildingNo(newAddress.getBuildingNo() != null && !newAddress.getBuildingNo().isEmpty() ?
                newAddress.getBuildingNo() : oldAddress.getBuildingNo());

        oldAddress.setLandmark(newAddress.getLandmark() != null && !newAddress.getLandmark().isEmpty() ?
                newAddress.getLandmark() : oldAddress.getLandmark());

        oldAddress.setNickName(newAddress.getNickName() != null && !newAddress.getNickName().isEmpty() ?
                newAddress.getNickName() : oldAddress.getNickName());

        oldAddress.setTag(newAddress.getTag() != null && !newAddress.getTag().isEmpty() ?
                newAddress.getTag() : oldAddress.getTag());

        oldAddress.setDateModified(timeConverter());

        return oldAddress;
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


}
