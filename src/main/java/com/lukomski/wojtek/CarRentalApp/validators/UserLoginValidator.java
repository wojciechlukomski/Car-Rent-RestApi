package com.lukomski.wojtek.CarRentalApp.validators;

import com.lukomski.wojtek.CarRentalApp.db.UserRepository;
import com.lukomski.wojtek.CarRentalApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
public class UserLoginValidator {
    private UserRepository userRepository;

    @Autowired
    public UserLoginValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean validate(User user) {
        User getUser = userRepository.findByLogin(user.getLogin());
        return getUser != null;
    }
}
