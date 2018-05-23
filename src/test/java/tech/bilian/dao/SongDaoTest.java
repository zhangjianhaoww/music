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
        Song song = new Song();
        song.setSongName("东风破-周杰伦");
        List<Song> songs = songDao.querySong(song);

        System.out.println(songs.get(0).getOwnerName());
    }

    @Test
    public void insertSongTest(){
        Song song = new Song();
        song.setSongName("东风破-周杰伦");
        song.setOwnerId(1l);

        int result = songDao.insertSong(song);
        System.out.println(result);


    }

}