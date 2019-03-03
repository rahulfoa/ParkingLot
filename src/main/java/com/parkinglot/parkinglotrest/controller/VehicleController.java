package com.parkinglot.parkinglotrest.controller;


import com.parkinglot.parkinglotrest.entity.Size;
import com.parkinglot.parkinglotrest.entity.Vehicle;
import com.parkinglot.parkinglotrest.entity.request.AddVehicleRequest;
import com.parkinglot.parkinglotrest.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController(value = "vehicle")
public class VehicleController {

    private VehicleRepository vehicleRepository;

    @Autowired
    public VehicleController(VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }

    @RequestMapping(value={"/vehicles"}, method = RequestMethod.GET)
    public List<Vehicle> listVehicles(){
        return vehicleRepository.findAll();
    }

    @RequestMapping(value={"/vehicles"}, method = RequestMethod.POST)
    public Vehicle addNewVehicle(@RequestBody AddVehicleRequest addVehicleRequest){
        for(Vehicle v: vehicleRepository.findAll()){
            if (v.getLicensePlate().equalsIgnoreCase(addVehicleRequest.getLicensePlate()))
                return null;

        }
        Vehicle vehicle = new Vehicle();
        vehicle.setColour(addVehicleRequest.getColour());
        vehicle.setLicensePlate(addVehicleRequest.getLicensePlate());
        vehicle.setSize(addVehicleRequest.getSize());
        vehicle.setParked(false);
        vehicle.setParkingSpotId(-1);

        vehicleRepository.save(vehicle);

        return vehicle;
    }

    @RequestMapping(value = {"/vehicles/licenseNumber/{LicenseNumber}"}, method = RequestMethod.GET)
    public Vehicle getVehicleByNo(@PathVariable("LicenseNumber") String licenceNumber){
        return vehicleRepository.findById(licenceNumber).get();
    }

    @RequestMapping(value = {"/vehicles/colour/{Colour}"}, method = RequestMethod.GET)
    public List<Vehicle> getVehicleByColour(@PathVariable("Colour") String colour){
        List<Vehicle> res = new ArrayList<>();
        List<Vehicle> temp = vehicleRepository.findAll();
        for(Vehicle v: temp){
            if(v.getColour().equalsIgnoreCase(colour))
                res.add(v);
        }

        return res;
    }

    @RequestMapping(value = {"/vehicles/size/{Size}"}, method = RequestMethod.GET)
    public List<Vehicle> getVehicleBySize(@PathVariable("Size") Size size){
        List<Vehicle> res = new ArrayList<>();
        List<Vehicle> temp = vehicleRepository.findAll();
        for(Vehicle v: temp){
            if(v.getSize() == size)
                res.add(v);
        }

        return res;
    }

}
