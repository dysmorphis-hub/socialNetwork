package com.faktorzehn.socialNetwork.controller;

import com.faktorzehn.socialNetwork.persistence.IMessageRepository;
import com.faktorzehn.socialNetwork.persistence.IUserRepository;
import com.faktorzehn.socialNetwork.model.dao.UserMessageDaoImpl;
import com.faktorzehn.socialNetwork.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class InitDataController {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private UserMessageDaoImpl userMessageDao;
    @Autowired
    private IMessageRepository messageRepository;

    @GetMapping("/init")
    public String init(){

        User bojanRef = new User("bojan");
        User tylerRef = new User ("tyler");
        User davidRef = new User ("david");

        User johnRef = new User ("john");
        User tomRef = new User("tom");
        User adamRef = new User("adam");
        User peterRef = new User ("peter");

        User chuckRef = new User ("chuck");

        this.userMessageDao.addNewFriend(bojanRef, johnRef);
        this.userMessageDao.addNewFriend(bojanRef, tylerRef);
        this.userMessageDao.addNewFriend(johnRef, tomRef);
        this.userMessageDao.addNewFriend(johnRef, adamRef);
        this.userMessageDao.addNewFriend(tylerRef, davidRef);
        this.userMessageDao.addNewFriend(adamRef, chuckRef);

        this.userRepository.save(bojanRef);
        this.userRepository.save(johnRef);
        this.userRepository.save(tomRef);
        this.userRepository.save(adamRef);
        this.userRepository.save(tylerRef);
        this.userRepository.save(davidRef);
        this.userRepository.save(peterRef);
        this.userRepository.save(chuckRef);

        return "init done";
    }
}
