package com.example.englishapp.Repository;

import com.example.englishapp.Dto.UserDTO;
import com.example.englishapp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * from user order by point desc limit 50", nativeQuery = true)
    List<User> getOrderByPoint();

    @Query(value = "SELECT * from user where username = :username", nativeQuery = true)
    User findByUsername(String username);

    @Query(value = "SELECT * from user where email = :email", nativeQuery = true)
    User findByEmail(String email);

    @Query(value = "SELECT * from user where userid = :id", nativeQuery = true)
    User findByUserID(Long id);

    @Query(value = "SELECT * from user where username = :username or email = :Email", nativeQuery = true)
    User findByUsernameAndEmail(String username, String Email);

    @Query(value = "select count(*)+1 from user where point > :point ", nativeQuery = true)
    int getRankOfMe(int point);

    @Query(value = "select * from user where username LIKE %:name%  ", nativeQuery = true)
    List<User> findByKey(String name);
}
