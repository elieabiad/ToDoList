package com.elieabiad.toDoList.advice;

import com.elieabiad.toDoList.model.exception.UnAuthenticatedUserException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class RestControllerAspect {

    @Around(value =
            "@within(org.springframework.web.bind.annotation.RestController) " +
                    "|| @annotation(org.springframework.web.bind.annotation.RestController)"
    )

    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String authHeader = request.getHeader("Authorization");

        //request.getCookies();

        //String jwt = authHeader.replace("Bearer ", "").replace("Bearer", "");

//security check


        String basic = authHeader.replace("Basic ", "");//.replace("Bearer", "");

        String[] split = basic.split(":");

        String user = split[0];

        String postmanPassword = split[1];
        Users users = new Users();

        if(!users.users.containsKey(user)) throw new UnAuthenticatedUserException("username not found.");
        String savedPass = users.users.get(user).get("password");
        if (!postmanPassword.equals(savedPass)) throw new UnAuthenticatedUserException("un-authenticated user");


        return joinPoint.proceed();
    }

    public static class Users {

        public HashMap<String, HashMap<String, String>> users;

/*        public HashMap<String, Map<String, String>> getGender() {
            return users;
        }*/

        Users() {

            users = new HashMap<>();

            HashMap<String, String> user1 = new HashMap(); user1.put("password", "password123");

            HashMap<String, String> user2 = new HashMap(); user2.put("password", "password123");

            users.put("elieabiad", user1);
            users.put("lucienchemaly", user2);

            //users.get("elieabiad").get("password");

        }
    }
}
