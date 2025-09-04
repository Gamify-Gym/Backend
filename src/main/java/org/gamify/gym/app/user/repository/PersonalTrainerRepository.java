package org.gamify.gym.app.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.gamify.gym.app.user.model.PersonalTrainer;
import org.gamify.gym.app.user.model.User;

public interface PersonalTrainerRepository extends JpaRepository<PersonalTrainer, Long> {
    Optional<PersonalTrainer> findByCref(String cref);

    Optional<PersonalTrainer> findByCpf(String cpf);

    boolean existsByUser(User user);
}
