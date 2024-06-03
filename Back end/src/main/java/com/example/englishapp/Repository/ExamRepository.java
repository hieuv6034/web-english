package com.example.englishapp.Repository;

import com.example.englishapp.Dto.ExamDTO;
import com.example.englishapp.Entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    @Query(value = "select * from exam where id = :id", nativeQuery = true)
    Exam GetByID(Long id);

    @Query(value = "SELECT TIMESTAMPDIFF(hour, time_open, :now) from exam where id = :id", nativeQuery = true)
    int TimeNow(Long id, Instant now);

    @Query(value = "select max(id) from exam where username = :userName group by username", nativeQuery = true)
    Long GetExam(String userName);

    @Query(value = "select count(*) from exam where date(time) = CURDATE() and username = :userName", nativeQuery = true)
    int GetExamCount(String userName);
}
