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
            if (songs.size() < 1){
                return new Execution<>(-3, "数据不存在");
            }
            return new Execution<>(1, "操作成功", songs);
        }catch (Exception e){
            return new Execution<>(-2, "数据库操作失败");
        }

    }


    /**
     * 添加音乐信息
     *
     * @param song
     * @return
     */
    @Override
    public Execution<Song> addSong(Song song) {
        if (song==null || song.getSongName()==null ||song.getSongName().trim().equals("")
                || song.getOwnerId()==null ||song.getOwnerId()<1){
            return new Execution<>(-1, "数据不完整");
        }
        try {
            int result = songDao.insertSong(song);
            if (result < 1){
                return new Execution<>(-2, "数据库操作失败");
            }
            return new Execution<>(1, "添加成功");
        }catch (Exception e){
            return new Execution<>(-2, "数据库操作失败");
        }
    }


    /**
     * 更新音乐信息
     *
     * @param song
     * @return
     */
    @Override
    public Execution<Song> updateSong(Song song) {
        if (song==null || song.getSongName()==null ||song.getSongName().trim().equals("")
                || song.getOwnerId()==null ||song.getOwnerId()<1
                || song.getSongId()==null || song.getSongId() <1){
            return new Execution<>(-1, "数据不完整");
        }
        try {
            int result = songDao.updateSong(song);
            System.out.println(result);
            if (result < 1){
                return new Execution<>(-2, "数据库操作失败!!");
            }
            return new Execution<>(1, "更新成功");
        }catch (Exception e){
            return new Execution<>(-2, "数据库操作失败!");
        }

    }
}
