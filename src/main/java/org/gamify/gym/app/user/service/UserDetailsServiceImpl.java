package org.gamify.gym.app.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.gamify.gym.app.user.model.User;
import org.gamify.gym.app.user.model.UserAuthenticated;
import org.gamify.gym.app.user.repository.NutritionistRepository;
import org.gamify.gym.app.user.repository.PersonalTrainerRepository;
import org.gamify.gym.app.user.repository.PlayerRepository;
import org.gamify.gym.app.user.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;
    private final NutritionistRepository nutritionistRepository;
    private final PersonalTrainerRepository personalTrainerRepository;

    public UserDetailsServiceImpl(
            UserRepository userRepository,
            PlayerRepository playerRepository,
            NutritionistRepository nutritionistRepository,
            PersonalTrainerRepository personalTrainerRepository) {
        this.userRepository = userRepository;
        this.playerRepository = playerRepository;
        this.nutritionistRepository = nutritionistRepository;
        this.personalTrainerRepository = personalTrainerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        String userType;
        if (playerRepository.existsByUser(user)) {
            userType = "player";
        } else if (nutritionistRepository.existsByUser(user)) {
            userType = "nutritionist";
        } else if (personalTrainerRepository.existsByUser(user)) {
            userType = "personal";
        } else {
            userType = "none";
        }

        return new UserAuthenticated(user, userType);
    }
}
