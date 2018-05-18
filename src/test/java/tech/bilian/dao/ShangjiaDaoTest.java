package tech.bilian.dao;

import org.junit.Test;
import tech.bilian.BaseTest;
import tech.bilian.pojo.Shangjia;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

public class ShangjiaDaoTest extends BaseTest {
    @Resource
    ShangjiaDao shangjiaDao;

    @Test
    public void queryShangjia() {
        List<Shangjia> shangjias = shangjiaDao.queryShangjia(null);
        System.out.println(shangjias.size());
    }

    @Test
    public void insertShangjia() {
        Shangjia shangjia = new Shangjia();
        shangjia.setShangjiaName("江苏省徐州市某某街第二家咖啡馆");
        int result = shangjiaDao.insertShangjia(shangjia);

        System.out.println(result);

    }
}