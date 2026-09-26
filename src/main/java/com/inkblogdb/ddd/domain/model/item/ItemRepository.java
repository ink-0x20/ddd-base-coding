package com.inkblogdb.ddd.domain.model.item;

public interface ItemRepository {

  Item findById(ItemId itemId);

}
