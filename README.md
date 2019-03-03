# ParkingLot

Steps:

1. Run 'mvn clean install'
2. Run Runner.java.


One the server is up.
Vehicles can be of 3 sizes(small,medium,large)

The project exposes following REST endpoints on localhost:8080:
1. /vehicles:   
    a. GET: List all vehicles.
    b. POST: Create a new vehicle. e.g. curl -X POST \
                                          http://localhost:8080/vehicles \
                                          -H 'Content-Type: application/json' \
                                          -d '{
                                        	"licensePlate":"ABC",
                                        	"colour": "red",
                                        	"size":"medium"
                                        }'
2. /vehicles/licenseNumber/{LicenseNumber}:
    a. GET: Find vehicle using License Number e.g. curl -X GET http://localhost:8080/vehicles/licenseNumber/ABC

3. /vehicles/colour/{colour}: 
    a. GET: List all vehicles of a Colour e.g. curl -X GET http://localhost:8080/vehicles/colour/red
    
4. /vehicles/size/{size}:
    a. GET: List all vehicles of a Size e.g. curl -X GET http://localhost:8080/vehicles/size/medium
    
5. /spot:
    a. GET: List all parking spots.
    b. POST: Create new parking spot. e.g. curl -X POST \
                                             http://localhost:8080/spot \
                                             -H 'Content-Type: application/json'
                                             -d '{
                                           
                                           	"level": "1",
                                           	"size":"small"
                                           }'
6. /park/{LicenseNumber}:
    a. POST: Park vehicle with given LicenceNumber to first available slot. e.g. curl -X POST http://localhost:8080/park/ABC
    
7. /unpark/{LicenceNumber}:
    a. POST: Unpark given vehicle. e.g. curl -X POST http://localhost:8080/park/ABC
    
9. /duration/{entryTime}:
    a. GET: List all the vehicles that are parked currently and entered before 'entryTime' in HH:MM:SS.
        e.g. curl -X GET http://localhost:8000/duration/23:21:00