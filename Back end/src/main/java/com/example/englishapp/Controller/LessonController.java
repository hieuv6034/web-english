package com.example.englishapp.Controller;

import com.example.englishapp.Dto.LessonDTO;
import com.example.englishapp.Dto.QuestionDTO;
import com.example.englishapp.Dto.StatusP;
import com.example.englishapp.Dto.levelDto;
import com.example.englishapp.Entity.Questions;
import com.example.englishapp.Service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lesson")
public class LessonController {
    @Autowired
    private LessonService lessonService;

    @CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false")
    @GetMapping
    public List<levelDto> showAll() {
        return lessonService.showAll();
    }
    @GetMapping("/{id}")
    public LessonDTO showByID(@PathVariable @RequestBody Long id) {
        return lessonService.showByID(id);
    }

    @GetMapping("/question/{id}")
    public List<QuestionDTO> showQuestion(@PathVariable @RequestBody Long id) {
        return lessonService.showQuetion(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/{id}")
    public StatusP PostAnswer(@RequestBody List<QuestionDTO> questionDTO){
        return lessonService.postAnswer(questionDTO);
    }
}
