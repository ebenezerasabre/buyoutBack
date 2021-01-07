package com.quest.buyout.Controllers;

import com.quest.buyout.model.News;
import com.quest.buyout.model.OutLaw;
import com.quest.buyout.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class NewsController {

    @Autowired
    NewsService newsService;

    // TODO FIND

    // find all news
    @GetMapping("/v1/news")
    public List<News> getNews(){
        return newsService.getNews();
    }


    // TODO COUNT

    // count all news
    @GetMapping("/v1/news/count")
    public String countNews(){
        return newsService.countNews();
    }

    // TODO CREATE

    // create news record
    @PostMapping("/v1/news")
    public OutLaw createNewsRecord(@RequestBody HashMap<String, String> body){
        return newsService.createNewsRecord(body);
    }

    // create multiple news records
    @PostMapping("/v1/newss")
    public String[] createMultipleNewsRecord(@RequestBody List<HashMap<String, String>> newBody){
        return newsService.createMultipleNewsRecord(newBody);
    }

    // TODO UPDATE

    // update a news record
    @PostMapping("/v1/news/update")
    public OutLaw updateNewsById(@RequestBody HashMap<String, String> body){
        return newsService.updateNewsById(body);
    }


    // update multiple news records
    @PostMapping("/v1/news/updates")
    public OutLaw updateMultipleNewsByIds(@RequestBody List<HashMap<String, String>> newBody){
        return newsService.updateMultipleNewsByIds(newBody);
    }


    // TODO THUMBS UP, DOWN
    @PostMapping("/v1/news/thumbs")
    public OutLaw thumbs(@RequestBody HashMap<String, String> body){
        return newsService.thumbs(body);
    }


    // TODO DELETE

    // delete a news record
    @PostMapping("/v1/news/delete")
    public OutLaw deleteNewsById(@RequestBody HashMap<String, String> body){
       return newsService.deleteNewsById(body);
    }

    // delete multiple news records
    @PostMapping("/v1/news/deletes")
    public OutLaw deleteNewsByIds(@RequestBody List<HashMap<String, String>> newBody){
        return newsService.deleteNewsByIds(newBody);
    }

    // delete all news records
    @GetMapping("/v1/news/del_all_admin")
    public OutLaw deleteAllNews(){
        return newsService.deleteAllNews();
    }


}
