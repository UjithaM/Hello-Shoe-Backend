package software.ujithamigara.helloShoesSystem.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import software.ujithamigara.helloShoesSystem.entity.CustomerEntity;
@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity,String> {
}
