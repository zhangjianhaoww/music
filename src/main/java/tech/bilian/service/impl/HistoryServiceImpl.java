package tech.bilian.service.impl;

import org.springframework.stereotype.Service;
import tech.bilian.dao.HistoryDao;
import tech.bilian.dao.PowerDao;
import tech.bilian.dao.SongDao;
import tech.bilian.dto.Execution;
import tech.bilian.dto.PlayCount;
import tech.bilian.pojo.History;
import tech.bilian.pojo.Power;
import tech.bilian.pojo.Song;
import tech.bilian.service.HistoryService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Resource
    HistoryDao historyDao;

    @Resource
    PowerDao powerDao;

    @Resource
    SongDao songDao;

    /**
     * 插入播放历史
     *
     * @param history
     * @return
     */
    @Override
    public Execution<History> insertHistory(History history) {
        if (history == null || history.getUserId()==null || history.getSongName()==null){
            return new Execution<>(-1, "插入数据有误");
        }



        //查询该歌曲是否需要授权
        Song song = new Song();
        song.setSongName(history.getSongName());
        List<Song> songs = songDao.querySong(song);
        //需要授权时查询该用户是否授权
        if (songs.size()>0){
            Power power = new Power();
            power.setSongName(history.getSongName());
            power.setUserId(history.getUserId());

            List<Power> powers = powerDao.queryPower(power);
            //已授权 状态为1
            if (powers.size()>0){
                history.setState(1);
            }
            //未授权
            else {
                history.setState(0);
            }
        }
        //歌曲未在服务列表内
        else {
            history.setState(2);
        }


        history.setTime(new Date());
        int result = historyDao.insertHistory(history);
        if (result<=0){
            return new Execution<>(-2, "插入数据库失败");

        }
        switch (history.getState()){
            case 0:
                return new Execution<>(1, "该歌曲并未授权");
            case 1:
                return new Execution<>(2, "该歌曲以授权");
            case 2:
                return new Execution<>(3, "该歌曲不在监听范围内");
            default:
                return new Execution<>(1, "插入成功");
        }

    }


    /**
     * 查询历史
     *
     * @param history
     * @return
     */
    @Override
    public Execution<History> queryHistory(History history) {
        List<History> histories = historyDao.queryHistory(history);
        if (histories == null || histories.size()<0){
            return new Execution<>(-1, "查询数据库失败");
        }
        return new Execution<>(1, "查询成功", histories);
    }


    @Override
    public Execution<PlayCount> selectPlayCount() {
        try{
            List<PlayCount> playCounts = historyDao.selectPlayCount();
            return new Execution<>(1, "查询成功", playCounts);
        }catch (Exception e){
            return new Execution<>(-2, "查询失败：" + e.getMessage());
        }
    }
}
