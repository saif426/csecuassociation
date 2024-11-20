package com.dev.csecu.repository;

import com.dev.csecu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.studentId = :studentId")
    User findUserByStudentId(@Param("studentId") int studentId);

    User findUserByPassword(String password);

    @Query("SELECT DISTINCT u.studentId, u.name, u.batch, u.session, u.email, u.mobile " +
            "FROM User u " +
            "WHERE u.is_Representative = 1 " +
            "ORDER BY u.batch, u.studentId")
    List<Object[]> findDistinctUsersGroupedByBatch();

}
