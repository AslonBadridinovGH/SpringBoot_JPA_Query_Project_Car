package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.Region;
import uz.pdp.repository.RegionRepository;

import java.util.List;

@RequestMapping(value = "/region")
@RestController
public class RegionController {

    @Autowired
    RegionRepository regionRepository;

    @PostMapping
    public String addCar(@RequestBody Region region) {
        boolean existsByName = regionRepository.existsByName(region.getName());
        if (existsByName) {
            return "Region already exist";
        } else {
            regionRepository.save(region);
            return "Region saved";
        }
    }

    @GetMapping
    public List<Region> getRegions(){
        return regionRepository.findAll();
    }

}
