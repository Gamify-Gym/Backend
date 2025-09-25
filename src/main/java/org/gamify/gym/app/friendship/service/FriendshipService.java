package org.gamify.gym.app.friendship.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.gamify.gym.app.friendship.model.Friendship;
import org.gamify.gym.app.friendship.model.Friendship.Status;
import org.gamify.gym.app.friendship.repository.FriendshipRepository;
import org.gamify.gym.app.user.model.Player;
import org.gamify.gym.app.user.repository.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FriendshipService {
    private final PlayerRepository playerRepository;
    private final FriendshipRepository friendshipRepository;

    public FriendshipService(PlayerRepository playerRepository, FriendshipRepository friendshipRepository) {
        this.playerRepository = playerRepository;
        this.friendshipRepository = friendshipRepository;
    }

    public Player getOtherPlayer(Friendship friendship, Long currentPlayerId) {
        if (friendship.getPlayer().getId_player().equals(currentPlayerId)) {
            return friendship.getFriend();
        } else if (friendship.getFriend().getId_player().equals(currentPlayerId)) {
            return friendship.getPlayer();
        } else {
            throw new IllegalArgumentException("Player " + currentPlayerId + " is not part of this friendship");
        }
    }

    public Friendship newFriendship(String email, Long friendId) {
        LocalDate date = LocalDate.now();
        Player player = playerRepository.findByUserEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Player friend = playerRepository.findById(friendId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (player.getId_player().equals(friendId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't be your own friend");
        }

        Friendship friendship = new Friendship();
        friendship.setCreatedOn(date);
        friendship.setPlayer(player);
        friendship.setFriend(friend);
        friendship.setStatus(Status.PENDING);

        return friendshipRepository.save(friendship);
    }

    public Friendship changeFriendshipStatus(Long friendshipId, String email, Long friendId, Status status) {
        Friendship friendship = friendshipRepository.findById(friendshipId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        LocalDate date = LocalDate.now();

        Player player = playerRepository.findByUserEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Player friend = playerRepository.findById(friendId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!friendship.getPlayer().getId_player().equals(player.getId_player())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Provided Player is different than saved Friendship");
        }
        if (!friendship.getFriend().getId_player().equals(friend.getId_player())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Provided Friend is different than saved Friendship");
        }

        if (friendship.getSinceWhen() == null && status == Status.OK) {
            friendship.setSinceWhen(date);
        }
        friendship.setStatus(status);

        return friendshipRepository.save(friendship);
    }

    public void deleteFriendship(UUID code, String email, Long friendId) {
        Friendship friendship = friendshipRepository.findByFriendCode(code);

        Player player = playerRepository.findByUserEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Player friend = playerRepository.findById(friendId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!friendship.getPlayer().getId_player().equals(player.getId_player())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Provided Player is different than saved Friendship");
        }
        if (!friendship.getFriend().getId_player().equals(friend.getId_player())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Provided Friend is different than saved Friendship");
        }

        friendshipRepository.delete(friendship);
    }

    public List<Friendship> getAllFriendships(String email) {
        Player player = playerRepository.findByUserEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<Friendship> friendships = friendshipRepository.findAllByPlayer(player.getId_player());

        return friendships;
    }
}
