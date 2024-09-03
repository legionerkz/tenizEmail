package kz.tenizcap.emailservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CouponPaymentDto {
    private String email;
    private String name;
    private String account;
    private double amount;
    private String currency;
}
