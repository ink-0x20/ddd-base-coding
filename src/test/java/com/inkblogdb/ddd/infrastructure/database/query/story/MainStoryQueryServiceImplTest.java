package com.inkblogdb.ddd.infrastructure.database.query.story;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.story.StoryId;
import com.inkblogdb.ddd.infrastructure.InfrastructureException;
import com.inkblogdb.ddd.infrastructure.database.query.story.main.MainStoryQueryMapper;
import com.inkblogdb.ddd.infrastructure.database.query.story.main.MainStoryQueryRecordEntity;
import com.inkblogdb.ddd.infrastructure.database.query.story.main.MainStoryQueryServiceImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MainStoryQueryServiceImplTest {

  @InjectMocks
  private MainStoryQueryServiceImpl mainStoryQueryService;
  @Mock
  private MainStoryQueryMapper mainStoryQueryMapper;

  @Nested
  class findAll {
    @Test
    void 値が取得できること() {
      // given
      PlayerId playerId = PlayerId.generate();
      StoryId storyId = new StoryId(UUID.randomUUID());
      List<MainStoryQueryRecordEntity> songs = List.of(
          new MainStoryQueryRecordEntity(
              storyId.value(),
              "テストストーリー",
              1,
              1,
              LocalDateTime.now()
          )
      );
      when(mainStoryQueryMapper.findAll(any())).thenReturn(songs);

      // when
      var result = mainStoryQueryService.findAll(playerId);

      // then
      assertNotNull(result);
      assertEquals(1, result.size());
      assertTrue(result.stream().findFirst().isPresent());
      assertEquals(storyId.toString(), result.stream().findFirst().get().id());
      assertEquals("テストストーリー", result.stream().findFirst().get().name());
      assertEquals(1, result.stream().findFirst().get().chapter());
      assertEquals(1, result.stream().findFirst().get().episode());
      assertNotNull(result.stream().findFirst().get().readAt());
    }

    @Test
    void 存在しない場合エラーになること() {
      // given
      PlayerId playerId = PlayerId.generate();
      when(mainStoryQueryMapper.findAll(any())).thenThrow(InfrastructureException.class);

      // when then
      assertThrows(InfrastructureException.class, () -> mainStoryQueryService.findAll(playerId));
    }
  }

}
