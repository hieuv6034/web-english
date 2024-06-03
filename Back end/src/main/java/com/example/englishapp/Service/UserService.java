package com.example.englishapp.Service;

import com.example.englishapp.Dto.RankDTO;
import com.example.englishapp.Dto.UserDTO;
import com.example.englishapp.Dto.levelDto;
import com.example.englishapp.Entity.Lesson;
import com.example.englishapp.Entity.Level;
import com.example.englishapp.Entity.User;
import com.example.englishapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    public UserDTO getUser(){
        User user = authService.getCurrentUser();
        UserDTO userDTO = new UserDTO();
        if(user.getRole().equals("ROLE_USER")){
            userDTO.setRole("Thành viên thường");
        }else if(user.getRole().equals("ROLE_VIP_USER")){
            userDTO.setRole("Thành viên vip");
        }else {
            userDTO.setRole("Admin");
        }
        userDTO.setUsername(user.getUsername());
        userDTO.setPoint(user.getPoint());
        return userDTO;
    }

    public List<UserDTO> getRank() {
        List<User> user = userRepository.getOrderByPoint();
        return  user.stream().map(this::MapDatatoDTO).collect(Collectors.toList());
    }

    public UserDTO MapDatatoDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserID(user.getUserID());
        userDTO.setBanned(user.isBanned());
        userDTO.setPoint(user.getPoint());
        userDTO.setUsername(user.getUsername());
        if(user.getRole().equals("ROLE_USER")){
            userDTO.setRole("Thành viên thường");
        }else if(user.getRole().equals("ROLE_VIP_USER")){
            userDTO.setRole("Thành viên vip");
        }else {
            userDTO.setRole("Admin");
        }
        return userDTO;
    }

    public RankDTO getRankOfMe() {
        User user = authService.getCurrentUser();
        RankDTO rankDTO = new RankDTO();
        rankDTO.setRank(userRepository.getRankOfMe(user.getPoint()));
        return rankDTO;
    }
}
