package et.com.gebeya.askuala_school_management_management_service.repository;


import et.com.gebeya.askuala_school_management_management_service.model.GradeSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;


@Repository
public interface GradeSectionRepository extends JpaRepository<GradeSection, Integer>, JpaSpecificationExecutor<GradeSection> {

    Set<GradeSection> findAllByIdIn(Set<Integer> ids);

    @Query(nativeQuery = true,value = "select grade_section.grade,grade_section.section from grade_section inner join teacher_subject ts on grade_section.id = ts.grade_section_id inner join teacher t on t.id = ts.teacher_id and teacher_id = :id and grade_section.is_active = true")
    List<Object[]> findGradeSectionByTeacherId(@PathVariable("id") Integer id);


    @Query(nativeQuery = true,value = "select grade_section.grade,grade_section.section from grade_section inner join teacher_subject ts on grade_section.id = ts.grade_section_id inner join student s on grade_section.id = s.grade_section_id and s.id = :id")
    List<Object[]> findGradeSectionByStudentId(@PathVariable("id") Integer id);



}
