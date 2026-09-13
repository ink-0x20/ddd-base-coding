package com.inkblogdb.ddd.presentation.api.v1.player;

import com.inkblogdb.ddd.application.dto.player.PlayerDTO;
import com.inkblogdb.ddd.application.usecase.abort.ExistsPlayerException;
import com.inkblogdb.ddd.application.usecase.abort.PlayerNotFondException;
import com.inkblogdb.ddd.application.usecase.player.AddPlayerUseCase;
import com.inkblogdb.ddd.application.usecase.player.GetPlayerUseCase;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.PlayerName;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/players")
@RequiredArgsConstructor
public class PlayerController {

  private final GetPlayerUseCase getPlayerUseCase;
  private final AddPlayerUseCase addPlayerUseCase;

  @GetMapping("/{playerId}")
  @ResponseStatus(HttpStatus.OK)
  public PlayerResponse getPlayer(@PathVariable String playerId) throws PlayerNotFondException {
    PlayerDTO playerDTO = getPlayerUseCase.getPlayer(new PlayerId(playerId));
    return new PlayerResponse(playerDTO.id(), playerDTO.name());
  }

  @PostMapping("/{playerId}")
  @ResponseStatus(HttpStatus.CREATED)
  public void addPlayer(@PathVariable String playerId, @Valid @RequestBody PlayerRequest playerRequest) throws ExistsPlayerException {
    PlayerId id = new PlayerId(playerId);
    PlayerName name = new PlayerName(playerRequest.name());
    addPlayerUseCase.addPlayer(id, name);
  }

}
