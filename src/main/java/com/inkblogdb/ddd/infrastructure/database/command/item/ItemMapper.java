package com.inkblogdb.ddd.infrastructure.database.command.item;

import java.util.UUID;

public interface ItemMapper {

  ItemRecordEntity findById(UUID itemId);

}
