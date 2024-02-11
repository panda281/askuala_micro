package et.com.gebeya.askuala_payment_service.model;

import et.com.gebeya.askuala_payment_service.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
@Table(name = "student_payment_information")
public class Payment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String studentId;
    private Double price;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;
    @CreationTimestamp
    @Column(name = "created_on")
    private Date createdOn;

    @UpdateTimestamp
    @Column(name = "paid_on")
    private Date updatedOn;
}
