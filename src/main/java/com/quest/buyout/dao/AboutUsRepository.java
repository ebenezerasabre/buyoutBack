package com.quest.buyout.dao;

import com.quest.buyout.model.AboutUs;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutUsRepository extends MongoRepository<AboutUs, String> {
}
