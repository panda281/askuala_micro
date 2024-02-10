package et.com.gebeya.askuala_school_management_management_service.repository.specifications;

import et.com.gebeya.askuala_school_management_management_service.model.Guardian;
import org.springframework.data.jpa.domain.Specification;

public class GuardianSpecifications {
    public static Specification<Guardian> getGuardianByFirstMiddleLastName(String fName, String mName, String lName)
    {
        return (((root, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(root.get("firstName"),fName),
                criteriaBuilder.equal(root.get("middleName"),mName),
                criteriaBuilder.equal(root.get("lastName"),lName)
        )));
    }

    public static Specification<Guardian> getAllGuardians()
    {
        return (root, query, criteriaBuilder) -> criteriaBuilder.notEqual(root.get("isActive"),false);
    }


}
