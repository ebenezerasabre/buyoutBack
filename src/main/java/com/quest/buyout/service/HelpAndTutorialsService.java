package com.quest.buyout.service;

import com.quest.buyout.dao.HelpAndTutorialsRepository;
import com.quest.buyout.model.HelpAndTutorials;
import com.quest.buyout.model.OutLaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class HelpAndTutorialsService {

    @Autowired
    HelpAndTutorialsRepository helpAndTutorialsRepository;

    public List<HelpAndTutorials> getHelp(){
        return helpAndTutorialsRepository.findAll();
    }

    public OutLaw countHelp(){
        return new OutLaw(String.valueOf(helpAndTutorialsRepository.count()));
    }

    public HelpAndTutorials createHelpRecord(HashMap<String, String> body){
        return helpAndTutorialsRepository.save(mkHelp(body));
    }

    public List<HelpAndTutorials> createHelpRecords(List<HashMap<String, String>> body){
        List<HelpAndTutorials> list = new ArrayList<>();

        for(HashMap<String, String> newBody : body){
            list.add(helpAndTutorialsRepository.save(mkHelp(newBody)));
        }
        return list;
    }

    public HelpAndTutorials update(HashMap<String, String> body){
        HelpAndTutorials helpAndTutorials = updater(body);
        if(helpAndTutorials != null){
            return helpAndTutorialsRepository.save(helpAndTutorials);
        }
        return null;
    }

    public List<HelpAndTutorials> updates(List<HashMap<String, String>> body){
        List<HelpAndTutorials> list = new ArrayList<>();
        for(HashMap<String, String> newBody : body){
            HelpAndTutorials helpAndTutorials = updater(newBody);
            if(helpAndTutorials != null){
                list.add(helpAndTutorialsRepository.save(helpAndTutorials));
            }
        }
        return  list;
    }


    public OutLaw deleteHelpRecord(HashMap<String, String> body){
        helpAndTutorialsRepository.deleteById(body.get("_id"));
        return new OutLaw("Help and tutorials record deleted");
    }

    public OutLaw deleteHelpRecords(List<HashMap<String, String>> body){
        for(HashMap<String, String> newBody : body){
            helpAndTutorialsRepository.deleteById(newBody.get("_id"));
        }
        return new OutLaw("Help and tutorial records deleted");
    }

    public OutLaw deleteAllHelp(){
        helpAndTutorialsRepository.deleteAll();
        return new OutLaw("All help and tutorial records deleted");
    }




    private HelpAndTutorials updater(HashMap<String, String> body){
        // find old object
        HelpAndTutorials oldHelp = helpAndTutorialsRepository.findById(body.get("_id")).orElse(null);
        if(oldHelp != null){
            // make object of the incoming properties
            HelpAndTutorials newHelp = mkHelp(body);
            // compare old with new
            return compareHelp(oldHelp, newHelp);
        }
        return null;
    }


    private HelpAndTutorials compareHelp(HelpAndTutorials oldHelp, HelpAndTutorials newHelp){
        oldHelp.setTitle(newHelp.getTitle() != null && !newHelp.getTitle().isEmpty() ?
                newHelp.getTitle() : oldHelp.getTitle());
        oldHelp.setMsg(newHelp.getMsg() != null && !newHelp.getMsg().isEmpty() ?
                newHelp.getMsg() : oldHelp.getMsg());
        oldHelp.setImg(newHelp.getImg() != null && !newHelp.getImg().isEmpty() ?
                newHelp.getImg() : oldHelp.getImg());
        return oldHelp;
    }

    private HelpAndTutorials mkHelp(HashMap<String, String> body){
       return new HelpAndTutorials(
                body.get("title"),
                body.get("msg"),
                body.get("img")
        );
    }

}
