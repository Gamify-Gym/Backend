package org.gamify.gym.app.friendship.controller;

import org.gamify.gym.app.friendship.service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group")
public class GroupController{
    private final GroupService groupService;

    public GroupController(GroupService groupService){
        this.groupService = groupService;
    }

    //@PostMapping
    //public ResponseEntity<?> createGroup(Authentication authentication){
    //
    //    }
}