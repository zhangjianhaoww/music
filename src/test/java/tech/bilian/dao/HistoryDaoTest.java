package tech.bilian.dao;

import org.junit.Test;
import tech.bilian.BaseTest;
import tech.bilian.pojo.History;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

public class HistoryDaoTest extends BaseTest {
    @Resource
    HistoryDao historyDao;

    @Test
    public void test(){
        History history = new History();
        history.setSongId(1L);
        history.setSongName("东风破");
        history.setState(0);
        history.setUserId(1L);

        historyDao.insertHistory(history);
    }

    @Test
    public void test2(){
        History history = new History();
        history.setUserId(1L);

       List<History> histories =  historyDao.queryHistory(history);
       System.out.println(histories.size());
    }

}