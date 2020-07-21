package com.faktorzehn.socialNetwork.persistence;

import com.faktorzehn.socialNetwork.model.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Long> {

    public abstract User findByUsername(String username);



}
