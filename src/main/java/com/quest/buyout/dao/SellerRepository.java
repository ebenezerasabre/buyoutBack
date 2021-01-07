package com.quest.buyout.dao;

import com.quest.buyout.model.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends MongoRepository<Seller, String> {
}
