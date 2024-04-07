package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    boolean existsByCarListId(Integer car_list_id);

}
