package com.resellerapp.util;

import com.resellerapp.service.UserService;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

public class SecurityUtils {

    public static Optional<ModelAndView> checkAccess(UserService userService, String redirectPath, boolean shouldBeLoggedIn) {
        boolean isLoggedIn = userService.isLoggedIn();

        if (shouldBeLoggedIn && !isLoggedIn) {
            return Optional.of(new ModelAndView("redirect:/" + redirectPath));
        }

        if (!shouldBeLoggedIn && isLoggedIn) {
            return Optional.of(new ModelAndView("redirect:/" + redirectPath));
        }

        return Optional.empty();
    }
}
