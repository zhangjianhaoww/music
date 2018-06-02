package tech.bilian.dao;

import org.apache.ibatis.annotations.Param;
import tech.bilian.pojo.Owner;

import java.util.List;

public interface OwnerDao {
    List<Owner> queryOwner(@Param("owner") Owner owner);

    int insertOwner(@Param("owner") Owner owner);

    int updateOwner(@Param("owner") Owner owner);

}
