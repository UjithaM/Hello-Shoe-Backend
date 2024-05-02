package software.ujithamigara.helloShoesSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import software.ujithamigara.helloShoesSystem.entity.EmployeeEntity;
@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeEntity,String> {
}
