package org.gamify.gym.app.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.gamify.gym.app.user.model.Nutritionist;
import org.gamify.gym.app.user.model.User;

public interface NutritionistRepository extends JpaRepository<Nutritionist, Long> {
    Optional<Nutritionist> findByCrn(long crn);

    Optional<Nutritionist> findByCpf(long cpf);

    boolean existsByUser(User user);
}
