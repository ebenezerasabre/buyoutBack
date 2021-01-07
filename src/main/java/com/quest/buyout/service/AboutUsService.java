package com.quest.buyout.service;

import com.quest.buyout.dao.AboutUsRepository;
import com.quest.buyout.model.AboutUs;
import com.quest.buyout.model.OutLaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AboutUsService {


    @Autowired
    AboutUsRepository aboutUsRepository;

    public List<AboutUs> index(){
        return aboutUsRepository.findAll();
    }

    public AboutUs findById(HashMap<String, String> body){
        return aboutUsRepository.findById(body.get("_id")).orElse(null);
    }

    public OutLaw createOne(HashMap<String, String> body){
        aboutUsRepository.save(mkAboutUs(body));
        return new OutLaw("AboutUs record created");
    }

    public String[] createAll(List<HashMap<String, String>> newBody){
        for(HashMap<String, String> body : newBody){
            aboutUsRepository.save(mkAboutUs(body));
        }
        return new String[]{"AboutUs records created"};
    }

    public OutLaw updateById(HashMap<String, String> body){
        updater(body);
        return new OutLaw("AboutUs record updated");
    }

    public String[] updateAll(List<HashMap<String, String>> newBody){
        for(HashMap<String, String> body : newBody){
            updater(body);
        }
        return new String[]{"AboutUs records updated"};
    }


    public OutLaw deleteById(HashMap<String, String> body){
        deleter(body);
        return new OutLaw("AboutUs record deleted");
    }


    public String[] deleteByIds(List<HashMap<String, String>> newBody){
        for(HashMap<String, String> body : newBody){
            deleter(body);
        }
        return new String[]{"AboutUs records deleted"};
    }

    public OutLaw deleteAll(){
        aboutUsRepository.deleteAll();
        return new OutLaw("All AboutUs records deleted");
    }

    private AboutUs mkAboutUs(HashMap<String, String> body){
        return new AboutUs(body.get("title"), body.get("msg"));
    }

    private void deleter(HashMap<String, String > body){
        aboutUsRepository.deleteById(body.get("_id"));
    }

    private void updater(HashMap<String, String> body){
        // get old aboutUs
        AboutUs oldAboutUs = aboutUsRepository.findById(body.get("_id")).orElse(null);

        if(oldAboutUs != null){
            AboutUs newAboutUs = mkAboutUs(body);   // make new aboutUs
            AboutUs updatedOldAboutUs = compare(oldAboutUs, newAboutUs); // compare old to new
            aboutUsRepository.save(updatedOldAboutUs); // save updated old aboutUs
        }
    }

    private AboutUs compare(AboutUs oldAboutUs, AboutUs newAboutUs){
        oldAboutUs.setTitle(newAboutUs.getTitle() != null && !newAboutUs.getTitle().isEmpty() ?
                newAboutUs.getTitle() : oldAboutUs.getTitle());

        oldAboutUs.setMsg(newAboutUs.getMsg() != null && !newAboutUs.getMsg().isEmpty() ?
                newAboutUs.getMsg() : oldAboutUs.getMsg());
        return oldAboutUs;
    }

}
