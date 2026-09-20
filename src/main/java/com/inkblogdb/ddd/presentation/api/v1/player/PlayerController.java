package com.inkblogdb.ddd.presentation.api.v1.player;

import com.inkblogdb.ddd.application.usecase.player.GetPlayerModel;
import com.inkblogdb.ddd.application.usecase.abort.ExistsPlayerException;
import com.inkblogdb.ddd.application.usecase.abort.PlayerNotFoundException;
import com.inkblogdb.ddd.application.usecase.player.AddPlayerCommand;
import com.inkblogdb.ddd.application.usecase.player.AddPlayerUseCase;
import com.inkblogdb.ddd.application.usecase.player.GetPlayerCommand;
import com.inkblogdb.ddd.application.usecase.player.GetPlayerUseCase;
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
  public PlayerResponse getPlayer(@PathVariable String playerId) throws PlayerNotFoundException {
    GetPlayerCommand getPlayerCommand = new GetPlayerCommand(
        playerId
    );
    GetPlayerModel getPlayerModel = getPlayerUseCase.getPlayer(getPlayerCommand);
    return new PlayerResponse(getPlayerModel.id(), getPlayerModel.name());
  }

  @PostMapping("/{playerId}")
  @ResponseStatus(HttpStatus.CREATED)
  public void addPlayer(@PathVariable String playerId, @Valid @RequestBody PlayerRequest playerRequest) throws ExistsPlayerException {
    AddPlayerCommand addPlayerCommand = new AddPlayerCommand(
        playerId,
        playerRequest.name()
    );
    addPlayerUseCase.addPlayer(addPlayerCommand);
  }

}
