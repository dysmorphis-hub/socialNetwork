package com.faktorzehn.socialNetwork.model.dao;

import com.faktorzehn.socialNetwork.persistence.IMessageRepository;
import com.faktorzehn.socialNetwork.persistence.IUserRepository;
import com.faktorzehn.socialNetwork.model.pojo.Message;
import com.faktorzehn.socialNetwork.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class UserMessageDaoImpl implements UserDao, MessageDao {

    @Autowired
    IMessageRepository messageRepository;
    @Autowired
    IUserRepository userRepository;

    @Override
    public List<User> listAllFriends(User user) {
        return user.getListOfFriends();
    }

    @Override
    public void sendMessageToSpecificUser(User sender, User recipient, Message message) {

        sender.getOutboundMessages().add(message);
        recipient.getInboundMessages().add(message);
        message.getReceiver().add(recipient.getUsername());
        message.setOverallRecipients(message.getReceiver().size());

        this.messageRepository.save(message);
        this.userRepository.save(sender);
        this.userRepository.save(recipient);

    }

    @Override
    public Message sendMessageToFriend(User sender, User receiver, Message message) {

        Message errorMessage = new Message ("User " + receiver.getUsername() + " is not your Friend. You cannot send to Non-Friends");
/*      if(!this.userRepository.findByUsername(sender.getUsername()).getListOfFriends().contains(this.userRepository.findByUsername(receiver.getUsername()))){*/
        if(!sender.getListOfFriends().contains(receiver)){
            return errorMessage;

        }else{
            this.sendMessageToSpecificUser(sender, receiver, message);
        }

        return message;
    }

    @Override
    public Message sendMessageToFriendsAndFriends(String pSender, String pMessage) {

        User sender = this.userRepository.findByUsername(pSender);
        Message message = new Message(pSender, pMessage);
        List<User> baseList = this.listAllFriends(sender);
        List<User> mergedList = new ArrayList<>();
        List<User> finalList = new ArrayList<>();

        for (User u : baseList){

            mergedList.addAll(this.listAllFriends(u));

        }

        mergedList.removeAll(Collections.singleton(sender));
        finalList.addAll(baseList);
        finalList.addAll(mergedList);

        for (User u : finalList){

            this.sendMessageToSpecificUser(sender, u, message);
        }

        this.messageRepository.save(message);
        this.userRepository.save(sender);

        return message;
    }


    @Override
    public List<String> getAllReceivers(Message message) {

        return  message.getReceiver();
    }

    @Override
    public List<Message> listAllInbound(User user) {
        return user.getInboundMessages();
    }

    @Override
    public List<Message> listAllOutbound(User user) {
        return user.getOutboundMessages();
    }

    @Override
    public void sendMessageAll(User sender, Message message) {

    }

    @Override
    public void addNewFriend(User userAdding, User userToAdd) {

        userAdding.getListOfFriends().add(userToAdd);
        userToAdd.getListOfFriends().add(userAdding);

        //To refactor:
        /*
        this.userRepository.save(userToAdd);
        this.userRepository.save(userAdding);
        */
    }

    @Override
    public void removeFriend(User userRemoving, User userToRemove) {

        userRemoving.getListOfFriends().remove(userToRemove);
        this.userRepository.save(userRemoving);
        this.userRepository.save(userToRemove);
    }

}
