package org.gamify.gym.app.friendship.dto;

import java.util.UUID;

import org.gamify.gym.app.friendship.model.Friendship.Status;

public record ChangeStatusRequest(UUID code, Long friendId, Status status) {
}
