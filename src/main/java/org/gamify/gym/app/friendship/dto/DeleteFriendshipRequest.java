package org.gamify.gym.app.friendship.dto;

import java.util.UUID;

public record DeleteFriendshipRequest(UUID code, Long friendId) {
}