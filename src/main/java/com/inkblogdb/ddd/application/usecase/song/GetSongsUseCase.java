package com.inkblogdb.ddd.application.usecase.song;

import com.inkblogdb.ddd.application.dto.song.SongDTO;
import com.inkblogdb.ddd.domain.model.song.SongRepository;
import com.inkblogdb.ddd.domain.model.song.Songs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetSongsUseCase {

  private final SongRepository songRepository;

  public List<SongDTO> getSongs() {
    Songs songs = songRepository.findAll();
    return songs
        .songs()
        .stream()
        .map(song -> new SongDTO(
            song.songId().value(),
            song.songName().value(),
            song.songLevel().value(),
            song.songReleaseDateTime().value()
        ))
        .toList();
  }

}
