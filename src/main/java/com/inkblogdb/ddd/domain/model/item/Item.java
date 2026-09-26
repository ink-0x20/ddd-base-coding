package com.inkblogdb.ddd.domain.model.item;

public record Item(
    ItemId itemId,
    ItemName itemName,
    ItemDescription itemDescription) {

}
