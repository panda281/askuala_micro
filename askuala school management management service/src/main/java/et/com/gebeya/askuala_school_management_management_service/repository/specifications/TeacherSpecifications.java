package et.com.gebeya.askuala_school_management_management_service.repository.specifications;


import et.com.gebeya.askuala_school_management_management_service.model.Teacher;
import org.springframework.data.jpa.domain.Specification;

public class TeacherSpecifications {

    public static Specification<Teacher> getTeacherByName(String name){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("first_name"),"%" + name + "%"));
    }

    public static Specification<Teacher> getAllTeachers()
    {
        return (((root, query, criteriaBuilder) -> criteriaBuilder.notEqual(root.get("isActive"),false)));
    }

    public static Specification<Teacher>getTeacherById(Integer id)
    {
        return (((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"),id)));
    }
}
