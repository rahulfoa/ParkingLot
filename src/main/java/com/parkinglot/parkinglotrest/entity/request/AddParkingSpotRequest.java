package com.parkinglot.parkinglotrest.entity.request;

import com.parkinglot.parkinglotrest.entity.Size;

public class AddParkingSpotRequest {

    private Size size;

    private int level;

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
