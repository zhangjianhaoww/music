package tech.bilian.service;

import tech.bilian.dto.Execution;
import tech.bilian.pojo.Shangjia;

public interface ShangjiaService {
    /**
     * 添加要检测的商家信息
     *
     * @return
     */
    Execution<Shangjia> insertShangjia(Shangjia shangjia);


    /**
     * 查询商家的具体信息
     *
     * @return
     */
    Execution<Shangjia> queryShangjia(Shangjia shangjia);


    Execution<Shangjia> updateShangjia(Shangjia shangjia);
}
