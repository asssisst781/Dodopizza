package kz.sabirov.pizzeria.controllers;

import kz.sabirov.pizzeria.dto.UserEmailFieldDTO;
import kz.sabirov.pizzeria.dto.UserPasswordAndEmailDTO;
import kz.sabirov.pizzeria.dto.UserPasswordFieldDTO;
import kz.sabirov.pizzeria.entities.User;
import kz.sabirov.pizzeria.services.Implementations.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private UserServiceImpl userService;
    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody UserPasswordAndEmailDTO userDTO){
        String msg;
        try {
            if(userService.findUser(userDTO.getEmail()) != null){
                msg = "Profile already exists";
                return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
            }
            userService.addUser(userDTO);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody UserPasswordAndEmailDTO user){
        String msg;
        try{
            User foundUser = userService.findUser(user.getEmail());
            if(foundUser != null){

                if(userService.passwordDecode(user.getPassword(),
                        foundUser.getPassword())){
                    msg = "You signed in successfully";
                    return new ResponseEntity<>(msg, HttpStatus.OK);
                }
                else{
                    msg = "wrong password";
                    return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
                }
            }
            else{
                msg = "invalid email";
                return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value="/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody UserEmailFieldDTO email){
        User foundUser = userService.findUser(email.getEmail());
        String msg;
        try{
            if(foundUser == null){
                msg = "Email not found";
                return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
            }
            else{
               String token = UUID.randomUUID().toString();
               User updUser = userService.createUser(foundUser);
               updUser.setToken(token);
               userService.updateUser(updUser);
               userService.sendVerificationLink(email.getEmail(), token);
                msg = "Link forwarded. Check email";
               return new ResponseEntity<>(msg, HttpStatus.OK);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value = "createNewPassword")
    public ResponseEntity<?> createNewPassword(@RequestBody UserPasswordFieldDTO password,
                                            @RequestParam("email") String email,
                                               @RequestParam("token") String token){
        String msg;
        User foundUser = userService.findUser(email);
        try{
            if(foundUser == null){
                msg = "Email not found";
                return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
            } else if (!token.equals(foundUser.getToken())) {
                msg = "invalid token";
                return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
            } else{
                msg = "Password changed successfully";
                User updUser = userService.createUser(foundUser);
                updUser.setPassword(userService.passwordEncode(password.getPassword()));
                updUser.setToken(null);
                userService.updateUser(updUser);
                return new ResponseEntity<>(msg, HttpStatus.OK);
            }
        }
        catch (Exception e){
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }
}
