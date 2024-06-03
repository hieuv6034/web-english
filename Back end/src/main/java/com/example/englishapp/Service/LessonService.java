package com.example.englishapp.Service;

import com.beust.jcommander.internal.Lists;
import com.example.englishapp.Dto.*;
import com.example.englishapp.Entity.*;
import com.example.englishapp.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonService {
    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private QuestionRepository quetionRepository;

    @Autowired
    private AuthService authService;


    public List<levelDto> showAll() {
        List<Level> level = levelRepository.findAll();
        return level.stream().map(this::MapDatatoDTO).collect(Collectors.toList());
    }

    public levelDto MapDatatoDTO(Level level){
        levelDto levelDTO = new levelDto();
        levelDTO.setName(level.getName());
        levelDTO.setPic(level.getPic());
        for (Lesson l: lessonRepository.getByLevelId(level.getId())){
            levelDTO.getNameLesson().add(l.getName());
            levelDTO.getId().add(l.getId());
        }
        return levelDTO;
    }

    public LessonDTO MapDataLessonToDTO(Lesson lesson){
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setLessonID(lesson.getId());
        lessonDTO.setEnglish_text(lesson.getContent());
        lessonDTO.setLevel(lesson.getLevel().getName());
        lessonDTO.setName(lesson.getName());
        return lessonDTO;
    }

    public QuestionDTO MapDataQuestionToDTO(Questions questions){
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuestion(questions.getQuestion());
        questionDTO.setID(questions.getID());
        questionDTO.setAnswer1(questions.getAnswer1());
        questionDTO.setAnswer2(questions.getAnswer2());
        questionDTO.setAnswer3(questions.getAnswer3());
        questionDTO.setAnswer4(questions.getAnswer4());
        return questionDTO;
    }

    public LessonDTO showByID(Long id) {
        Lesson lesson = lessonRepository.getById(id);
        LessonDTO lessonDTO = new LessonDTO();
        User user = authService.getCurrentUser();
        if(lesson.getLevel().getId() == 1 || lesson.getLevel().getId() == 2 || lesson.getLevel().getId() == 3){
            return MapDataLessonToDTO(lesson);
        }else{
            if(user.getRole().equals("ROLE_VIP_USER") || user.getRole().equals("ROLE_ADMIN")){
                return MapDataLessonToDTO(lesson);
            }else{
                return lessonDTO;
            }
        }
    }

    public List<QuestionDTO> showQuetion(Long id) {
        List<QuestionDTO> questionDTOs = new ArrayList<>();
        Lesson lesson = lessonRepository.getById(id);
        if(lesson.getLevel().getId() == 1 || lesson.getLevel().getId() == 2 || lesson.getLevel().getId() == 3){
            return quetionRepository.getByLessonId(id).stream().map(this::MapDataQuestionToDTO).collect(Collectors.toList());
        }else{
            User user = authService.getCurrentUser();
            if(user.getRole().equals("ROLE_VIP_USER") || user.getRole().equals("ROLE_ADMIN")){
                return quetionRepository.getByLessonId(id).stream().map(this::MapDataQuestionToDTO).collect(Collectors.toList());
            }else{
                return questionDTOs;
            }
        }

    }

    public StatusP postAnswer(List<QuestionDTO> questionDTOs) {
        int Diem = 0;
        for(QuestionDTO q: questionDTOs){
            Questions questions = quetionRepository.getById(q.getID());
            try {
                if(q.getClientAnswer().equals(questions.getAnswerCorrect())){
                    Diem+=1;
                }
            }catch (Exception e){
                continue;
            }
        }
        if(Diem != questionDTOs.size())
            return new StatusP("oke", "Bạn đã trả lời đúng " + Diem + "/" + questionDTOs.size() + " câu", "");
        return new StatusP("oke", "Chúc mừng bạn đã trả lời đúng tất cả các câu hỏi!", "");
    }

}
