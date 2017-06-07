package com.apo.model;


import com.apo.error.InvalidCoordinatesException;

import java.util.Calendar;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 05/06/2017.
 */
public class Desk {
    public static final int DESK_SIZE = 100;
    //offset = x + y * 100;
    private short[] field = new short[DESK_SIZE * DESK_SIZE];
    private long timestamp = Calendar.getInstance().getTimeInMillis();

    public synchronized void setPoint(int x, int y, short color) {
        if (x < 0 || x >= 100 || y < 0 || y >= 100) {
            throw new InvalidCoordinatesException();
        }
        field[x + 100 * y] = color;
        updateTimestamp();
    }

    public synchronized short getPoint(int x, int y) {
        if (x < 0 || x >= 100 || y < 0 || y >= 100) {
            throw new InvalidCoordinatesException();
        }
        return field[x + 100 * y];
    }

    public short[] getField() {
        return field;
    }

    public void setField(short[] field) {
        this.field = field;
        updateTimestamp();
    }

    private void updateTimestamp() {
        timestamp = Calendar.getInstance().getTimeInMillis();
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
