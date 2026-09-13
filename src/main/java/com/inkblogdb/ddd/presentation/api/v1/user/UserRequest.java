package com.inkblogdb.ddd.presentation.api.v1.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
    @NotBlank(message = "ユーザーIDが空")
    @Size(min = 36, max = 36, message = "ユーザーIDの形式が不正")
    String userId
) {

}
