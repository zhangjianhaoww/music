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
            if (owners.size()<=0){
                return new Execution<>(-2, "数据不存在");
            }
            return new Execution<>(1, "数据库操作成功", owners);
        }catch (Exception e){
            return new Execution<>(-1,"数据库操作失败");
        }
    }


    //查询数据是否存在
    @Override
    public Execution<Owner> selectOwner(Owner owner) {
        try {
            List<Owner> owners = ownerDao.queryOwner(owner);
            if (owners.size()<=0){
                return new Execution<>(1,"数据符合条件");
            }
            return new Execution<>(-2, "数据已存在，请确认信息");
        }catch (Exception e){
            return new Execution<>(-1,"数据库操作失败");
        }
    }


    @Override
    public Execution<Owner> insertOwner(Owner owner) {
        if (owner.getOwnerAddress()==null || owner.getOwnerName()==null || owner.getPhone()==null){
            return new Execution<>(-2, "输入数据不完整");

        }
        try{
            int result = ownerDao.insertOwner(owner);
            if (result<=0){
                return new Execution<>(-1,"数据库添加失败");
            }
            return new Execution<>(1, "操作成功");
        }catch (Exception e){
            return new Execution<>(-1,"数据库已存在，请确认信息");
        }
    }


    //更新版权方信息
    @Override
    public Execution<Owner> updateOwner(Owner owner) {

        if (owner.getOwnerId()==null || owner.getOwnerName()==null || owner.getOwnerAddress()==null || owner.getPhone()==null){
            return new Execution<>(-2, "输入数据不完整");
        }

        try{
            int result = ownerDao.updateOwner(owner);
            if (result < 1){
                return new Execution<>(-1,"数据库更新失败");
            }
            return new Execution<>(1, "操作成功");
        }catch (Exception e){
            return new Execution<>(-1,"数据库已存在，请确认信息" + e.getMessage());
        }
    }
}
