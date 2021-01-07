package com.quest.buyout.dao;

import com.quest.buyout.model.History;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends MongoRepository<History, String> {
}
