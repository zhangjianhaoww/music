package tech.bilian.dao;

import org.apache.ibatis.annotations.Param;
import tech.bilian.pojo.Power;

import java.util.List;

public interface PowerDao {

    List<Power> queryPower(@Param("power")Power power);


    int insertPower(@Param("power")Power power);
}
