package et.com.gebeya.askuala_school_management_management_service.repository.specifications;


import et.com.gebeya.askuala_school_management_management_service.enums.Grade;
import et.com.gebeya.askuala_school_management_management_service.enums.Section;
import et.com.gebeya.askuala_school_management_management_service.model.GradeSection;
import et.com.gebeya.askuala_school_management_management_service.model.Student;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecifications {

    public static Specification<Student> StudentByName(String name){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("firstName"),"%"+name+"%");
    }

    private static Join<Student, GradeSection> getStudentGradeSectionJoin(Root<Student> root) {
        return root.join("gradeSection");
    }
    public static Specification<Student> StudentByGradeSection(Grade grade, Section section) {
        return (root, query, criteriaBuilder) -> {
            Join<Student, GradeSection> studentGradeSection = getStudentGradeSectionJoin(root);
            return criteriaBuilder.and(
                    criteriaBuilder.equal(studentGradeSection.get("grade"), grade),
                    criteriaBuilder.equal(studentGradeSection.get("section"), section)
            );
        };
    }

    public static Specification<Student> getAllStudents(){
        return (((root, query, criteriaBuilder) -> criteriaBuilder.notEqual(root.get("isActive"),false)));
    }
}
