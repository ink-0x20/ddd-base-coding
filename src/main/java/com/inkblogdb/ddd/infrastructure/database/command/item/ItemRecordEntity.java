package com.inkblogdb.ddd.infrastructure.database.command.item;

import java.util.UUID;

public record ItemRecordEntity(
    UUID id,
    String name,
    String description
) {

}
