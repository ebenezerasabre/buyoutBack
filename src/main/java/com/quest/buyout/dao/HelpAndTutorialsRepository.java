package com.quest.buyout.dao;

import com.quest.buyout.model.HelpAndTutorials;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelpAndTutorialsRepository extends MongoRepository<HelpAndTutorials, String> {
}

