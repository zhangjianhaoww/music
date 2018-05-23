package tech.bilian.service.impl;

import org.junit.Test;
import tech.bilian.BaseTest;
import tech.bilian.dto.Execution;
import tech.bilian.pojo.Shangjia;
import tech.bilian.service.ShangjiaService;

import javax.annotation.Resource;

import static org.junit.Assert.*;

public class ShangjiaServiceImplTest extends BaseTest {
    @Resource
    ShangjiaService shangjiaService;

    @Test
    public void insertShangjia() {
        Shangjia shangjia = new Shangjia();
        shangjia.setShangjiaName("江苏省徐州市某某街第二家咖啡馆");
        Execution<Shangjia> shangjiaExecution = shangjiaService.insertShangjia(shangjia);
    }

    @Test
    public void queryShangjia() {
    }
}