package com.example.englishapp.Repository;

import com.example.englishapp.Entity.Lesson;
import com.example.englishapp.Entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Questions, Long> {
    @Query(value = "select * from questions where lesson_id = :lessonId", nativeQuery = true)
    List<Questions> getByLessonId(Long lessonId);

    @Query(value = "select * from questions where id = :id", nativeQuery = true)
    Questions getById(Long id);

    @Query(value = "select * from questions where (id + :ran1) % :ran = 0 limit 10", nativeQuery = true)
    List<Questions> getRanDom(int ran, int ran1);
}
