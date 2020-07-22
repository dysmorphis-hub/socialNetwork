package com.faktorzehn.socialNetwork.model.dao;

import com.faktorzehn.socialNetwork.model.pojo.User;
import com.faktorzehn.socialNetwork.persistence.IMessageRepository;
import com.faktorzehn.socialNetwork.persistence.IUserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class UserMessageDaoImplTest {

    @TestConfiguration
    static class UserMessageDaoImplTestConfiguration {

        @Bean
        public UserMessageDaoImpl userMessageDao() {
            return new UserMessageDaoImpl();
        }
    }

    @Autowired
    private UserMessageDaoImpl userMessageDao;
    @MockBean
    private IUserRepository userRepository;

/*
    @Autowired
    private IUserRepository userRepository;*/
/*  @Autowired
    private UserMessageDaoImpl userMessageDao;*/

    @MockBean
    private IMessageRepository messageRepository;

    @Before
    public void setUp() throws Exception {

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

    }

    @Test
    public void listAllFriends(){

        User user = this.userRepository.findByUsername("tyler");
        String friendList = "David";
        assertEquals(friendList, this.userMessageDao.listAllFriends(user));

    }

    @Test
    public void sendMessageToSpecificUser() {
    }

    @Test
    public void sendMessageToFriend() {
    }

    @Test
    public void sendMessageToFriendsAndFriends() {
    }

    @Test
    public void getAllReceivers() {
    }

    @Test
    public void listAllInbound() {
    }

    @Test
    public void listAllOutbound() {
    }

    @Test
    public void sendMessageAll() {
    }

    @Test
    public void addNewFriend() {
    }

    @Test
    public void removeFriend() {
    }
}