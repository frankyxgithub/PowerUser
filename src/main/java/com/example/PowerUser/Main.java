package com.example.PowerUser;

import com.example.PowerUser.model.PowerUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PowerUser> user = restTemplate.getForEntity("http://localhost:8080/power/users/v1/user/3", PowerUser.class);

        System.out.printf("Status code: %s, PowerUser name: %s, Age: %s", user.getStatusCode(), Objects.requireNonNull(user.getBody()).getFullName(), user.getBody().getAge());

    }
}
