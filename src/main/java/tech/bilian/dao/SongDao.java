package tech.bilian.dao;

import org.apache.ibatis.annotations.Param;
import tech.bilian.pojo.Song;

import java.util.List;

public interface SongDao {

    /**
     * 查询歌曲信息
     * @param song
     * @return
     */
    List<Song> querySong(@Param("song")Song song);

    /**
     * 添加歌曲信息
     * @param song
     * @return
     */
    int insertSong(@Param("song")Song song);


    int deleteSong(String songName);


    int updateSong(@Param("song")Song song);
}
