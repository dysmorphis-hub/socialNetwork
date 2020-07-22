package com.faktorzehn.socialNetwork.controller;

import com.faktorzehn.socialNetwork.persistence.IMessageRepository;
import com.faktorzehn.socialNetwork.persistence.IUserRepository;
import com.faktorzehn.socialNetwork.model.dao.UserMessageDaoImpl;
import com.faktorzehn.socialNetwork.model.pojo.Message;
import com.faktorzehn.socialNetwork.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MessageController {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private UserMessageDaoImpl userMessageDao;
    @Autowired
    private IMessageRepository messageRepository;

    @GetMapping("/sendToUser")
    public Message sendToUser(@RequestParam(name = "sender") String userSender, @RequestParam(name="recipient") String userRecipient, @RequestParam (name = "message") String pMessage) {

        User sender = this.userRepository.findByUsername(userSender);
        User recipient = this.userRepository.findByUsername(userRecipient);
        Message message = new Message(userSender, userRecipient, pMessage);

        this.userMessageDao.sendMessageToSpecificUser(sender, recipient, message);

        return message;

    }

    @GetMapping("/sendToFriend")
    public Message sendToFriend(@RequestParam(name = "sender") String userSender, @RequestParam(name="recipient") String userRecipient, @RequestParam (name = "message") String pMessage) {

        User sender = this.userRepository.findByUsername(userSender);
        User recipient = this.userRepository.findByUsername(userRecipient);
        Message message = new Message(userSender, userRecipient, pMessage);
        Message returnedMessage = message;

        returnedMessage  =  this.userMessageDao.sendMessageToFriend(sender, recipient, message);

        return returnedMessage;

    }

    @GetMapping("/sendToFriendsAndTheirFriends")
    public Message sendToFriendsAndTheirFriends(@RequestParam(name = "sender") String userSender, @RequestParam (name = "message") String pMessage){

        return this.userMessageDao.sendMessageToFriendsAndFriends(userSender, pMessage);

    }

    @GetMapping("/recursiveSend")
    public Message sendRecursiveToFriends(@RequestParam(name = "sender") String userSender, @RequestParam (name = "message") String pMessage) {

        return this.userMessageDao.sendRecursiveMessageToFriends(userSender, pMessage);

    }



}
