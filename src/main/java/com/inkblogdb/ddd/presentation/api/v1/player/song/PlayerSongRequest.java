package com.inkblogdb.ddd.presentation.api.v1.player.song;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PlayerSongRequest(
    @NotBlank(message = "操作が空")
    @Size(max = 30, message = "操作の形式が不正")
    String action
) {

}
