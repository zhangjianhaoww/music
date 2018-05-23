package tech.bilian.service.impl;

import org.springframework.stereotype.Service;
import tech.bilian.dao.ShangjiaDao;
import tech.bilian.dto.Execution;
import tech.bilian.pojo.Shangjia;
import tech.bilian.service.ShangjiaService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShangjiaServiceImpl implements ShangjiaService {
    @Resource
    ShangjiaDao shangjiaDao;

    @Override
    public Execution<Shangjia> insertShangjia(Shangjia shangjia) {

        if (shangjia == null || shangjia.getShangjiaName() == null || shangjia.getShangjiaName().trim().equals("")){
            return new Execution<>(-1, "输入的数据不符合格式");
        }
        try {
            int result = shangjiaDao.insertShangjia(shangjia);
            return new Execution<>(1, "添加成功");
        }catch (Exception e){
            //e.printStackTrace();
            return new Execution<>(-2, "插入数据库错误");

        }

//        if (result <= 0){
//            return new Execution<>(-2, "插入数据库错误");
//        }


    }

    @Override
    public Execution<Shangjia> queryShangjia(Shangjia shangjia) {

        try {
            List<Shangjia> shangjiaList = shangjiaDao.queryShangjia(shangjia);
            if (shangjiaList == null || shangjiaList.size() < 0) {
                return new Execution<>(-2, "数据库操作失败");
            }
            return new Execution<>(1, "查询成功", shangjiaList);
        }catch(Exception e){
            return new Execution<>(-2, "数据库操作失败");

        }

    }
}
