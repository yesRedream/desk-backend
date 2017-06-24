package com.apo.model.desk.service;

import com.apo.model.desk.Desk;
import com.apo.model.desk.dao.DeskDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 24/06/2017.
 */
@Service
public class DeskServiceImpl implements DeskService{
    @Autowired
    private DeskDAO dao;

    @Override
    public Desk getDesk() {
        return dao.getDesk();
    }

    @Override
    public void updateDesk(Desk desk) {
        dao.updateDesk(desk);
    }
}
