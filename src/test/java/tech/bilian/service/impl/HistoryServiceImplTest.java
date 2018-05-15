package tech.bilian.service.impl;

import org.junit.Test;
import tech.bilian.BaseTest;
import tech.bilian.dto.Execution;
import tech.bilian.pojo.History;
import tech.bilian.service.HistoryService;

import javax.annotation.Resource;

import java.util.Date;

import static org.junit.Assert.*;

public class HistoryServiceImplTest extends BaseTest {

    @Resource
    HistoryService historyService;

    @Test
    public void test(){
        History history = new History();
        history.setSongId(1L);
        history.setSongName("东风破");
        history.setState(0);
        history.setUserId(1L);
        history.setTime(new Date());

        historyService.insertHistory(history);

    }


    @Test
    public void test2(){
        History history = new History();
        history.setSongId(1L);
        history.setSongName("东风破");
        history.setState(0);
        history.setUserId(1L);
        history.setTime(new Date());

        Execution<History> histories = historyService.queryHistory(null);

        System.out.println(histories.getCount());

    }

}