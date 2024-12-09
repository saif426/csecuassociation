package com.dev.csecu.repository;

import com.dev.csecu.entity.Menu;
import com.dev.csecu.entity.Submenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmenuRepository extends JpaRepository<Submenu, Integer> {
    List<Submenu> findByMenuAndRole(Menu menu, int role);



}
