package com.example.englishapp.Repository;

import com.example.englishapp.Entity.TokenPayment;
import com.example.englishapp.Entity.VipMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenPaymentRepository extends JpaRepository<TokenPayment, Long> {
    @Query(value = "SELECT * from token_payment where username = :username", nativeQuery = true)
    TokenPayment findByUsername(String username);
    @Query(value = "SELECT * from token_payment where token = :token", nativeQuery = true)
    TokenPayment findByToken(String token);
}
