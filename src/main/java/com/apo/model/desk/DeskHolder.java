package com.apo.model.desk;

import com.apo.model.desk.service.DeskService;
import com.apo.mongo.MongoProperty;
import com.apo.util.PropertyManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 24/06/2017.
 */

/**
 * Holder of Desk object in memory. Temporary used for cache.
 * REMOVE or replace with Redis
 */
public class DeskHolder {

    @Autowired
    private DeskService service;
    @Autowired
    private PropertyManager propertyManager;

    private Desk desk = null;

    public synchronized Desk getDesk() {
        if (desk == null) {
            desk = service.getDesk();
            if (desk == null) {
                desk = Desk.createInstanceWithId(propertyManager.getProperty(MongoProperty.DESK_ID));
                service.updateDesk(desk);
            }
        }
        return desk;
    }
}
