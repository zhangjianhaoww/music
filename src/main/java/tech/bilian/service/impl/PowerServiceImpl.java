package tech.bilian.service.impl;

import org.springframework.stereotype.Service;
import tech.bilian.dao.PowerDao;
import tech.bilian.dto.Execution;
import tech.bilian.pojo.Power;
import tech.bilian.service.PowerService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class PowerServiceImpl implements PowerService {

    @Resource
    PowerDao powerDao;


    /**
     * 授权
     *
     * @param power
     * @return
     */
    @Override
    public Execution<Power> insertPower(Power power) {
        if (power.getSongName()==null || power.getUserId()==null){
            return new Execution<>(-1, "输入数据错误！");
        }
        power.setTime(new Date());
        int result = powerDao.insertPower(power);
        if (result <= 0){
            return new Execution<>(-2, "数据库操作失败！");
        }
        return new Execution<>(1, "操作成功！");
    }

    /**
     * 查询
     *
     * @param power
     * @return
     */
    @Override
    public Execution<Power> queryPower(Power power) {
        List<Power> powers = powerDao.queryPower(power);

        if (power==null || powers.size()<0){
            return new Execution<>(-2, "数据库操作失败！");
        }
        return new Execution<>(1, "查询成功！", powers);
    }

    /**
     * 收权
     *
     * @param power
     * @return
     */
    @Override
    public Execution<Power> deletePower(Power power) {

        if (power.getUserId() == null || power.getSongName() == null){
            return new Execution<>(-1, "输入数据错误！");

        }

        int result = powerDao.deletePower(power);

        if (result <= 0){
            return new Execution<>(-2, "数据库操作失败！");
        }
        return new Execution<>(1, "操作成功！");
    }
}
