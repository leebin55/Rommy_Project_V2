package com.roomy.controller;

import com.roomy.model.Guest;
import com.roomy.service.GuestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/room")
@RestController @Slf4j
public class GuestController {

    private final GuestService guestService;

    @GetMapping("/{username}/guest")
    public ResponseEntity<?> list(@PathVariable String username, @RequestParam(name = "limit",required = false,defaultValue = "0") Long limit) {
             guestService.getGuestByUsername(username);

        return null;
    }
}
