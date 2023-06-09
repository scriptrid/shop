package ru.scriptrid.userservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.scriptrid.common.dto.UserDto;
import ru.scriptrid.common.security.JwtAuthenticationToken;
import ru.scriptrid.userservice.service.UserService;

import java.math.BigDecimal;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/user/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}/balance")
    public UserDto setBalance(@PathVariable long id, @RequestBody @Valid @Min(0) BigDecimal balance) {
        return userService.setBalance(id, balance);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}/freeze")
    public void freezeUser(@PathVariable long id, @RequestBody boolean isFrozen) {
        userService.setFreeze(id, isFrozen);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}/delete")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable long id) {
        return userService.getUserDtoById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/me/delete")
    public void deleteUser(@AuthenticationPrincipal JwtAuthenticationToken token) {
        userService.deleteUser(token.getId());
    }
}
