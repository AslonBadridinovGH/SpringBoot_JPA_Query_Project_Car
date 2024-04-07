package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.playload.CarDto;
import uz.pdp.entity.Car;
import uz.pdp.entity.Region;
import uz.pdp.repository.CarRepository;
import uz.pdp.repository.RegionRepository;

import java.util.List;
import java.util.Optional;


@RequestMapping(value = "/car")
@RestController
public class CarController {

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    CarRepository carRepository;

    @PostMapping
    public String addCar(@RequestBody CarDto carDto) {

        Car car = new Car();
        car.setModel(carDto.getModel());
        car.setMadeYear(carDto.getMadeYear());
        car.setStateNumber(carDto.getStateNumber());
        car.setType(carDto.getType());

        Optional<Region> optionalRegion = regionRepository.findById(carDto.getRegionId());
        if (!optionalRegion.isPresent()){
            return "Region not found";
        } else {
            car.setRegion(optionalRegion.get());
            carRepository.save(car);
            return "Car saved";
        }
    }

    @GetMapping
    public List<Car> getCars(){
        return carRepository.findAll();
    }


    @GetMapping(value = "/byRegionId/{regionId}")
    public List<Car>getCarsByRegionId(@PathVariable Integer regionId){
        List<Car> allByRegionId = carRepository.findAllByRegionId(regionId);
        return allByRegionId;
    }


    @GetMapping(value = "/byUserId/{userId}")
    public List<Car>getCarsByUserId(@PathVariable Integer userId){
        List<Car> carsByUserIdNative = carRepository.getCarsByUserIdNative(userId);
        return carsByUserIdNative;
    }


}
