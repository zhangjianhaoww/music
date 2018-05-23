package tech.bilian.service;

import tech.bilian.dto.Execution;
import tech.bilian.pojo.Song;

public interface SongService {

    Execution<Song> querySong(Song song);
}
