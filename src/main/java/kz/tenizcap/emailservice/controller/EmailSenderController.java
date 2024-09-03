package kz.tenizcap.emailservice.controller;

import kz.tenizcap.emailservice.dto.CouponPaymentDto;
import kz.tenizcap.emailservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/email")
public class EmailSenderController {
    private final EmailService emailService;

    @PostMapping
    public CouponPaymentDto sendCouponNotification(@RequestBody CouponPaymentDto couponPaymentDto){
        return emailService.sendCouponNotification(couponPaymentDto);
    }

}
