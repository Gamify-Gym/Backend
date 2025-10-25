package org.gamify.gym.app.dieta.repository;

import org.gamify.gym.app.dieta.model.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlimentoRepository extends JpaRepository<Alimento, Long> {

}
