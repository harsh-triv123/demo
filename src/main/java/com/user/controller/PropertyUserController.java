package com.user.controller;

import com.user.dto.PropertyUserDto;
import com.user.entity.PropertyUser;
import com.user.service.PropertyUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/PropertyUserService")
// Rest Controller
@RestController
public class PropertyUserController {//REST API class
    private PropertyUserService pu;

    public PropertyUserController(PropertyUserService pu) {
        this.pu = pu;
    }

    //    http://localhost:8080/api/v1/PropertyUserService/addPropertyUser
    @PostMapping("/addPropertyUser")
    public ResponseEntity<?> addPropertyUser(
            @Valid @RequestBody PropertyUserDto propertyUserDto,
            BindingResult result) {
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.OK);
        }
//        calling service layer
        //puo consist of dto data , return it using ResponseEntity class
        PropertyUserDto propertyUserDto1 = pu.addPropertyUser(propertyUserDto);
        return new ResponseEntity<>(propertyUserDto1, HttpStatus.CREATED);
    }
    @DeleteMapping
    public ResponseEntity<String> deletePropertyUser(@RequestParam long propertyUserId){
        pu.deletePropertyUser(propertyUserId);
        return new ResponseEntity<>("record is deleted",HttpStatus.OK);
    }
    @PutMapping("/{propertyUserId}")
    public ResponseEntity<PropertyUserDto> updatePropertyUser(
        @RequestBody PropertyUserDto propertyUserDto,
        @PathVariable long propertyUserId
    ){
        PropertyUserDto propertyUserDto1 = pu.updatePropertyUser(propertyUserId, propertyUserDto);

        return new ResponseEntity<>(propertyUserDto1,HttpStatus.OK);
    }
    //GET all the records from database
//    http://localhost:8080/api/v1/PropertyUserService
//    http://localhost:8080/api/v1/PropertyUserService?pageSize=2&pageNo=0&sortBy=id&sortDir=asc
    @GetMapping
    public ResponseEntity<List<PropertyUserDto>> getPropertyUser(
//            that query parameter we accept here
            @RequestParam(name = "pageSize",defaultValue = "5",required = false) int pageSize,
            @RequestParam(name = "pageNo",defaultValue = "5",required = false) int pageNo,
            @RequestParam(name = "sortBy",defaultValue = "id",required = false) String sortBy,
             @RequestParam(name = "sortDir",defaultValue = "id",required = false) String sortDir

    ){
        List<PropertyUserDto> propertyUser = pu.getPropertyUser(pageSize,pageNo,sortBy,sortDir);
        return new ResponseEntity<>(propertyUser,HttpStatus.OK);
    }
    //GET a particular record from database
//    http://localhost:8080/api/v1/PropertyUserService/getPropertyUserById?propertyUserId1=6
    // create a new api
    @GetMapping("/getPropertyUserById")
    public ResponseEntity<PropertyUser> getPropertyUserById(
            @RequestParam long propertyUserId1
    ){
        PropertyUser propertyUser = pu.getPropertyUserById(propertyUserId1);
        return new ResponseEntity<>(propertyUser,HttpStatus.OK);
    }
}
