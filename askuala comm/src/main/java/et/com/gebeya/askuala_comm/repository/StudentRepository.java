package et.com.gebeya.askuala_comm.repository;

import et.com.gebeya.askuala_comm.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
}
