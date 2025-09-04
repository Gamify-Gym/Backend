package org.gamify.gym.app.user.service;

import org.gamify.gym.app.user.model.User;
import org.gamify.gym.app.user.repository.UserRepository;
import org.gamify.gym.app.user.model.Player;
import org.gamify.gym.app.user.repository.PlayerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository, PlayerRepository playerRepository) {
        this.userRepository = userRepository;
        this.playerRepository = playerRepository;
    }

    @Transactional
    public User createUser(String username, String email, String password) {
        User user = new User();
        String hash = encoder.encode(password);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(hash);
        return userRepository.save(user);
    }

    @Transactional
    public Player assignType(String email, double weight, double height) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Player player = new Player();
        player.setUser(user);
        player.setWeight(weight);
        player.setHeight(height);
        playerRepository.save(player);
        return player;
    }
}
