package com.parkinglot.parkinglotrest.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalTime;


@Entity
@Table(name = "parking_spot")
public class ParkingSpot {

    @Id
    @GeneratedValue
    private int id;

    private Size size;

    private int level;

    private boolean available;

    private String vehicleNumber;

    private LocalTime time;

    public int getId() {
        return id;
    }

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

    public boolean isAvailable(){
        return this.available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
