package tech.bilian.service.impl;

import org.junit.Test;
import tech.bilian.BaseTest;
import tech.bilian.dao.SongDao;
import tech.bilian.pojo.Song;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

public class SongServiceImplTest extends BaseTest {

    @Resource
    SongDao songDao;

    @Test
    public void querySong() {
        List<Song> songs = songDao.querySong(null);
        System.out.println(songs.size());
    }
}