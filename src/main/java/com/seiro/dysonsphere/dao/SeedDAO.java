package com.seiro.dysonsphere.dao;

import com.seiro.dysonsphere.pojo.Seed;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface SeedDAO extends MongoRepository<Seed, String> {
}
