package com.quest.buyout.dao;

import com.quest.buyout.model.ThirdParty;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdPartyRepository extends MongoRepository<ThirdParty, String> {
}
