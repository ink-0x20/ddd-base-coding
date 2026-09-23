package com.inkblogdb.ddd.presentation.api.v1.player.song;

import com.inkblogdb.ddd.application.usecase.abort.FailedPlayerSongUnlockException;
import com.inkblogdb.ddd.application.usecase.player.song.UnlockSongCommand;
import com.inkblogdb.ddd.application.usecase.player.song.UnlockSongUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/players/{playerId}/songs")
@RequiredArgsConstructor
public class PlayerSongController {

  private final UnlockSongUseCase unlockSongUseCase;

  @PostMapping("/{songId}")
  @ResponseStatus(HttpStatus.CREATED)
  public void unlock(@PathVariable String playerId,
                        @PathVariable String songId,
                        @Valid @RequestBody PlayerSongRequest playerSongRequest) throws FailedPlayerSongUnlockException {
    UnlockSongCommand unlockSongCommand = new UnlockSongCommand(
        playerId,
        songId,
        playerSongRequest.action()
    );
    unlockSongUseCase.unlock(unlockSongCommand);
  }

}
