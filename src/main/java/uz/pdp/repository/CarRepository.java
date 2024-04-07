package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

       // find the carData  by regionId
      List<Car> findAllByRegionId(Integer region_id);


      // native query, that finds by UserId  all CarData
      @Query(value = "select  * from car " +
              "join  users_car_list " +
              "on car.id=users_car_list.car_list_id " +
              "where users_id=:userId", nativeQuery = true)

      List<Car> getCarsByUserIdNative(Integer userId);

}
