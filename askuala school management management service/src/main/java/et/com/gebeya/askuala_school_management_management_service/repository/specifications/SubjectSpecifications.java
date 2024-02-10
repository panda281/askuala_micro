package et.com.gebeya.askuala_school_management_management_service.repository.specifications;

import et.com.gebeya.askuala_school_management_management_service.model.Subject;
import org.springframework.data.jpa.domain.Specification;

public class SubjectSpecifications {
    public static Specification<Subject> getByName(String name){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"),name));
    }

    public static Specification<Subject> getById(int id){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"),id));
    }

    public static Specification<Subject> getAllSubject()
    {
        return (((root, query, criteriaBuilder) -> criteriaBuilder.notEqual(root.get("isActive"),false)));
    }

    public static Specification<Subject> hasTeacherId(Long teacherId) {
        return (root, query, criteriaBuilder) -> {
            query.distinct(true); // Ensures distinct results
            return criteriaBuilder.isMember(teacherId, root.get("teachers").get("id"));
        };
    }
}
