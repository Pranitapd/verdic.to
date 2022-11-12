package com.ancient.verdicto.repository;

import com.ancient.verdicto.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    public User findByEmailId(String emailId);
}
