package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.District;
import uz.pdp.playload.DistrictDto;
import uz.pdp.entity.Region;
import uz.pdp.repository.DistrictRepository;
import uz.pdp.repository.RegionRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/district")
public class DistrictController {

    @Autowired
    RegionRepository  regionRepository;

    @Autowired
    DistrictRepository districtRepository;

    @PostMapping
    public String addDistrict(@RequestBody DistrictDto districtDto) {

        District district=new District();
        district.setName(districtDto.getName());

        Optional<Region> optionalRegion = regionRepository.findById(districtDto.getRegionId());
        if (!optionalRegion.isPresent()){
            return "Region not found";
        } else {
            district.setRegion(optionalRegion.get());
            districtRepository.save(district);
            return "District saved";
        }
    }

    @GetMapping
    public List<District> getDistricts(){
        return districtRepository.findAll();
    }


}
