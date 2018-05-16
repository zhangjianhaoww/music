package tech.bilian.service;

import tech.bilian.dto.Execution;
import tech.bilian.pojo.Power;

public interface PowerService {

    Execution<Power> insertPower(Power power);

    Execution<Power> queryPower(Power power);

    Execution<Power> deletePower(Power power);

}
