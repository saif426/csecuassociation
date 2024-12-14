package com.dev.csecu.repository;

import com.dev.csecu.entity.EventIncomeDTO;
import com.dev.csecu.entity.RegisteredUserDTO;
import com.dev.csecu.entity.Registration;
import com.dev.csecu.entity.RegistrationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration,Long>  {

    List<Registration> findAllByEventId(long id);

    @Query(value = "SELECT u.StudentId, u.Name, u.Mobile, u.Email " +
            "FROM registration r " +
            "JOIN User u ON r.studentid = u.StudentId " +
            "WHERE r.event_id = :eventId", nativeQuery = true)
    List<Object[]> findRegisteredUsers(Long eventId);

}
