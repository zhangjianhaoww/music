package tech.bilian.service.impl;

import org.springframework.stereotype.Service;
import tech.bilian.dao.OwnerDao;
import tech.bilian.dto.Execution;
import tech.bilian.pojo.Owner;
import tech.bilian.service.OwnerService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {
    @Resource
    OwnerDao ownerDao;


    @Override
    public Execution<Owner> queryOwner(Owner owner) {
        try {
            List<Owner> owners = ownerDao.queryOwner(owner);
            return new Execution<>(1, "数据库操作成功", owners);
        }catch (Exception e){
            return new Execution<>(-1,"数据库操作失败");
        }
    }
}
