package com.quest.buyout.dao;

import com.quest.buyout.model.AddAddress;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddAddressRepository extends MongoRepository<AddAddress, String> {


}
