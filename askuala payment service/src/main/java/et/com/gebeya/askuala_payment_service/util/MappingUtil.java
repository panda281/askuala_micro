package et.com.gebeya.askuala_payment_service.util;

import et.com.gebeya.askuala_payment_service.dto.requestdto.PaymentRequestDto;
import et.com.gebeya.askuala_payment_service.dto.responsedto.PaymentResponseDto;
import et.com.gebeya.askuala_payment_service.model.Payment;

public class MappingUtil {

    private MappingUtil() {
    }
    public static Payment mappingPaymentDtoToModel(PaymentRequestDto  dto)
    {
        return Payment.builder()
                .studentId(dto.getStudentId())
                .price(dto.getPrice())
                .paymentStatus(dto.getPaymentStatus())
                .build();
    }

    public static PaymentResponseDto mappingModelToPaymentDto(Payment payment)
    {
        return PaymentResponseDto.builder()
                .studentId(payment.getStudentId())
                .price(payment.getPrice())
                .paymentStatus(payment.getPaymentStatus())
                .build();
    }
}
