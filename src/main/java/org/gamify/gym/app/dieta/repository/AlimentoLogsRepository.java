package org.gamify.gym.app.dieta.repository;

import org.gamify.gym.app.dieta.model.AlimentoLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlimentoLogsRepository extends JpaRepository<AlimentoLogs, Long> {
}