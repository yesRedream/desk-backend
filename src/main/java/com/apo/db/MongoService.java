package com.apo.db;

import com.apo.model.Desk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 07/06/2017.
 */
@Service
public class MongoService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Desk findDesk() {
        Desk desk = mongoTemplate.findOne(Query.query(Criteria.where("id").is(Constants.DESK_MONGO_ID)),
                Desk.class, Constants.COLLECTION_DESK);
        if (desk == null) {
            desk = new Desk();
        }
        return desk;
    }

    public void updateDesk(Desk desk) {
        desk.setId(Constants.DESK_MONGO_ID);
        mongoTemplate.save(desk, Constants.COLLECTION_DESK);
    }
}
