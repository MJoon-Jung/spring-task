package com.lost.user.controller;

import com.lost.common.domain.exception.InvalidRequestException;
import com.lost.user.controller.response.CheckDuplicatedUserNicknameResponse;
import com.lost.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/duplicate/check")
    public ResponseEntity<CheckDuplicatedUserNicknameResponse> checkDuplicatedUserNickname(
            @RequestParam String nickname
    ) {
        if (nickname == null || nickname.isBlank()) {
            InvalidRequestException invalidRequestException = new InvalidRequestException();
            invalidRequestException.addValidation("nickname", "닉네임을 입력해주세요.");
            throw invalidRequestException;
        }

        Boolean result = userService.checkDuplicatedNickname(nickname);
        return ResponseEntity.ok()
                .body(CheckDuplicatedUserNicknameResponse.from(result));
    }
}
