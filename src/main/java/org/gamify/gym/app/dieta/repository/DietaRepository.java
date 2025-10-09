package org.gamify.gym.app.dieta.repository;

import java.util.List;

import org.gamify.gym.app.dieta.model.Dieta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DietaRepository extends JpaRepository<Dieta, Long> {

    @Query("SELECT DISTINCT d FROM Dieta d JOIN FETCH d.alimentos.acucars JOIN FETCH d.alimentos.gorduras WHERE d.player.user.email = :email")
    List<Dieta> findDietasWithAlimentosByPlayerEmail(String email);

}