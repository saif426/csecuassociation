package com.dev.csecu.service;


import com.dev.csecu.entity.Event;
import com.dev.csecu.entity.Student;
import com.dev.csecu.entity.User;
import com.dev.csecu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user)
    {

        this.userRepository.save(user);
    }


    public boolean authenticate(User user) {

        int userId=user.getStudentId();
        String pass=user.getPassword();
        int isAd=user.getRole();
        System.out.println(userId+" "+pass+isAd);

        User u1= userRepository.findUserByStudentId(user.getStudentId());


        if(u1!=null &&u1.getPassword().equals(pass) && u1.getRole()==1)
        {
            return true;
        }
        else {
            return false;
        }

    }

    public List<User> userList() {
        List<User> users = userRepository.findAll(); // Retrieve all events from the database
        return users;
    }

    public List<User> getCRGroupedByBatch() {
        List<Object[]> results = userRepository.findDistinctUsersGroupedByBatch();

        List<User> users = results.stream()
                .map(row -> {
                    User user = new User();
                    user.setStudentId((Integer) row[0]);
                    user.setName((String) row[1]);
                    user.setBatch((Integer) row[2]);
                    user.setSession((String) row[3]);
                    user.setEmail((String) row[4]);
                    user.setMobile((String) row[5]);
                    return user;
                })
                .collect(Collectors.toList());

        // Debugging: Print distinct users
        users.forEach(user -> {
            System.out.println("Distinct User: " + user.getName() + " in Batch " + user.getBatch());
        });

        return users;
    }
    public List<User> getAllUsersGroupedByBatch() {
        List<Object[]> results = userRepository.findUsersGroupedByBatch();

        List<User> users = results.stream()
                .map(row -> {
                    User user = new User();
                    user.setStudentId((Integer) row[0]);
                    user.setName((String) row[1]);
                    user.setBatch((Integer) row[2]);
                    user.setSession((String) row[3]);
                    user.setEmail((String) row[4]);
                    user.setMobile((String) row[5]);
                    return user;
                })
                .collect(Collectors.toList());



        return users;
    }

}
