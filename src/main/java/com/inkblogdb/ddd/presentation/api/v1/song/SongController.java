package com.inkblogdb.ddd.presentation.api.v1.song;

import com.inkblogdb.ddd.application.query.song.GetSongModel;
import com.inkblogdb.ddd.application.query.song.SongQueryService;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/songs")
@RequiredArgsConstructor
public class SongController {

  private final SongQueryService songQueryService;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public List<SongResponse> getSongs(@AuthenticationPrincipal PlayerId playerId) {
    List<GetSongModel> songs = songQueryService.findAll(playerId);
    return songs
        .stream()
        .map(song -> new SongResponse(
            song.id(),
            song.name(),
            song.easyLevel(),
            song.normalLevel(),
            song.hardLevel(),
            song.expertLevel(),
            song.unlockType(),
            song.unlockTargetId(),
            song.unlockQuantity(),
            song.releaseDateTime(),
            song.unlockStatus(),
            song.unlockedAt()
        ))
        .toList();
  }

}
