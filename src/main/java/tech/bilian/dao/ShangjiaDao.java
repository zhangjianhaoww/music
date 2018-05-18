package tech.bilian.dao;

import org.apache.ibatis.annotations.Param;
import tech.bilian.pojo.Shangjia;

import java.util.List;

public interface ShangjiaDao {

    List<Shangjia> queryShangjia(@Param("shangjia")Shangjia shangjia);

    int insertShangjia(@Param("shangjia")Shangjia shangjia);


}
