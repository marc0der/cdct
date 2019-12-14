package org.example;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.Collections;
import java.util.UUID;

@Controller("/users")
public class UsersController {
    @Get(value = "/32fadd88-4d61-402d-8d80-2679f12b5c66", produces = "application/json; charset=UTF-8")
    public User index() {
        User user = new User(
                UUID.fromString("32fadd88-4d61-402d-8d80-2679f12b5c66"),
                "John",
                "West",
                59,
                Collections.singletonList(
                        new Id(9946, UUID.fromString("042534a4-e998-4ac0-98da-91f4af65cc94")))
        );
        return user;
    }
}
