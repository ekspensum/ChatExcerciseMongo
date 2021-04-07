package pl.aticode.dal.postgres;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pl.aticode.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	@Query("Select employee from Employee employee where employee.user.username = ?1")
	Employee findByUserUsername(String username);
}
