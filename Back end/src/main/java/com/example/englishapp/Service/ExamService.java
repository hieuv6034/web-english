package com.example.englishapp.Service;

import com.example.englishapp.Dto.ExamDTO;
import com.example.englishapp.Dto.QuestionDTO;
import com.example.englishapp.Dto.StatusP;
import com.example.englishapp.Entity.Exam;
import com.example.englishapp.Entity.Questions;
import com.example.englishapp.Entity.User;
import com.example.englishapp.Repository.ExamRepository;
import com.example.englishapp.Repository.QuestionRepository;
import com.example.englishapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    public ExamDTO MapDataExamToDTO(Exam exam){
        ExamDTO examDTO = new ExamDTO();
        examDTO.setID(exam.getID());
        return examDTO;
    }

    public StatusP getExam() {
        User user = authService.getCurrentUser();
        int count = examRepository.GetExamCount(user.getUsername());
        if(!user.getRole().equals("ROLE_VIP_USER") && !user.getRole().equals("ROLE_ADMIN")) {
            if (count >= 2) {
                return new StatusP("false", "Bạn chỉ có 2 lượt làm bài trong hôm nay, bạn cần trở thành thành viên vip mới có thể làm tiếp", "");
            }
        }
        return new StatusP("OK", "", "");
    }

    public QuestionDTO MapDataQuestionToDto(Questions questions){
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setID(questions.getID());
        questionDTO.setQuestion(questions.getQuestion());
        questionDTO.setAnswer1(questions.getAnswer1());
        questionDTO.setAnswer2(questions.getAnswer2());
        questionDTO.setAnswer3(questions.getAnswer3());
        questionDTO.setAnswer4(questions.getAnswer4());
        return questionDTO;
    }

    public List<QuestionDTO> getQuestion() {
        User user = authService.getCurrentUser();
        int count = examRepository.GetExamCount(user.getUsername());
        if(!user.getRole().equals("ROLE_VIP_USER") && !user.getRole().equals("ROLE_ADMIN")) {
            System.out.println(count);
            if (count >= 2) {
                List<Questions> questions = new ArrayList<>();
                return questions.stream().map(this::MapDataQuestionToDto).collect(Collectors.toList());
            }
        }
            Exam exam = new Exam();
            exam.setTimeOpen(Instant.now());
            exam.setTimeclose(15);
            exam.setTime(Instant.now());
            exam.setUsername(user.getUsername());
            examRepository.save(exam);
            Random rand = new Random();
            int ran = rand.nextInt(5) + 1;
            int ran1 = rand.nextInt(5) + 1;
            return questionRepository.getRanDom(ran, ran1).stream().map(this::MapDataQuestionToDto).collect(Collectors.toList());
    }

    public StatusP postAnswer(List<QuestionDTO> questionDTO) {
        double diem = 0;
        for (QuestionDTO q: questionDTO){
            Questions questions = questionRepository.getById(q.getID());
            if(q.getClientAnswer() == null){
                continue;
            }
            if(q.getClientAnswer().equals(questions.getAnswerCorrect())){
                diem++;
            }
        }

        String name = authService.getCurrentUser().getUsername();
        Exam exam = examRepository.getById(examRepository.GetExam(name));
        User user = authService.getCurrentUser();
        if(user.getRole().equals("ROLE_VIP_USER") || user.getRole().equals("ROLE_ADMIN")) {
            if (examRepository.TimeNow(exam.getID(), Instant.now()) < exam.getTimeclose()) {
                exam.setPoint(diem);
                examRepository.save(exam);
                user.setPoint(user.getPoint() + (int) diem);
                userRepository.save(user);
                return new StatusP("oke", "Điểm " + diem + "/10", "");
            } else {
                return new StatusP("false", "Hết thời gian làm bài", "");
            }
        }else{
            int count = examRepository.GetExamCount(user.getUsername());
            if(count > 2){
                return new StatusP("false", "Bạn chỉ có 2 lượt làm bài trong hôm nay, bạn cần trở thành thành viên vip mới có thể làm tiếp", "");
            }else{
                if (examRepository.TimeNow(exam.getID(), Instant.now()) < exam.getTimeclose()) {
                    exam.setPoint(diem);
                    examRepository.save(exam);
                    user.setPoint(user.getPoint() + (int) diem);
                    userRepository.save(user);
                    return new StatusP("oke", "Điểm " + diem + "/10", "");
                } else {
                    return new StatusP("false", "Hết thời gian làm bài", "");
                }
            }
        }
    }
}
