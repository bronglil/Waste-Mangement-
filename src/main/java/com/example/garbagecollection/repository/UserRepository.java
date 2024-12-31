package com.example.garbagecollection.repository;

import com.example.garbagecollection.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    Optional<Object> getUserByEmail(String email);
    @Query("SELECT u FROM User u LEFT JOIN Vehicle v ON u.userId = v.user.userId " +
            "WHERE v.user.userId IS NULL AND u.role = 'DRIVER'")
    List<User> findUsersWithoutVehicles();

    @Query("SELECT u FROM User u WHERE u.role = 'ADMIN'")
    List<User> findUsersByRole(@Param("role") String role);
}
