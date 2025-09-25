package org.gamify.gym.app.friendship.repository;

import java.util.List;
import java.util.Optional;

import org.gamify.gym.app.friendship.model.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.UUID;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    @Query("SELECT f FROM Friendship f WHERE f.player.id = :playerId OR f.friend.id = :playerId")
    List<Friendship> findAllByPlayer(@Param("playerId") Long playerId);

    @Query("SELECT f FROM Friendship f WHERE f.friend.id = :playerId AND f.status = org.gamify.gym.app.friendship.model.Friendship.Status.PENDING")
    List<Friendship> findPendingRequests(@Param("playerId") Long playerId);

    @Query("SELECT f FROM Friendship f WHERE (f.player.id = :playerId OR f.friend.id = :playerId) AND f.status = org.gamify.gym.app.friendship.model.Friendship.Status.OK")
    List<Friendship> findAcceptedFriends(@Param("playerId") Long playerId);

    @Query("""
            SELECT f FROM Friendship f
            WHERE (f.player.id = :p1 AND f.friend.id = :p2)
               OR (f.player.id = :p2 AND f.friend.id = :p1)
            """)
    Optional<Friendship> findBetweenPlayers(@Param("p1") Long p1, @Param("p2") Long p2);

    Friendship findByFriendCode(UUID friendCode);
}
