package com.faktorzehn.socialNetwork.model.dao;

import com.faktorzehn.socialNetwork.model.pojo.Message;
import com.faktorzehn.socialNetwork.model.pojo.User;

import java.util.List;

public interface MessageDao {

    void sendMessageToSpecificUser(User sender, User recepient, Message message);

    void sendMessageAll(User sender, Message message);

    Message sendMessageToFriend(User sender, User receiver, Message message);

    Message sendMessageToFriendsAndFriends(String sender, String message);

    List<String> getAllReceivers (Message message);


}
