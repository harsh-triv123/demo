package com.user.service;

import com.user.dto.PropertyUserDto;
import com.user.entity.PropertyUser;

import java.util.List;

//Abstraction: Provides a higher level of abstraction
//Separate the service contract (what the service does) from the implementation (how it does it)
public interface PropertyUserService {

    PropertyUserDto addPropertyUser(PropertyUserDto propertyUserDto);


    void deletePropertyUser(long propertyUserId);

    PropertyUserDto updatePropertyUser(long propertyUserId, PropertyUserDto propertyUserDto);

    List<PropertyUserDto> getPropertyUser(int pageSize, int pageNo, String sortBy, String sortDir);

    PropertyUser getPropertyUserById(long propertyUserId1);
}
