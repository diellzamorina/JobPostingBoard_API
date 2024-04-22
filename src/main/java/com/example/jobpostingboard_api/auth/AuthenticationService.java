package com.example.jobpostingboard_api.auth;



import com.example.jobpostingboard_api.configuration.JwtService;
import com.example.jobpostingboard_api.dto.PasswordResetDto;
import com.example.jobpostingboard_api.entity.Address;
import com.example.jobpostingboard_api.entity.User;
import com.example.jobpostingboard_api.enums.UserRoles;
import com.example.jobpostingboard_api.repository.UserRepository;
import com.example.jobpostingboard_api.service.AddressService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final AddressService addressService;


    public AuthenticationResponse register(RegisterRequest request) {

        Address address = addressService.findAddressById(request.getAddressId());


        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .emailAddress(request.getEmailAddress())
                .password(passwordEncoder.encode(request.getPassword()))
                .contactNumber(request.getContactNumber())
                .userRole(UserRoles.ADMIN)
                .address(address)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();

    }

    public AuthenticationResponse login(LoginRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmailAddress(), request.getPassword()));
        var user = userRepository.findByEmailAddress(request.getEmailAddress()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }


    public String resetPassword(HttpServletRequest request, PasswordResetDto passwordResetDto){
        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.substring("Bearer ".length());
        var username = jwtService.extractUsername(token);
        var user = userRepository.findByEmailAddress(username).orElse(null);
        if(passwordResetDto.getNewPassword().equals(passwordResetDto.getConfirmPassword())){
            user.setPassword(passwordEncoder.encode(passwordResetDto.getNewPassword()));
            userRepository.save(user);
            return "Password reseted succesfully!";
        }
        else{
            return "Passwords doesnt match";
        }


    }

    public String forgotPassword( PasswordResetDto passwordResetDto){
        var user = userRepository.findByEmailAddress(passwordResetDto.getUsername()).orElse(null);
        if(user == null){
            return "This user does not exist! Please make sure you typed username correctly!";

        }
        else if(passwordResetDto.getNewPassword().equals(passwordResetDto.getConfirmPassword())){
            user.setPassword(passwordEncoder.encode(passwordResetDto.getNewPassword()));
            userRepository.save(user);
            return "Password reset successfully";

        }
        else{
            return "Password does not match!";
        }
    }


}
