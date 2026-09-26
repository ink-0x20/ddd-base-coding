package com.inkblogdb.ddd.infrastructure.database.command.item;

import com.inkblogdb.ddd.domain.model.item.Item;
import com.inkblogdb.ddd.domain.model.item.ItemId;
import com.inkblogdb.ddd.domain.model.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ItemDataSource implements ItemRepository {

  private final ItemMapper itemMapper;
  private final ItemDomainFactory itemDomainFactory;

  @Override
  public Item findById(ItemId itemId) {
    return itemDomainFactory.createFrom(
        itemMapper.findById(itemId.value())
    );
  }

}
