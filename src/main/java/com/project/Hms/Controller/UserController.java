package com.project.Hms.Controller;

import com.project.Hms.Security.CurrentUser;
import com.project.Hms.Security.UserPrincipal;
import com.project.Hms.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/assignToRoom/{roomId}")
    public ResponseEntity<?> assignUserToRoom(@PathVariable Long roomId, @CurrentUser UserPrincipal currentUser){
        Long userId = currentUser.getId();
       return userService.assignUserToRoom(userId,roomId);
    }
}
