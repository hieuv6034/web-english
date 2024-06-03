package com.example.englishapp.Repository;


import com.example.englishapp.Entity.VipMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VipMemberRepository extends  JpaRepository<VipMember, Long> {
    @Query(value = "SELECT * from vip_member where username = :username", nativeQuery = true)
    VipMember findByUsername(String username);
}
