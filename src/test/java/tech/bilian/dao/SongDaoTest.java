package tech.bilian.dao;

import org.junit.Test;
import tech.bilian.BaseTest;
import tech.bilian.pojo.Song;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

public class SongDaoTest extends BaseTest {

    @Resource
    SongDao songDao;

    @Test
    public void querySongTest(){
        List<Song> songs = songDao.querySong(null);

        System.out.println(songs.size());
    }

    @Test
    public void insertSongTest(){
        Song song = new Song();
        song.setSongName("东风破3");
        song.setArtist("周杰伦");
        song.setPrice(4.0);
        song.setAlbum("梦游记");

        int result = songDao.insertSong(song);
        System.out.println(result);

        result = songDao.deleteSong(2l);
        System.out.println(result);
    }

}