package com.example.garbagecollection.repository;

import com.example.garbagecollection.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRole(User.UserRole role);
    Optional<User> findByIdAndRole(Long id, User.UserRole role);
    List<User> findByFirstNameAndRole(String firstName, User.UserRole role);
    Optional<User> findByEmail(String email);
    Optional<Object> getDriverByEmail(String email);
    @Query("SELECT u FROM User u WHERE u.userId NOT IN (SELECT v.user.userId FROM Vehicle v) AND u.role = 'driver' ")
    List<User> findUsersWithoutVehicles();
}
