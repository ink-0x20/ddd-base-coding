package com.inkblogdb.ddd.application.query.story.main;

import com.inkblogdb.ddd.domain.model.player.PlayerId;

import java.util.List;

public interface MainStoryQueryService {

  List<GetMainStoryModel> findAll(PlayerId playerId);

}
