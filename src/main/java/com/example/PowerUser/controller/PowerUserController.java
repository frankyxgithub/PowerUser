package com.example.PowerUser.controller;

import com.example.PowerUser.exception.ErrorResponse;
import com.example.PowerUser.exception.UserException;
import com.example.PowerUser.model.PowerUser;
import com.example.PowerUser.repository.PowerUserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/power/users/v1")
public class PowerUserController {

//    private final String url = "http://localhost:8080/power/users/v1";
//    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("version", 1);

    @Autowired
    private PowerUserRepository userRepository;

    @Autowired
    private PowerUserService userService;

    @PostMapping( "/user" )
    public ResponseEntity<PowerUser> addUser(@RequestBody @Valid PowerUser powerUser){

        return userService.addUser(powerUser);

    }

    @GetMapping("/user")
    public List<PowerUser> getAllPowerUsers(){
        System.out.println(LocalDateTime.now());
        List<PowerUser> powerUsers = userService.getAllPowerUsers();
        System.out.println(LocalDateTime.now());
        return powerUsers;
    }
/*
    @GetMapping("/error")
    public ResponseEntity<String> error(HttpStatus httpStatus){

        if(HttpStatus.NOT_FOUND.is3xxRedirection()){
            return ResponseEntity.badRequest().body(new GlobalExceptions()
                            .handleResourceNotFound(new ResourceNotFoundException()))
                    .getBody();
        }
        if(HttpStatus.INTERNAL_SERVER_ERROR.is4xxClientError()){
            return ResponseEntity.badRequest().body(new GlobalExceptions()
                            .handleExceptions(new Exception()))
                    .getBody();
        }
        return ResponseEntity.internalServerError().body(new GlobalExceptions()
                        .handleResourceNotFound(new ResourceNotFoundException()))
                .getBody();

    }

    @GetMapping("/errors")
    public ResponseEntity<String> notFoundHandler(){
        return ResponseEntity.badRequest().body(new GlobalExceptions()
                        .handleResourceNotFound(new ResourceNotFoundException()))
                .getBody();
    }
*/
    @GetMapping("/user/{id}")
    @ResponseBody
    public ResponseEntity<PowerUser> getPowerUser(@PathVariable Integer id){
        return userService.getPowerUser(id);
    }

    @GetMapping("/user-mail/{email}")
    @ResponseBody
    public ResponseEntity<PowerUser> getPowerUserByEmail(@PathVariable String email){
        return userService.getPowerUserByEmail(email);
    }

    @GetMapping("/user-name/{fullName}")
    @ResponseBody
    public ResponseEntity<PowerUser> getPowerUserByFullName(@PathVariable String fullName){
        return userService.getPowerUserByFullName(fullName);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<String> updatePowerUser( @PathVariable Integer id, @RequestBody PowerUser powerUser ){
        userService.updatePowerUser(id, powerUser);

        return ResponseEntity.ok("User successfully updated");

    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deletePowerUser(@PathVariable Integer id){
        userService.deletePowerUser(id);
        return ResponseEntity.ok("User successfully deleted");
    }
}
