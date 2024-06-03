package com.example.englishapp.Repository;

import com.example.englishapp.Entity.Lesson;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    @Query(value = "select * from lesson where id = :lessonId", nativeQuery = true)
    Lesson getById(Long lessonId);

    @Query(value = "select * from lesson where level_id = :Id", nativeQuery = true)
    List<Lesson> getByLevelId(Long Id);

    @Query(value = "select * from lesson where type = :Category", nativeQuery = true)
    List<Lesson> showByCategory(String Category);

    @Query(value = "SELECT TIMESTAMPDIFF(hour, lesson.TimeCreate, :now) from lesson where lesson.id = :id", nativeQuery = true)
    int TimeNow(Long id, Instant now);

    @Query(value = "SELECT * from lesson as l WHERE l.name LIKE %:key% ", nativeQuery = true)
    List<Lesson> showByKey(String key);

}
