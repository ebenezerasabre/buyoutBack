package com.quest.buyout.service;

import com.quest.buyout.dao.ThirdPartyRepository;
import com.quest.buyout.model.OutLaw;
import com.quest.buyout.model.Seller;
import com.quest.buyout.model.ThirdParty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ThirdPartyService {

    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    public List<ThirdParty> index(){
        return thirdPartyRepository.findAll();
    }

    public ThirdParty findThirdPartyRecordById(HashMap<String, String> body){
        return thirdPartyRepository.findById(body.get("_id")).orElse(null);
    }

    public List<ThirdParty> findThirdPartyRecordsByIds(List<HashMap<String, String>> body){
        List<ThirdParty> list = new ArrayList<>();
        for (HashMap<String, String> hs : body){
            ThirdParty thirdParty = thirdPartyRepository.findById(hs.get("_id")).orElse(null);
            if(thirdParty != null){
                list.add(thirdParty);
            }
        }
        return list;
    }

    public ThirdParty createThirdPartyRecord(HashMap<String, String> body){
        ThirdParty tp = mkThirdParty(body);
        if(tp != null){
            return thirdPartyRepository.save(tp);
        }
        return new ThirdParty();
    }

    public List<ThirdParty> createThirdPartyRecords(List<HashMap<String, String>> body){
        List<ThirdParty> list = new ArrayList<>();
        for(HashMap<String, String> hs : body){
            ThirdParty tp = mkThirdParty(hs);
            if(tp != null){
                list.add(thirdPartyRepository.save(tp));
            }
        }
        return list;
    }

    public ThirdParty updateThirdPartyRecord(HashMap<String, String> body){
        return thirdPartyRepository.save(updater(body));
    }

    public List<ThirdParty> updateThirdPartyRecords(List<HashMap<String, String>> body){
        List<ThirdParty> list = new ArrayList<>();
        for(HashMap<String, String> hs : body){
            list.add(thirdPartyRepository.save(updater(hs)));
        }
        return list;
    }

    public OutLaw deleteThirdPartyRecord(HashMap<String, String> body){
        thirdPartyRepository.deleteById(body.get("_id"));
        return new OutLaw("Third party record deleted");
    }

    public String[] deleteThirdPartyRecords(List<HashMap<String, String>> body){
        for(HashMap<String, String> hs : body){
            thirdPartyRepository.deleteById(hs.get("_id"));
        }
        return new String[]{"Third party records deleted"};
    }

    public String deleteAllThirdPartyRecords(){
        thirdPartyRepository.deleteAll();
        return "All third party records deleted";
    }

    private ThirdParty mkThirdParty(HashMap<String, String> body){
        String companyName = body.get("companyName");
        if(null != companyName && !companyName.isEmpty()){
            return new ThirdParty(
                    body.get("companyName"),
                    body.get("percentageIncrease"),
                    timeConverter()
            );
        }
       return null;
    }

    private String timeConverter(){
        Date date = new Date();
        long millis = date.getTime();

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);

        return String.format("%s-%s-%S", day, month, year);
    }

    private ThirdParty updater(HashMap<String, String> body){
        String _id = body.get("_id");
        ThirdParty oldThirdParty = thirdPartyRepository.findById(_id).orElse(null);
        ThirdParty updatedOldThirdParty = new ThirdParty();

        if(oldThirdParty != null){
            ThirdParty newThirdParty = mkThirdParty(body);
            updatedOldThirdParty = compare(oldThirdParty, newThirdParty);
        }
        return updatedOldThirdParty;
    }

    private ThirdParty compare(ThirdParty oldObj, ThirdParty newObj){
        oldObj.setCompanyName(newObj.getCompanyName() != null && !newObj.getCompanyName().isEmpty() ?
                newObj.getCompanyName() : oldObj.getCompanyName());

        oldObj.setPercentageIncrease(newObj.getPercentageIncrease() != null && !newObj.getPercentageIncrease().isEmpty() ?
                newObj.getPercentageIncrease() : oldObj.getPercentageIncrease());
        return oldObj;
    }





}




