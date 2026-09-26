package com.inkblogdb.ddd.infrastructure.database.command.item;

import com.inkblogdb.ddd.domain.model.item.Item;
import com.inkblogdb.ddd.domain.model.item.ItemDescription;
import com.inkblogdb.ddd.domain.model.item.ItemId;
import com.inkblogdb.ddd.domain.model.item.ItemName;
import com.inkblogdb.ddd.infrastructure.InfrastructureException;
import org.springframework.stereotype.Component;

@Component
public class ItemDomainFactory {

  public Item createFrom(ItemRecordEntity itemRecordEntity) {
    if (itemRecordEntity == null) {
      throw new InfrastructureException("楽曲レコードが存在しない");
    }
    return new Item(
        new ItemId(itemRecordEntity.id()),
        new ItemName(itemRecordEntity.name()),
        new ItemDescription(itemRecordEntity.description())
    );
  }

}
