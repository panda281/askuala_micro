package et.com.gebeya.askuala_school_management_management_service.repository;

import et.com.gebeya.askuala_school_management_management_service.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer>, JpaSpecificationExecutor<Subject> {
    Set<Subject> findAllByIdIn(Set<Integer> ids);

    @Query(nativeQuery = true,value = "select DISTINCT subject.name from subject inner join teacher_subject ts on subject.id = ts.subject_id join teacher t on t.id = ts.teacher_id and teacher_id = :id")
//    Map<String,String> getTeacherSubject(@PathVariable("id") Integer id);
    List<Object[]> getTeacherSubject(@PathVariable("id") Integer id);

    @Query(nativeQuery = true, value = "select DISTINCT subject.name from subject inner join grade_section_subject gss on subject.id = gss.subject_id inner join student s on gss.grade_section_id = s.grade_section_id and s.id = :id")
    List<Object[]> getStudentSubject(@PathVariable("id") Integer id);
}
