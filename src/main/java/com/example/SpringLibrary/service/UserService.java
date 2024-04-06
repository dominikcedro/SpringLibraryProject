package com.example.SpringLibrary.service;

import com.example.SpringLibrary.dto.UserDTO;
import com.example.SpringLibrary.entity.Auth;
import com.example.SpringLibrary.entity.User;
import com.example.SpringLibrary.repository.AuthRepository;
import com.example.SpringLibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuthRepository authRepository;

    @Autowired
    public UserService(UserRepository userRepository, AuthRepository authRepository) {
        this.userRepository = userRepository;
        this.authRepository = authRepository;
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());

        Auth auth = new Auth();
        auth.setUsername(userDTO.getUsername());
        auth.setPassword(userDTO.getPassword());

        if (userDTO.getRole() == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }

        auth.setRole(userDTO.getRole());
        auth.setUser(user);

        user.setAuth(auth);

        authRepository.save(auth);
        return userRepository.save(user);
    }

    public User updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(Long.valueOf(id)).orElseThrow(() -> new RuntimeException("User not found"));
        Auth auth = user.getAuth();

        user.setEmail(userDTO.getEmail());

        auth.setUsername(userDTO.getUsername());
        auth.setPassword(userDTO.getPassword());
        auth.setRole(userDTO.getRole());

        authRepository.save(auth);
        return userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(Long.valueOf(id));
    }
    public UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getAuth().getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getAuth().getPassword());
        userDTO.setRole(user.getAuth().getRole());
        userDTO.setLoanCount(user.getLoanCount());
        return userDTO;
    }
}