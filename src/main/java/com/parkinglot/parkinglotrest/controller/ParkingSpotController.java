package com.parkinglot.parkinglotrest.controller;

import com.parkinglot.parkinglotrest.entity.ParkingSpot;
import com.parkinglot.parkinglotrest.entity.request.AddParkingSpotRequest;
import com.parkinglot.parkinglotrest.repository.ParkingSpotRepository;
import com.parkinglot.parkinglotrest.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController(value = "parkingSpot")
public class ParkingSpotController {

    private ParkingSpotRepository parkingSpotRepository;

    @Autowired
    public ParkingSpotController(ParkingSpotRepository parkingSpotRepository){
        this.parkingSpotRepository = parkingSpotRepository;
    }

    @RequestMapping(value = {"/spot"}, method = RequestMethod.GET)
    public List<ParkingSpot> listParkingSpots(){
        return parkingSpotRepository.findAll();
    }

    @RequestMapping(value = {"/spot"}, method = RequestMethod.POST)
    public ParkingSpot createParkingSpot(@RequestBody AddParkingSpotRequest addParkingSpotRequest){
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setAvailable(true);
        parkingSpot.setLevel(addParkingSpotRequest.getLevel());
        parkingSpot.setSize(addParkingSpotRequest.getSize());
        parkingSpot.setVehicleNumber(null);

        parkingSpotRepository.save(parkingSpot);
        return parkingSpot;
    }


//    @RequestMapping(method = RequestMethod.GET)
//    public ParkingSpot getParkingSpotById(@RequestParam String id){
//
//        return parkingSpotRepository.findById(id).get();
//    }
}
