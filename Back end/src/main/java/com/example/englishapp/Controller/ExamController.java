package com.example.englishapp.Controller;

import com.example.englishapp.Dto.ExamDTO;
import com.example.englishapp.Dto.QuestionDTO;
import com.example.englishapp.Dto.StatusP;
import com.example.englishapp.Dto.levelDto;
import com.example.englishapp.Service.ExamService;
import com.example.englishapp.Service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exam")
public class ExamController {
    @Autowired
    private ExamService examService;
    @GetMapping()
    public StatusP GetExam() {
        return examService.getExam();
    }

    @GetMapping("/question")
    public List<QuestionDTO> GetQuestion() {
        return examService.getQuestion();
    }

    @PostMapping()
    public StatusP PostAnswer(@RequestBody List<QuestionDTO> questionDTO) {
        return examService.postAnswer(questionDTO);
    }
}
