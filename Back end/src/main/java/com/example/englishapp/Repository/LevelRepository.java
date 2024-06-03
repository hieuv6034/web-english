package com.example.englishapp.Repository;

import com.example.englishapp.Entity.Lesson;
import com.example.englishapp.Entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {

}
