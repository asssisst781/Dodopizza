package kz.sabirov.pizzeria.services.Implementations;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import kz.sabirov.pizzeria.dto.UserEmailFieldDTO;
import kz.sabirov.pizzeria.dto.UserPasswordAndEmailDTO;
import kz.sabirov.pizzeria.entities.User;
import kz.sabirov.pizzeria.mappers.UserMapper;
import kz.sabirov.pizzeria.repositories.UserRepository;
import kz.sabirov.pizzeria.security.Permission;
import kz.sabirov.pizzeria.security.PermissionRepository;
import kz.sabirov.pizzeria.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public void addUser(UserPasswordAndEmailDTO userDTO) {
        User user = userMapper.userRegistrationDTOToUser(userDTO);
        user.setPassword(passwordEncode(userDTO.getPassword()));
        user.setBalance(0.0);
        user.setDefaultPermission();
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User createUser(User u0) {
        User u1 = new User();
        u1.setId(u0.getId());
        u1.setBalance(u0.getBalance());
        u1.setPermissions(u0.getPermissions());
        u1.setEmail(u0.getEmail());
        u1.setPassword(u0.getPassword());

        return u1;
    }

    @Override
    public ResponseEntity<String> banUser(UserEmailFieldDTO userDTO) {
        String msg;
        Permission banned = new Permission(100L, "ROLE_BANNED");
        User foundUser = userRepository.findAllByEmail(userDTO.getEmail());
        if(foundUser == null){
            msg = "User not found";
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
        List<Permission> permissionList = foundUser.getPermissions();
        if(permissionList.contains(banned)){
            msg = "User already banned";
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
        msg = "User had been banned";
        foundUser.setBanned();
        userRepository.save(foundUser);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> unbanUser(UserEmailFieldDTO userDTO) {
        String msg;
        Permission banned = new Permission(100L, "ROLE_BANNED");
        User foundUser = userRepository.findAllByEmail(userDTO.getEmail());
        if(foundUser == null){
            msg = "User not found";
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
        List<Permission> permissionList = foundUser.getPermissions();
        if(!(permissionList.contains(banned))){
            msg = "User not banned";
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
        msg = "User had benn unbanned";
        foundUser.setUnbanned();
        userRepository.save(foundUser);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    public String passwordEncode(String password){
        return passwordEncoder.encode(password);
    }
    public boolean passwordDecode(String rawPassword, String encodedPassword){
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
    public User findUser(String email){
        return userRepository.findAllByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findAllByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("username not found");
        }
        return user;
    }
    public void sendVerificationLink(String to, String token){
        String subject = "Click on this link to reset password";
        String text = String.format("http://localhost:8080/auth/createNewPassword?email=%s&token=%s", to, token);
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
