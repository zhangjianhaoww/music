package tech.bilian.service;

import tech.bilian.dto.Execution;
import tech.bilian.pojo.History;

public interface HistoryService {


    Execution<History> insertHistory(History history);


    Execution<History> queryHistory(History history);

}
