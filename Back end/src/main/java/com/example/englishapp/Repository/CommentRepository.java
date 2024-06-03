package com.example.englishapp.Repository;

import com.example.englishapp.Entity.Comment;
import com.example.englishapp.Entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "select * from comments as c where c.lesson_id = :lessonId", nativeQuery = true)
    List<Comment> getCmById(Long lessonId);
}
