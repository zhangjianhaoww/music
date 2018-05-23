package tech.bilian.service.impl;

import org.springframework.stereotype.Service;
import tech.bilian.dao.SongDao;
import tech.bilian.dto.Execution;
import tech.bilian.pojo.Song;
import tech.bilian.service.SongService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    @Resource
    SongDao songDao;

    @Override
    public Execution<Song> querySong(Song song) {

        try {
            List<Song> songs = songDao.querySong(song);
            return new Execution<>(1, "操作成功", songs);
        }catch (Exception e){
            return new Execution<>(-2, "数据库操作失败");
        }

    }
}
