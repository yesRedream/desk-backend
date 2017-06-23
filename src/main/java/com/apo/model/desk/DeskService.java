package com.apo.model.desk;

import org.springframework.stereotype.Service;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 24/06/2017.
 */
public interface DeskService {
   Desk getDesk();
   void updateDesk(Desk desk);
}
