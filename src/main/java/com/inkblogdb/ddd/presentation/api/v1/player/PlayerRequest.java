package com.inkblogdb.ddd.presentation.api.v1.player;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PlayerRequest(
    @NotBlank(message = "プレイヤー名が空")
    @Size(max = 30, message = "プレイヤー名の形式が不正")
    String name
) {

}
