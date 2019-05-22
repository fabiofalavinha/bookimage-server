package com.bookimage.parser;

import com.bookimage.controller.dto.UserDTO;
import com.bookimage.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserParserFactory {

    public Parser<User, UserDTO> createParser() {
        return new UserParser();
    }
}
