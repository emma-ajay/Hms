package com.project.Hms.Repository;


import com.project.Hms.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.id = ?1")
    User findByUserId(Long id);

    Boolean existsByUserName(String userName);

    Boolean existsByEmail(String email);

    Optional<User> findByUserNameOrEmail(String userName, String email);


    @Query("SELECT u FROM User u WHERE u.email = ?1 ")
    Optional<User> findByEmail(String email);







}
