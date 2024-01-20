package et.com.gebeya.askuala_payment_service.repository;

import et.com.gebeya.askuala_payment_service.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,String> {
}
