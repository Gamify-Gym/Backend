package org.gamify.gym.app.dieta.repository;

import org.gamify.gym.app.dieta.model.Dieta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietaRepository extends JpaRepository<Dieta, Long> {

}