package com.apo.model.desk.service;

import com.apo.model.desk.Desk;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 24/06/2017.
 */
public interface DeskService {
   Desk getDesk();
   void updateDesk(Desk desk);
}
