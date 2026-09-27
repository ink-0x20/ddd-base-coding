package com.inkblogdb.ddd.infrastructure.database.command.player.item;

import com.inkblogdb.ddd.domain.model.item.ItemId;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.item.PlayerItem;
import com.inkblogdb.ddd.domain.model.player.item.PlayerItemRepository;
import com.inkblogdb.ddd.infrastructure.InfrastructureException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlayerItemDataSource implements PlayerItemRepository {

  private final PlayerItemMapper playerItemMapper;
  private final PlayItemDomainFactory playItemDomainFactory;

  @Override
  public Optional<PlayerItem> findById(PlayerId playerId, ItemId itemId) {
    return playItemDomainFactory.createFrom(
        playerItemMapper.findById(playerId.value(), itemId.value())
    );
  }

  @Override
  public void updateQuantity(PlayerItem playerItem) {
    PlayerItemRecordEntity entity = new PlayerItemRecordEntity(
        playerItem.playerId().value(),
        playerItem.itemId().value(),
        playerItem.playerItemQuantity().value()
    );
    try {
      playerItemMapper.updateQuantity(entity);
    } catch (Exception e) {
      throw new InfrastructureException(e);
    }
  }

}
