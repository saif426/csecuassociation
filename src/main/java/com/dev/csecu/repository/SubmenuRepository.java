package com.dev.csecu.repository;

import com.dev.csecu.entity.Submenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmenuRepository extends JpaRepository<Submenu, Integer> {
    List<Submenu> findByRole(int role);
}
