package com.quest.buyout.Controllers;

import com.quest.buyout.model.OutLaw;
import com.quest.buyout.model.ThirdParty;
import com.quest.buyout.service.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class ThirdPartyController {

    @Autowired
    ThirdPartyService thirdPartyService;

    // TODO FIND

    // find all thirdParty records
    @GetMapping("/v1/thirdParty")
    public List<ThirdParty> index(){
        return thirdPartyService.index();
    }

    // find thirdParty by id
    @PostMapping("/v1/thirdParty/id")
    public ThirdParty findThirdPartyRecordById(@RequestBody HashMap<String, String> body){
        return thirdPartyService.findThirdPartyRecordById(body);
    }

    // find thirdParties by ids
    @PostMapping("/v1/thirdParty/ids")
    public List<ThirdParty> findThirdPartyRecordsByIds(@RequestBody List<HashMap<String, String>> body){
        return thirdPartyService.findThirdPartyRecordsByIds(body);
    }



    // TODO CREATE

    // create a thirdParty record
    @PostMapping("/v1/thirdParty")
    public ThirdParty createThirdPartyRecord(@RequestBody HashMap<String, String> body){
        return thirdPartyService.createThirdPartyRecord(body);
    }

    // create multiple thirdParty records
    @PostMapping("/v1/thirdParties")
    public List<ThirdParty> createThirdPartyRecords(@RequestBody List<HashMap<String, String>> body){
        return thirdPartyService.createThirdPartyRecords(body);
    }

    // TODO UPDATE

    // update  a thirdParty record
    @PostMapping("/v1/thirdParty/update")
    public ThirdParty updateThirdPartyRecord(@RequestBody HashMap<String, String> body){
        return thirdPartyService.updateThirdPartyRecord(body);
    }

    // update multiple thirdParty records
    @PostMapping("/v1/thirdParty/updates")
    public List<ThirdParty> updateThirdPartyRecords(@RequestBody List<HashMap<String, String>> body){
        return thirdPartyService.updateThirdPartyRecords(body);
    }


    // TODO DELETE

    // delete a thirdParty record
    @PostMapping("/v1/thirdParty/delete")
    public OutLaw deleteThirdPartyRecord(@RequestBody HashMap<String, String> body){
        return thirdPartyService.deleteThirdPartyRecord(body);
    }

    // delete multiple thirdParty records
    @PostMapping("/v1/thirdParty/deletes")
    public String[] deleteThirdPartyRecords(@RequestBody List<HashMap<String, String>> body){
        return thirdPartyService.deleteThirdPartyRecords(body);
    }

    // delete all thirdParty records
    // to be used only in development builds
    @GetMapping("/v1/thirdParty/del_all_admin")
    public String deleteAllThirdPartyRecords(){
        return thirdPartyService.deleteAllThirdPartyRecords();
    }


}
