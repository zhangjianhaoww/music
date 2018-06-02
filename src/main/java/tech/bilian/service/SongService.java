package tech.bilian.service;

import org.apache.ibatis.annotations.Param;
import tech.bilian.dto.Execution;
import tech.bilian.pojo.Song;

public interface SongService {

    Execution<Song> querySong(Song song);


    /**
     * 添加音乐信息
     *
     * @param song
     * @return
     */
    Execution<Song> addSong(Song song);


    /**
     * 更新音乐信息
     *
     * @param song
     * @return
     */
    Execution<Song> updateSong(@Param("song")Song song);
}
