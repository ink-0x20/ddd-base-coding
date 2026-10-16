package com.inkblogdb.ddd.infrastructure.database.query.story.main;

import java.util.List;

public interface MainStoryQueryMapper {

  List<MainStoryQueryRecordEntity> findAll(String playerId);

}
