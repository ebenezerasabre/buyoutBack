package com.quest.buyout.service;

import com.quest.buyout.dao.NewsRepository;
import com.quest.buyout.model.News;
import com.quest.buyout.model.OutLaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class NewsService {

    @Autowired
    NewsRepository newsRepository;


    public List<News> getNews(){
        return newsRepository.findAll();
    }

    public String countNews(){
        return String.valueOf(newsRepository.count());
    }

    public OutLaw createNewsRecord(HashMap<String, String> body){
        newsRepository.save(mkNews(body));
        return new OutLaw("News created");
    }

    public String[] createMultipleNewsRecord(List<HashMap<String, String>> newBody){
        for(HashMap<String, String> body : newBody){
            newsRepository.save(mkNews(body));
        }
        return new String[]{"News records created"};
    }

    public OutLaw updateNewsById(HashMap<String, String> body){
        updater(body);
        return new OutLaw("News record update");
    }

    public OutLaw updateMultipleNewsByIds(List<HashMap<String, String>> newBody){
        for(HashMap<String, String> body : newBody){
            updater(body);
        }
        return new OutLaw("News record update");
    }


    public OutLaw thumbs(HashMap<String, String> body){

        String sub = body.get("sub");
        String subId = body.get("subId");
        String msg = "Couldn't update thumbs";

        News news = newsRepository.findById(subId).orElse(null);

        if(news != null){
            String strU = news.getThumbs()[0];
            String strD = news.getThumbs()[1];

            switch (sub){
                case "thumbsUp":
                    int tU = strU.isEmpty() ? 0 : Integer.parseInt(strU);
                    tU += 1;
                    strU = String.valueOf(tU);
                    String[] thumbsUp = {strU, strD};
                    news.setThumbs(thumbsUp);
                    msg = strU;
                    break;
                case "thumbsDown":
                    int tD = strD.isEmpty() ? 0 : Integer.parseInt(strD);
                    tD += 1;
                    strD = String.valueOf(tD);
                    String[] thumbsDown = {strU, strD};
                    news.setThumbs(thumbsDown);
                    msg = strD;
                    break;
                default:
                    // do nothing
                    break;
            }
            newsRepository.save(news);
        }
        return new OutLaw(msg);
    }



    public OutLaw deleteNewsById(HashMap<String, String> body){
        newsRepository.deleteById(body.get("_id"));
        return new OutLaw("News record deleted");
    }


    public OutLaw deleteNewsByIds(List<HashMap<String, String>> newBody){

        for(HashMap<String, String> body : newBody){
            newsRepository.deleteById(body.get("_id"));
        }
        return new OutLaw("News records deleted");
    }


    public OutLaw deleteAllNews(){
        newsRepository.deleteAll();
        return new OutLaw("All news records deleted");
    }





    private News mkNews(HashMap<String, String> body){
        News news = new News(
                body.get("source"),
                body.get("msg"),
                body.get("img")
        );
        news.setDate(timeConverter());
        return news;
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

    private void updater(HashMap<String, String> body){
        // get old news record
        News oldNews = newsRepository.findById(body.get("_id")).orElse(null);
        if(oldNews != null){
            // make new news record
            News newNews = mkNews(body);
            // compare old to new
            News updateOldNews = compareNews(oldNews, newNews);
            // save updated news record
            newsRepository.save(updateOldNews);
        }

    }

    private News compareNews(News oldNews, News newNews){
        oldNews.setSource(newNews.getSource() != null && !newNews.getSource().isEmpty() ?
                newNews.getSource() : oldNews.getSource());
        oldNews.setMsg(newNews.getMsg() != null && !newNews.getMsg().isEmpty() ?
                newNews.getMsg() : oldNews.getMsg());
        oldNews.setImg(newNews.getImg() != null && !newNews.getImg().isEmpty() ?
                newNews.getImg() : oldNews.getImg());
        return oldNews;
    }



}
