package com.faktorzehn.socialNetwork.controller;

import com.faktorzehn.socialNetwork.persistence.IMessageRepository;
import com.faktorzehn.socialNetwork.persistence.IUserRepository;
import com.faktorzehn.socialNetwork.model.dao.UserMessageDaoImpl;
import com.faktorzehn.socialNetwork.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private UserMessageDaoImpl userMessageDao;
    @Autowired
    private IMessageRepository messageRepository;

    @GetMapping("/addFriend")
    public User addFriend(@RequestParam(name = "adder") String userAdding, @RequestParam(name="toAdd") String userAdded) {

        User adding = this.userRepository.findByUsername(userAdding);
        User added = this.userRepository.findByUsername(userAdded);
        List friendlist = this.userMessageDao.listAllFriends(this.userRepository.findByUsername(adding.getUsername()));

        if(!friendlist.contains(added)){

            this.userMessageDao.addNewFriend(adding,added);
            this.userRepository.save(added);
            this.userRepository.save(adding);
        }

        return adding;
    }

    @GetMapping("/getUserInfo")
    public User getUserInfo(@RequestParam(name = "user") String userName) throws IOException {

        User user = this.userRepository.findByUsername(userName);
        User deserializedUser = new User();

        //Object "User" serialization into file:
        try{
            File fileOutput = new File("C:\\serialize\\User");
            ObjectOutputStream bFileOutput = new ObjectOutputStream(new FileOutputStream(fileOutput));
            bFileOutput.writeObject(user);

        }catch (Exception e) {

            System.out.println("LOG: " + e.getMessage());
        }

        return deserializedUser;
    }
}
