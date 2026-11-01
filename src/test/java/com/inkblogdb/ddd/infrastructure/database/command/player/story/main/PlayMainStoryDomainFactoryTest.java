package com.inkblogdb.ddd.infrastructure.database.command.player.story.main;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.story.main.MainStoryReadDateTime;
import com.inkblogdb.ddd.domain.model.story.StoryId;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PlayMainStoryDomainFactoryTest {

  @InjectMocks
  private PlayMainStoryDomainFactory playMainStoryDomainFactory;

  @Nested
  class createFrom {
    @Test
    void 生成できること() {
      // given
      PlayerId playerId = PlayerId.generate();
      StoryId storyId = new StoryId(UUID.randomUUID());
      PlayerMainStoryRecordEntity playerItemRecordEntity = new PlayerMainStoryRecordEntity(
          playerId.value(),
          storyId.value(),
          MainStoryReadDateTime.createUnread().value()
      );

      // when
      var result = playMainStoryDomainFactory.createFrom(playerItemRecordEntity);

      // then
      assertTrue(result.isPresent());
      assertEquals(playerId.value(), result.get().playerId().value());
      assertEquals(storyId.value(), result.get().storyId().value());
      assertNull(result.get().mainStoryReadDateTime().value());
      assertFalse(result.get().isRead());
    }

    @Test
    void nullの場合空になること() {
      // when
      var result = playMainStoryDomainFactory.createFrom(null);

      // then
      assertTrue(result.isEmpty());
    }
  }

}
