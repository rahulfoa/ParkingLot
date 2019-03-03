package com.parkinglot.parkinglotrest.entity.request;

import com.parkinglot.parkinglotrest.entity.Size;

public class AddVehicleRequest {

    private String licensePlate;
    private String colour;

    private Size size;

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

}
