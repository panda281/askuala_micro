package et.com.gebeya.askuala_school_management_management_service.repository;

import et.com.gebeya.askuala_school_management_management_service.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {

    Optional<Student> findByStudentId(String integer);

}
