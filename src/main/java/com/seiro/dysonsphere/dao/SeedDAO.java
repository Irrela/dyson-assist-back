package com.seiro.dysonsphere.dao;

import com.seiro.dysonsphere.pojo.Seed;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SeedDAO extends MongoRepository<Seed, String> {
}
