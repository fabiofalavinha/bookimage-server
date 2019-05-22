package com.bookimage.controller;

import com.bookimage.controller.dto.UserDTO;
import com.bookimage.model.User;
import com.bookimage.parser.Parser;
import com.bookimage.parser.UserParserFactory;
import com.bookimage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;
    private final UserParserFactory userParserFactory;

    @Autowired
    public UserController(UserService userService, UserParserFactory userParserFactory) {
        this.userService = userService;
        this.userParserFactory = userParserFactory;
    }

    @PostMapping("/public/user")
    public void createUser(@RequestBody UserDTO dto) {
        final Parser<User, UserDTO> userParser = userParserFactory.createParser();
        final User user = userParser.parseFrom(dto);
        userService.createUser(user);
    }

    @PutMapping("/api/user")
    public void updateUser(@RequestBody UserDTO dto) {
        final Parser<User, UserDTO> userParser = userParserFactory.createParser();
        final User user = userParser.parseFrom(dto);
        userService.updateUser(user);
    }
}
