package com.example.englishapp.Controller;

import com.example.englishapp.Dto.StatusP;
import com.example.englishapp.Dto.UserDTO;
import com.example.englishapp.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Admin")
public class AdminController{
    @Autowired
    public AdminService adminService;
    @GetMapping("/user")
    public List<UserDTO> GetAllUser(){
        return adminService.getAllUser();
    }

    @PutMapping("/user/{id}")
    public StatusP BanOrUnBanUser(@RequestBody UserDTO userDTO){
        return adminService.BanUser(userDTO);
    }

    @GetMapping("/user/{name}")
    public List<UserDTO> filter(@PathVariable @RequestBody String name){
        return adminService.filter(name);
    }
}
