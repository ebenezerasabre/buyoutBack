package com.quest.buyout.Controllers;

import com.quest.buyout.model.HelpAndTutorials;
import com.quest.buyout.model.OutLaw;
import com.quest.buyout.service.HelpAndTutorialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class HelpAndTutorialsController {

    @Autowired
    HelpAndTutorialsService helpAndTutorialsService;

    // TODO FIND

    // find all helpAndTutorials
    @GetMapping("/v1/help")
    public List<HelpAndTutorials> getHelp(){
        return helpAndTutorialsService.getHelp();
    }

    // TODO COUNT

    // count all helpAndTutorials
    @GetMapping("/v1/help/count")
    public OutLaw countHelp(){
        return helpAndTutorialsService.countHelp();
    }


    // TODO CREATE

    // create a helpAndTutorial record
    @PostMapping("/v1/help")
    public HelpAndTutorials createHelpRecord(@RequestBody HashMap<String, String> body){
        return helpAndTutorialsService.createHelpRecord(body);
    }

    // create multiple help records
    @PostMapping("/v1/helps")
    public List<HelpAndTutorials> createHelpRecords(@RequestBody List<HashMap<String, String>> body){
        return helpAndTutorialsService.createHelpRecords(body);
    }


    // TODO UPDATE

    // update a help record
    @PostMapping("/v1/help/update")
    public HelpAndTutorials update(@RequestBody HashMap<String, String> body){
        return helpAndTutorialsService.update(body);
    }

    @PostMapping("/v1/help/updates")
    // update multiple help records
    public List<HelpAndTutorials> updates(@RequestBody List<HashMap<String, String>> body){
        return helpAndTutorialsService.updates(body);
    }


    // TODO DELETE

    // delete help record
    @PostMapping("/v1/help/delete")
    public OutLaw deleteHelpRecord(@RequestBody HashMap<String, String> body){
        return helpAndTutorialsService.deleteHelpRecord(body);
    }

    // delete multiple help records
    @PostMapping("/v1/help/deletes")
    public OutLaw deleteHelpRecords(@RequestBody List<HashMap<String, String>> body){
        return helpAndTutorialsService.deleteHelpRecords(body);
    }


    // delete all help records
    // caution ** not to be used in production builds
    @GetMapping("/v1/help/del_all_admin")
    public OutLaw deleteAllHelps(){
        return helpAndTutorialsService.deleteAllHelp();
    }


}
