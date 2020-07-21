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

        User sender = new User("bojan");
        User senderFriendOne = new User ("tyler");
        User senderFriendoneFriend = new User ("david");

        User recipient = new User ("john");
        User recipientFriendOne = new User("tom");
        User recipientFriendTwo = new User("adam");
        User recipientNoFriendshipRelation = new User ("peter");

        this.userMessageDao.addNewFriend(sender, recipient);
        this.userMessageDao.addNewFriend(sender, senderFriendOne);
        this.userMessageDao.addNewFriend(recipient, recipientFriendOne);
        this.userMessageDao.addNewFriend(recipient, recipientFriendTwo);
        this.userMessageDao.addNewFriend(senderFriendOne, senderFriendoneFriend);

        this.userRepository.save(sender);
        this.userRepository.save(recipient);
        this.userRepository.save(recipientFriendOne);
        this.userRepository.save(recipientFriendTwo);
        this.userRepository.save(senderFriendOne);
        this.userRepository.save(senderFriendoneFriend);
        this.userRepository.save(recipientNoFriendshipRelation);


        return "init done";
    }
}
