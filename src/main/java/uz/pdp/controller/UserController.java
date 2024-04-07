package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.Address;
import uz.pdp.entity.Car;
import uz.pdp.entity.District;
import uz.pdp.entity.User;
import uz.pdp.repository.AddressRepository;
import uz.pdp.repository.CarRepository;
import uz.pdp.repository.DistrictRepository;
import uz.pdp.repository.UserRepository;
import uz.pdp.playload.UserDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequestMapping(value = "/user")
@RestController
public class UserController {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CarRepository carRepository;



    @PostMapping
    public String addUser(@RequestBody UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());

        Optional<District> optionalDistrict = districtRepository.findById(userDto.getDistrictId());
        if (!optionalDistrict.isPresent()) {
            return "District not found";
        }else {
            Address address = new Address();
            address.setHouseNumber(userDto.getHouseNumber());
            address.setStreet(userDto.getStreet());
            address.setDistrict(optionalDistrict.get());
            Address savedAddress = addressRepository.save(address);
            user.setAddress(savedAddress);
        }
        List<Car>carList=new ArrayList<>();

        for (Integer carId : userDto.getCarIds()) {
            boolean exists = userRepository.existsByCarListId(carId);
            if (exists) {
                return carId+" - CarId  already used";
            }
            Optional<Car> optionalCar = carRepository.findById(carId);
            if (!optionalCar.isPresent())
                return "Car not found";
            carList.add(optionalCar.get());
        }
        user.setCarList(carList);
        userRepository.save(user);

        return "User saved";
    }


    @GetMapping
    public List<User> getUsers(){
        return userRepository.findAll();
    }


}
