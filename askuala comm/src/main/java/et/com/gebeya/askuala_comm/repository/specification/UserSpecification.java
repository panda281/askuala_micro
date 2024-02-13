package et.com.gebeya.askuala_comm.repository.specification;

import et.com.gebeya.askuala_comm.model.Authority;
import et.com.gebeya.askuala_comm.model.Users;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
    public static Specification<Users> getAllStudent() {
        return (root, query, criteriaBuilder) -> {
            Predicate isAdmin = criteriaBuilder.notEqual(root.get("role"), Authority.ADMIN);
            Predicate isTeacher = criteriaBuilder.notEqual(root.get("role"), Authority.TEACHER);
            return criteriaBuilder.and(isAdmin, isTeacher);
        };
    }

    public static Specification<Users> getAllTeacher() {
        return (root, query, criteriaBuilder) -> {
            Predicate isAdmin = criteriaBuilder.notEqual(root.get("role"), Authority.ADMIN);
            Predicate isTeacher = criteriaBuilder.notEqual(root.get("role"), Authority.STUDENT);
            return criteriaBuilder.and(isAdmin, isTeacher);
        };
    }
}
