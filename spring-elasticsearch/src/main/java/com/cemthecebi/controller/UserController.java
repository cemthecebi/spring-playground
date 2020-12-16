package com.cemthecebi.controller;

import com.cemthecebi.entity.User;
import com.cemthecebi.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.List;

import static com.cemthecebi.entity.User.UserBuilder.anUser;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        final User user1 = anUser()
                .id("K0001")
                .name("cem")
                .surname("cebi")
                .address("bayrampasa")
                .birtDate(Calendar.getInstance().getTime())
                .build();

        final User user2 = anUser()
                .id("K0002")
                .name("furkan")
                .surname("kocer")
                .address("zeytinburnu")
                .birtDate(Calendar.getInstance().getTime())
                .build();

        final User user3 = anUser()
                .id("K0002")
                .name("cem")
                .surname("berke")
                .address("besiktas")
                .birtDate(Calendar.getInstance().getTime())
                .build();

        userRepository.saveAll(List.of(user1, user2, user3));
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<User>> getUser(@PathVariable String name) {
        final List<User> userList = userRepository.getUserListByName(name);
        return ResponseEntity.ok(userList);
    }
}
