package et.com.gebeya.askuala_school_management_management_service.repository.specifications;


import et.com.gebeya.askuala_school_management_management_service.model.Teacher;
import et.com.gebeya.askuala_school_management_management_service.model.TeacherSubject;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class TeacherSubjectSpecifications {


    private static Join<TeacherSubject, Teacher> getTeacherTeacherSubject(Root<TeacherSubject> root) {
        return root.join("teacher");
    }


    public static Specification<TeacherSubject> getTeacherSubjects(Integer id) {
        return (root, query, criteriaBuilder) -> {
            Join<TeacherSubject, Teacher> teacher = getTeacherTeacherSubject(root);
            return criteriaBuilder.equal(teacher.get("id"),id);
        };
    }


}
