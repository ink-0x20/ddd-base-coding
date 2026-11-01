package com.inkblogdb.ddd.infrastructure.database.command.player.story.main;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.item.PlayerItemQuantity;
import com.inkblogdb.ddd.domain.model.player.story.main.MainStoryReadDateTime;
import com.inkblogdb.ddd.domain.model.player.story.main.PlayerMainStory;
import com.inkblogdb.ddd.domain.model.story.StoryId;
import com.inkblogdb.ddd.infrastructure.InfrastructureException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerMainStoryDataSourceTest {

  @InjectMocks
  private PlayerMainStoryDataSource playerMainStoryDataSource;
  @Mock
  private PlayerMainStoryMapper playerMainStoryMapper;
  @Mock
  private PlayMainStoryDomainFactory playMainStoryDomainFactory;

  @Nested
  class findById {
    @Test
    void 値が取得できること() {
      // given
      PlayerId playerId = PlayerId.generate();
      StoryId storyId = new StoryId(UUID.randomUUID());
      PlayerMainStory playerMainStory = new PlayerMainStory(
          playerId,
          storyId,
          MainStoryReadDateTime.createUnread()
      );
      when(playMainStoryDomainFactory.createFrom(any())).thenReturn(Optional.of(playerMainStory));

      // when
      var result = playerMainStoryDataSource.findById(playerId, storyId);

      // then
      assertTrue(result.isPresent());
      assertEquals(playerId, result.get().playerId());
      assertEquals(storyId, result.get().storyId());
      assertFalse(result.get().isRead());
    }

    @Test
    void 存在しない場合エラーになること() {
      // given
      PlayerId playerId = PlayerId.generate();
      StoryId storyId = new StoryId(UUID.randomUUID());
      when(playMainStoryDomainFactory.createFrom(any())).thenThrow(InfrastructureException.class);

      // when then
      assertThrows(InfrastructureException.class, () -> playerMainStoryDataSource.findById(playerId, storyId));
    }
  }

}
