package et.com.gebeya.askuala_school_management_management_service.repository;


import et.com.gebeya.askuala_school_management_management_service.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
}
