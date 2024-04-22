package software.ujithamigara.helloShoesSystem.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import software.ujithamigara.helloShoesSystem.entity.CustomerEntity;

public interface CustomerRepo extends JpaRepository<CustomerEntity,String> {
}
