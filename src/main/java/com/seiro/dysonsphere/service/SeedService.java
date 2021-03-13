package com.seiro.dysonsphere.service;

import com.seiro.dysonsphere.dao.SeedDAO;
import com.seiro.dysonsphere.pojo.Seed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeedService {
    @Autowired
    SeedDAO seedDAO;
    @Autowired
    MongoTemplate mongoTemplate;

    public List<Seed> list() {
        return seedDAO.findAll();
    }

    public void addOrUpdate(Seed seed) {
        seedDAO.insert(seed);
    }

    public void deleteById(String id) {
        seedDAO.deleteById(id);
    }
}
