package com.apo.model.desk.dao;

import com.apo.model.desk.Desk;
import com.apo.util.PropertyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 24/06/2017.
 */
public class DeskDAOMongo implements DeskDAO{

    @Autowired
    private MongoOperations operations;
    @Autowired
    private PropertyManager propertyManager;

    @Override
    public void updateDesk(Desk desk) {
        operations.save(desk);
    }

    @Override
    public Desk getDesk() {
        return operations
                .findOne(Query.query(Criteria.where("_id")
                        .is(propertyManager.getProperty("db.mongo.desk.id"))), Desk.class);
    }
}
