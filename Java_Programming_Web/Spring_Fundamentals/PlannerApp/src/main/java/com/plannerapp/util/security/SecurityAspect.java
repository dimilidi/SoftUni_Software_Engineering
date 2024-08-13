package com.plannerapp.util.security;

import com.plannerapp.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
@Aspect
public class SecurityAspect {

    private final UserService userService;

    public SecurityAspect(UserService userService) {
        this.userService = userService;
    }

    @Around("@annotation(checkSecurity)")
    public Object checkSecurity(ProceedingJoinPoint pjp, CheckSecurity checkSecurity) throws Throwable {
        boolean isLoggedIn = userService.isLoggedIn();
        boolean shouldBeLoggedIn = checkSecurity.shouldBeLoggedIn();

        if (shouldBeLoggedIn && !isLoggedIn) {
            return new ModelAndView("redirect:/login");
        }

        if (!shouldBeLoggedIn && isLoggedIn) {
            return new ModelAndView("redirect:/home");
        }

        // Proceed with the original method
        return pjp.proceed();
    }
}

