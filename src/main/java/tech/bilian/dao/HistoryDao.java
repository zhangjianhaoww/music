package tech.bilian.dao;

import org.apache.ibatis.annotations.Param;
import tech.bilian.pojo.History;

import java.util.List;

public interface HistoryDao {

    List<History> queryHistory(@Param("history") History history);

    int insertHistory(@Param("history") History history);
}
