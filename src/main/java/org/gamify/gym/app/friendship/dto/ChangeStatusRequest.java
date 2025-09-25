package org.gamify.gym.app.friendship.dto;

import org.gamify.gym.app.friendship.model.Friendship.Status;

public record ChangeStatusRequest(Long friendshipId, Long friendId, Status status) {
}
