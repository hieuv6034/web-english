package com.example.englishapp.Controller;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.example.englishapp.Dto.QuestionDTO;
import com.example.englishapp.Dto.StatusP;
import com.example.englishapp.Entity.Checkout;
import com.example.englishapp.Entity.TokenPayment;
import com.example.englishapp.Entity.User;
import com.example.englishapp.Entity.VipMember;
import com.example.englishapp.Repository.TokenPaymentRepository;
import com.example.englishapp.Repository.UserRepository;
import com.example.englishapp.Repository.VipMemberRepository;
import com.example.englishapp.Service.AuthService;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

@RestController
@RequestMapping(value = "/api")
public class StripeController {
    private static Gson gson = new Gson();
    @Autowired
    AuthService authService;
    @Autowired
    VipMemberRepository vipMemberRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenPaymentRepository tokenPaymentRepository;

    @GetMapping("/subscription/{token}")
    public StatusP checkSubscription(@PathVariable @RequestBody String token) {
        System.out.println(token);
        TokenPayment tokenPayment = tokenPaymentRepository.findByToken(token);
        User user = authService.getCurrentUser();
        if(tokenPayment != null){
            tokenPaymentRepository.delete(tokenPayment);
            VipMember vipMember = vipMemberRepository.findByUsername(user.getUsername());
            if(vipMember != null){
                user.setRole("ROLE_VIP_USER");
                userRepository.save(user);
                vipMemberRepository.save(vipMember);
                return new StatusP("ok", "Chúc mừng bạn đã trở thành thành viên vip của chúng tôi", "");
            }
            VipMember vipMember1 = new VipMember(user.getUsername(), Instant.now());
            vipMemberRepository.save(vipMember1);
            return new StatusP("ok", "Chúc mừng bạn đã trở thành thành viên vip của chúng tôi", "");
        }
        return new StatusP("false", "Giao dịch không còn tồn tại", "");
    }
    @PostMapping("/subscription")
    public String subscriptionWithCheckoutPage(@RequestBody Checkout checkout) throws StripeException {
        init();
        String token = UUID.randomUUID().toString();
        checkout.setSuccessUrl(checkout.getSuccessUrl() + token);
        SessionCreateParams params = new SessionCreateParams.Builder().setSuccessUrl(checkout.getSuccessUrl())
                .setCancelUrl(checkout.getCancelUrl()).addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.SUBSCRIPTION).addLineItem(new SessionCreateParams.LineItem.Builder()
                        .setQuantity(1L).setPrice(checkout.getPriceId()).build())
                .build();
        try {
            Session session = Session.create(params);
            System.out.println(checkout.getSuccessUrl());
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("sessionId", session.getId());
            User user = authService.getCurrentUser();
            TokenPayment tokenPayment = tokenPaymentRepository.findByUsername(user.getUsername());
            if(tokenPayment == null){
               TokenPayment tokenPayment1 = new TokenPayment(user.getUsername(), token);
               tokenPaymentRepository.save(tokenPayment1);
            }else{
                tokenPayment.setToken(token);
                tokenPaymentRepository.save(tokenPayment);
            }
            return gson.toJson(responseData);
        } catch (Exception e) {
            Map<String, Object> messageData = new HashMap<>();
            messageData.put("message", e.getMessage());
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("error", messageData);
            System.out.println(responseData);
            System.out.println(e.getMessage());
            return gson.toJson(responseData);
        }
    }

    private static void init() {
        Stripe.apiKey = "sk_test_51LKYhQFqvipCbJY9xRLx0gIEphvr2bOaNwR7JVZqpTlRFIasurps68uTE2Ww5YLOKdSk6mXRsc46er6wgOK7h5kF00iUwHsqE1";
    }
}
