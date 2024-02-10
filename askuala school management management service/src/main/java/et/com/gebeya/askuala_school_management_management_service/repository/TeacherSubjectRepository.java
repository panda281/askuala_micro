package et.com.gebeya.askuala_school_management_management_service.repository;

import et.com.gebeya.askuala_school_management_management_service.model.TeacherSubject;
import et.com.gebeya.askuala_school_management_management_service.model.TeacherSubjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherSubjectRepository extends JpaRepository<TeacherSubject, TeacherSubjectId>, JpaSpecificationExecutor<TeacherSubject> {
}
