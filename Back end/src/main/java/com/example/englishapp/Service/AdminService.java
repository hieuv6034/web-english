package com.example.englishapp.Service;

import com.example.englishapp.Dto.StatusP;
import com.example.englishapp.Dto.UserDTO;
import com.example.englishapp.Entity.User;
import com.example.englishapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    public List<UserDTO> getAllUser() {
        return userRepository.findAll().stream().map(this::MapDataUserToDTO).collect(Collectors.toList());
    }

    public UserDTO MapDataUserToDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserID(user.getUserID());
        userDTO.setUsername(user.getUsername());
        userDTO.setAvt(user.getAvt());
        userDTO.setBanned(user.isBanned());
        userDTO.setPoint(user.getPoint());
        if(user.getRole().equals("ROLE_USER")){
            userDTO.setRole("Thành viên thường");
        }else if(user.getRole().equals("ROLE_VIP_USER")){
            userDTO.setRole("Thành viên vip");
        }else {
            userDTO.setRole("Admin");
        }
        return userDTO;
    }

    public StatusP BanUser(UserDTO userDTO) {
        try {
            User user = userRepository.findByUserID(userDTO.getUserID());
            user.setBanned(!user.isBanned());
            userRepository.save(user);
            return new StatusP("oke", "Thành công","");
        }catch (Exception e){
            return new StatusP("false", "Không thành công", "");
        }
    }

    public List<UserDTO> filter(String name) {
        return userRepository.findByKey(name).stream().map(this::MapDataUserToDTO).collect(Collectors.toList());
    }
}
