package org.gamify.gym.app.friendship.repository;

import org.gamify.gym.app.friendship.model.PlayerGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerGroupRepository extends JpaRepository<PlayerGroup, Long>{
    
}
