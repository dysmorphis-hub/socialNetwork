package com.faktorzehn.socialNetwork.persistence;

import com.faktorzehn.socialNetwork.model.pojo.Message;
import com.faktorzehn.socialNetwork.model.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMessageRepository extends JpaRepository<Message, Long> {

    Message findBySender(User user);

    Message findByReceiver(User user);

    List<Message> findMessagesByMessageContent(String messageContent);


}
