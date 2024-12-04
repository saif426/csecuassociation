package com.dev.csecu.service;

import com.dev.csecu.entity.Submenu;
import com.dev.csecu.repository.SubmenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmenuService {

    @Autowired
    private SubmenuRepository submenuRepository;

    public List<Submenu> getSubmenusByRole(int role) {
        return submenuRepository.findByRole(role);
    }
}
