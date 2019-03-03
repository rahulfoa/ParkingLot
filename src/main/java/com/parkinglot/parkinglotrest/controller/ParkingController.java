package com.parkinglot.parkinglotrest.controller;

import com.parkinglot.parkinglotrest.entity.ParkingSpot;
import com.parkinglot.parkinglotrest.entity.Size;
import com.parkinglot.parkinglotrest.entity.Vehicle;
import com.parkinglot.parkinglotrest.repository.ParkingSpotRepository;
import com.parkinglot.parkinglotrest.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController(value = "park")
public class ParkingController {
    private ParkingSpotRepository parkingSpotRepository;
    private VehicleRepository vehicleRepository;

    @Autowired
    public ParkingController(ParkingSpotRepository parkingSpotRepository, VehicleRepository vehicleRepository){
        this.parkingSpotRepository = parkingSpotRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @RequestMapping(value = "/park/{LicenseNumber}", method = RequestMethod.POST)
    public ResponseEntity parkVehicle(@PathVariable("LicenseNumber") String licenseNumber){
        List<ParkingSpot> spots = parkingSpotRepository.findAll();

        if(vehicleRepository.findById(licenseNumber)==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle with given Id not found");
        }
        Vehicle v = vehicleRepository.findById(licenseNumber).get();
        Size size = v.getSize();
        List<Size> spotSize = getSizeOfLot(size);

        boolean parked = false;
        for(ParkingSpot spot: spots){
            if(spot.isAvailable() && spotSize.contains(spot.getSize())){
                v.setParked(true);
                v.setParkingSpotId(spot.getId());
                spot.setAvailable(false);
                spot.setVehicleNumber(v.getLicensePlate());
                spot.setTime(LocalTime.now());
                parked = true;
                parkingSpotRepository.save(spot);
                break;
            }
        }

        vehicleRepository.save(v);
        if(parked==false)
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("No Suitable parking lot available");
        return ResponseEntity.status(HttpStatus.OK).body("Vehicle Parked :)");
    }


    @RequestMapping(value = "/unpark/{LicenseNumber}", method = RequestMethod.POST)
    public ResponseEntity unparkVehicle(@PathVariable("LicenseNumber") String licenseNumber){
        if(vehicleRepository.findById(licenseNumber)==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle with given Id not found");
        }
        Vehicle v = vehicleRepository.findById(licenseNumber).get();
        if(v.isParked()==false)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vehicle Not Parked");
        ParkingSpot spot = parkingSpotRepository.findById(v.getParkingSpotId()).get();

        v.setParkingSpotId(-1);
        v.setParked(false);
        spot.setTime(null);
        spot.setVehicleNumber(null);
        spot.setAvailable(true);
        parkingSpotRepository.save(spot);
        vehicleRepository.save(v);

        return ResponseEntity.status(HttpStatus.OK).body("Vehicle unparked successfully");
    }

    @RequestMapping(value = "/duration/{entryTime}", method = RequestMethod.GET)
    public List<Vehicle> findVehiclesParkedInDuration(@PathVariable("entryTime") String strTime){
        LocalTime time = LocalTime.parse(strTime);
        List<Vehicle> temp = vehicleRepository.findAll();
        List<Vehicle> res = new ArrayList<>();
        for(Vehicle v : temp){
            if(v.isParked()==true){
                LocalTime parkTime = parkingSpotRepository.findById(v.getParkingSpotId()).get().getTime();
                if(time.isAfter(parkTime))
                    res.add(v);
            }
        }
        return res;
    }

    public List<Size> getSizeOfLot(Size size){
        List<Size> res = new ArrayList<>();
        switch(size){
            case small:
                res.add(Size.small);
            case medium:
                res.add(Size.medium);
            case large:
                res.add(Size.large);

        }

        return res;
    }
}
