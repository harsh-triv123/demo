package com.user.service;

import com.user.dto.PropertyUserDto;
import com.user.entity.PropertyUser;
import com.user.exception.ResourceNotFoundException;
import com.user.repository.PropertyUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyUserServiceImp implements PropertyUserService {
    private PropertyUserRepository repo;

    public PropertyUserServiceImp(PropertyUserRepository repo) {
        this.repo = repo;
    }

    @Override
    public PropertyUserDto addPropertyUser(PropertyUserDto propertyUserDto) {
        PropertyUser propertyUser = dtoToEntity(propertyUserDto);
        PropertyUser save = repo.save(propertyUser);
        PropertyUserDto propertyUserDto1 = entityToDto(save);
        return propertyUserDto1;
    }

    @Override
    public void deletePropertyUser(long propertyUserId) {
//        delete that id coming from api
        Optional<PropertyUser> byId = repo.findById(propertyUserId);
        if (byId.isPresent()){
            repo.deleteById(propertyUserId);
        }else {
            throw new ResourceNotFoundException("This Id is Not Present In Data base:-"+ propertyUserId);
        }


    }





    @Override
    public PropertyUserDto updatePropertyUser(long propertyUserId, PropertyUserDto propertyUserDto) {
        // Get the original record
        PropertyUser propertyUser =null;
        Optional<PropertyUser> propertyUserOptional = repo.findById(propertyUserId);

        // Check if the record exists
        if (propertyUserOptional.isPresent()) {
            // Update the properties
             propertyUser= propertyUserOptional.get();

        } else {
            throw new ResourceNotFoundException("Property user not found with ID: " + propertyUserId);
        }
        propertyUser.setName(propertyUserDto.getName());
        propertyUser.setMobile(propertyUserDto.getMobile());
        propertyUser.setEmail(propertyUserDto.getEmail());

        // Save the updated record
        PropertyUser save = repo.save(propertyUser);
        // but in return I am returning dto not entity
        return entityToDto(save);

    }

    @Override
    public List<PropertyUserDto> getPropertyUser(int pageSize, int pageNo, String sortBy, String sortDir) {
       //Pageable has a feature of ascending/descending
        Pageable pageable=null;
        // if from parameter : input is asc , pageable done ascending() else descending()
        if(sortDir.equalsIgnoreCase("asc")){
          pageable=PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
        } else if (sortDir.equalsIgnoreCase("desc")) {
            pageable=PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        }
        //stored in object

        // fetch only that record
        Page<PropertyUser> all = repo.findAll(pageable);

        //convert all record to list
        List<PropertyUser> content = all.getContent();
        //b/s i dont want to save entity hence convert it to dto
        List<PropertyUserDto> collect = content.stream().map(e -> entityToDto(e)).collect(Collectors.toList());
        //return dto;
        return collect;


    }

    @Override
    public PropertyUser getPropertyUserById(long propertyUserId1) {
        PropertyUser propertyUser = repo.findById(propertyUserId1).orElseThrow(()->new ResourceNotFoundException("id is not found for :"+propertyUserId1));
        return propertyUser;

    }
//entity to dto
    public PropertyUserDto entityToDto(PropertyUser propertyUser) {
        PropertyUserDto propertyUserDto = new PropertyUserDto();
        propertyUserDto.setId(propertyUser.getId());
        propertyUserDto.setName(propertyUser.getName());
        propertyUserDto.setEmail(propertyUser.getEmail());
        propertyUserDto.setMobile(propertyUser.getMobile());
        return propertyUserDto;
    }
    //dto to entity
    public PropertyUser dtoToEntity(PropertyUserDto propertyUserDto) {
        PropertyUser propertyUser = new PropertyUser();
        propertyUser.setId(propertyUserDto.getId());
        propertyUser.setName(propertyUserDto.getName());
        propertyUser.setEmail(propertyUserDto.getEmail());
        propertyUser.setMobile(propertyUserDto.getMobile());
        return propertyUser;
    }


}
