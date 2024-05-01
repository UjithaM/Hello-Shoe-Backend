package software.ujithamigara.helloShoesSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import software.ujithamigara.helloShoesSystem.entity.EmployeeEntity;

public interface EmployeeRepo extends JpaRepository<EmployeeEntity,String> {
}
