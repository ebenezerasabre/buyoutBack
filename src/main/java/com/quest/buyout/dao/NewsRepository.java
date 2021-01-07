package com.quest.buyout.dao;

import com.quest.buyout.model.News;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends MongoRepository<News, String> {


}
