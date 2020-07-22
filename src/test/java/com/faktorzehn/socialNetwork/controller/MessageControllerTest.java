package com.faktorzehn.socialNetwork.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
public class MessageControllerTest {


    @InjectMocks
    private MockMvc mockMvc;
    @Mock
    private MessageController messageController;

    @Before
    public void setUp() throws Exception {

        this.mockMvc = MockMvcBuilders.standaloneSetup(messageController).build();

    }

    @Test
    public void sendToUser() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/sendToUser")).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.content().string("hello world!"));

    }

    @Test
    public void sendToFriend() {
    }

    @Test
    public void sendToFriendsAndTheirFriends() {
    }
}