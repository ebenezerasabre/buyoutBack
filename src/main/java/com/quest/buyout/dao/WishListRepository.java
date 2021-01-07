package com.quest.buyout.dao;

import com.quest.buyout.model.WishList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends MongoRepository<WishList, String> {
}
