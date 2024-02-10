package et.com.gebeya.askuala_school_management_management_service.repository;


import et.com.gebeya.askuala_school_management_management_service.model.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GuardianRepository extends JpaRepository<Guardian, Integer> , JpaSpecificationExecutor<Guardian> {

}
