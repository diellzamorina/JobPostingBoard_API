package com.example.jobpostingboard_api.controller;



import com.example.jobpostingboard_api.dto.UserDto;
import com.example.jobpostingboard_api.entity.User;
import com.example.jobpostingboard_api.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

        private final UserService userService;


    @GetMapping("/profile/me")
    public ResponseEntity<UserDto> getUserByToken(HttpServletRequest request){

        if(userService.getMe(request)!=null){
            return ResponseEntity.ok(userService.getMe(request));
        }
        else{
            return ResponseEntity.notFound().build();
        }


    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") int id){
        var response = userService.findUserById(id);
           return  errorHandler(response);

    }


    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){

        var response = userService.getAllUsers();
        if(response.size()!=0){
            return ResponseEntity.ok(response);
        }
        else{
            return ResponseEntity.notFound().build();
        }



    }

    @PutMapping("")
    public ResponseEntity<User> updateProfile(@RequestBody UserDto userDto, HttpServletRequest request){
        return ResponseEntity.ok(userService.updateProfile(userDto, request));
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUserById(@RequestBody UserDto userDto, @PathVariable("id") int id){
        var response = userService.updateUserById(userDto, id);
        return errorHandler(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") int id){
        return ResponseEntity.ok(userService.deleteUserById(id));
    }


    public ResponseEntity<UserDto> errorHandler (UserDto response){
        if(response != null){
            return ResponseEntity.ok(response);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

}
