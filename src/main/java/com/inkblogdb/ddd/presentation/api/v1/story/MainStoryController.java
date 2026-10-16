package com.inkblogdb.ddd.presentation.api.v1.story;

import com.inkblogdb.ddd.application.query.story.main.GetMainStoryModel;
import com.inkblogdb.ddd.application.query.story.main.MainStoryQueryService;
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
@RequestMapping("/api/v1/stories/main")
@RequiredArgsConstructor
public class MainStoryController {

  private final MainStoryQueryService mainStoryQueryService;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public List<MainStoryResponse> getMainStories(@AuthenticationPrincipal PlayerId playerId) {
    List<GetMainStoryModel> songs = mainStoryQueryService.findAll(playerId);
    return songs
        .stream()
        .map(story -> new MainStoryResponse(
            story.id(),
            story.name(),
            story.chapter(),
            story.episode(),
            story.readAt()
        ))
        .toList();
  }

}
