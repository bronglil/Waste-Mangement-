package com.example.garbagecollection.repository;

import com.example.garbagecollection.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardRepository extends JpaRepository<User, Long> {
    @Query("SELECT COUNT(u) FROM User u WHERE u.role = 'ADMIN'")
    long countADmins();

    @Query("SELECT COUNT(u) FROM User u WHERE u.role = 'DRIVER'")
    long countDrivers();

    @Query("SELECT COUNT(u) FROM User u WHERE u.role = 'DRIVER' AND u.status = 'ACTIVE'")
    long countActiveDrivers();

    @Query("SELECT COUNT(u) FROM User u WHERE u.role = 'DRIVER' AND u.status != 'ACTIVE'")
    long countInactiveDrivers();

    @Query("SELECT COUNT(v) FROM Vehicle v")
    long countVehicles();

    @Query("SELECT COUNT(b) FROM Bin b")
    long countBins();

    @Query("SELECT COUNT(b) FROM Bin b WHERE b.status > 85")
    long countBinsAboveThreshold();

    @Query("SELECT COUNT(b) FROM Bin b WHERE b.status <= 85 AND b.status >=20")
    long countBinsInProgressThreshold();

    @Query("SELECT COUNT(b) FROM Bin b WHERE b.status < 20")
    long countBinsBelowThreshold();



    @Query("SELECT COUNT(v) FROM Vehicle v WHERE v.user IS NULL")
    long countVehiclesWithoutUser();
}
