package com.quest.buyout.Controllers;

import com.quest.buyout.model.AboutUs;
import com.quest.buyout.model.OutLaw;
import com.quest.buyout.service.AboutUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class AboutUsContoller {

    @Autowired
    AboutUsService aboutUsService;

    // TODO FIND

    // find all aboutUs
    @GetMapping("/v1/aboutUs")
    public List<AboutUs> index(){
        return aboutUsService.index();
    }

    // find aboutUs record by id
    @PostMapping("/v1/aboutUs/_id")
    public AboutUs findById(@RequestBody HashMap<String, String> body){
        return aboutUsService.findById(body);
    }


    // TODO CREATE

    // create an aboutUs record
    @PostMapping("/v1/aboutUs")
    public OutLaw createOne(@RequestBody HashMap<String, String> body){
        return aboutUsService.createOne(body);
    }

    // create multiple aboutUs records
    @PostMapping("/v1/aboutUss")
    public String[] createAll(@RequestBody List<HashMap<String, String>> newBody){
        return aboutUsService.createAll(newBody);
    }

    // TODO UPDATE

    // update a single record
    @PostMapping("/v1/aboutUs/update")
    public OutLaw updateById(@RequestBody HashMap<String, String> body){
        return aboutUsService.updateById(body);
    }

    // update multiple records
    @PostMapping("/v1/aboutUs/updates")
    public String[] updateAll(@RequestBody List<HashMap<String, String>> newBody){
        return aboutUsService.updateAll(newBody);
    }

    // TODO DELETE

    // delete a single record
    @PostMapping("/v1/aboutUs/delete")
    public OutLaw deleteById(@RequestBody HashMap<String, String> body){
        return aboutUsService.deleteById(body);
    }

    // delete multiple records
    @PostMapping("/v1/aboutUs/deletes")
    public String[] deleteByIds(@RequestBody List<HashMap<String, String>> newBody){
        return aboutUsService.deleteByIds(newBody);
    }

    // delete all records
    // only used in development builds
    @GetMapping("/v1/aboutUs/del_all_admin")
    public OutLaw deleteAll(){
        return aboutUsService.deleteAll();
    }


}
//and the sign said
//The words of the prophets are written on the subway walls and tournament halls