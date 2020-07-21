package com.faktorzehn.socialNetwork.model.dao;

import com.faktorzehn.socialNetwork.model.pojo.Message;
import com.faktorzehn.socialNetwork.model.pojo.User;

import java.util.List;
import java.util.Set;

public interface UserDao {

    List<User> listAllFriends(User user);

    void addNewFriend(User userAdding, User userToAdd);

    void removeFriend(User userRemoving, User userToRemove);

    List<Message> listAllInbound (User user);

    List<Message> listAllOutbound (User user);



}
