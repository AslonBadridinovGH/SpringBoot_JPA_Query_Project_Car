package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region,Integer> {

     boolean existsByName(String name);

}
