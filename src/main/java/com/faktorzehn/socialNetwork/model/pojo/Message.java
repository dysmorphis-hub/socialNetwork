package com.faktorzehn.socialNetwork.model.pojo;

import lombok.Data;
import javax.persistence.*;
import java.util.*;


@Data
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String messageContent, sender;
    private Date created;
    private int overallRecipients;
    @ElementCollection
    private List<String> receiver = new ArrayList<>();

    public Message(){
    }

    public Message(String messageContent) {

        this.messageContent = messageContent;
    }

    public Message(String sender, String messageContent){

        this.sender = sender;
        this.messageContent = messageContent;
        this.created = new Date();
    }

    public Message(String sender, String receiver, String messageContent){

        this.sender = sender;
        this.messageContent = messageContent;
        this.created = new Date();
        //this.receiver.add(receiver);
        this.overallRecipients = this.receiver.size();
    }


}
