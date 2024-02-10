package et.com.gebeya.askuala_school_management_management_service.repository.specifications;


import et.com.gebeya.askuala_school_management_management_service.model.Admin;
import org.springframework.data.jpa.domain.Specification;

public class AdminSpecifications {
    public static Specification<Admin> getAllAdmin ()
    {
        return (root, query, criteriaBuilder) -> criteriaBuilder.notEqual(root.get("isActive"),false);
    }
}
