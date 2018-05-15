package tech.bilian.service.impl;

import org.springframework.stereotype.Service;
import tech.bilian.dao.HistoryDao;
import tech.bilian.dto.Execution;
import tech.bilian.pojo.History;
import tech.bilian.service.HistoryService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Resource
    HistoryDao historyDao;

    /**
     * 插入播放历史
     *
     * @param history
     * @return
     */
    @Override
    public Execution<History> insertHistory(History history) {
        if (history == null || history.getUserId()==null){
            return new Execution<>(-1, "插入数据有误");
        }
        history.setTime(new Date());
        int result = historyDao.insertHistory(history);
        if (result<=0){
            return new Execution<>(-2, "插入数据库失败");

        }

        return new Execution<>(1, "插入成功");
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
}
