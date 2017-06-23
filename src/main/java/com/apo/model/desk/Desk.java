package com.apo.model.desk;


import com.apo.util.ByteArraySerializer;
import com.apo.error.InvalidCoordinatesException;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Calendar;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 05/06/2017.
 */
@Document(collection = Desk.COLLECTION)
public class Desk {
    static final String COLLECTION = "desk";

    @Id
    private String id;

    public static final int DESK_SIZE = 100;

    /**
     * offset = x + y * 100;
     */
    private byte[] field = new byte[DESK_SIZE * DESK_SIZE];
    private long timestamp = Calendar.getInstance().getTimeInMillis();

    public synchronized void setPoint(int x, int y, byte color) {
        if (x < 0 || x >= 100 || y < 0 || y >= 100) {
            throw new InvalidCoordinatesException();
        }
        field[x + 100 * y] = color;
        updateTimestamp();
    }

    public synchronized Byte getPoint(int x, int y) {
        if (x < 0 || x >= 100 || y < 0 || y >= 100) {
            throw new InvalidCoordinatesException();
        }
        return field[x + 100 * y];
    }

    @JsonSerialize(using = ByteArraySerializer.class)
    public byte[] getField() {
        return field;
    }

    public void setField(byte[] field) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
