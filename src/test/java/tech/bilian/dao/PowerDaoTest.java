package tech.bilian.dao;

import org.junit.Test;
import tech.bilian.BaseTest;
import tech.bilian.pojo.Power;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class PowerDaoTest extends BaseTest {

    @Resource
    private PowerDao powerDao;

    @Test
    public void test(){
//        Power power = new Power();
//        power.setSongId(1l);
//        power.setUserId(1l);
//        power.setTime(new Date());
//
//        int result = powerDao.insertPower(power);
//
//        System.out.println(result);

        List<Power> powers = powerDao.queryPower(null);

        System.out.println(powers.size());
    }

}