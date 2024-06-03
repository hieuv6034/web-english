package com.example.englishapp.Controller;

import com.example.englishapp.Dto.RankDTO;
import com.example.englishapp.Dto.UserDTO;
import com.example.englishapp.Repository.UserRepository;
import com.example.englishapp.Service.AdminService;
import com.example.englishapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/user")
    public UserDTO GetAllUser(){
        return userService.getUser();
    }
    @GetMapping("/user/rank")
    public List<UserDTO> GetRank(){
        return userService.getRank();
    }
    @GetMapping("/you/rank")
    public RankDTO GetRankOfMe(){
        return userService.getRankOfMe();
    }
}
