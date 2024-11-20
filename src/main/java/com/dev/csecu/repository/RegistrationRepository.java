package com.dev.csecu.repository;

import com.dev.csecu.entity.Registration;
import com.dev.csecu.entity.RegistrationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration,Long>  {

    List<Registration> findAllByEventId(long id);
}
