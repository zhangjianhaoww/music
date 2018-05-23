package tech.bilian.service;

import tech.bilian.dto.Execution;
import tech.bilian.pojo.Owner;

public interface OwnerService {

    Execution<Owner> queryOwner(Owner owner);

}
