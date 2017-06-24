package com.apo.model.desk.dao;

import com.apo.model.desk.Desk;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 24/06/2017.
 */
public interface DeskDAO {
    void updateDesk(Desk desk);
    Desk getDesk();
}
