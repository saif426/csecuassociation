package com.dev.csecu.service;
import com.dev.csecu.entity.Menu;
import com.dev.csecu.entity.Submenu;

import com.dev.csecu.repository.MenuRepository;
import com.dev.csecu.repository.SubmenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmenuService {

    @Autowired
    private SubmenuRepository submenuRepository;
    @Autowired
    private MenuRepository menuRepository;


    public List<Menu> getMenusForUser(int userRole) {
        List<Menu> menus = menuRepository.findAll();

        // Fetch submenus based on user role
        for (Menu menu : menus) {
            List<Submenu> filteredSubmenus = submenuRepository.findByMenuAndRole(menu,userRole);
            menu.setSubmenus(filteredSubmenus);  // Update submenus for each menu
        }

        return menus;
    }
}
