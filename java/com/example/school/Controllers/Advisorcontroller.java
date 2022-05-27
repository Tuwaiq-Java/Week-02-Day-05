package com.example.school.Controllers;

import com.example.school.Models.Advisor;
import com.example.school.Models.Api;
import com.example.school.Models.Classes;
import com.example.school.Models.Student;
import com.example.school.Services.Advisorservice;
import com.example.school.Services.Classservice;
import com.example.school.Services.Studentservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/advisor")
@RequiredArgsConstructor
public class Advisorcontroller {
    private final Advisorservice advisorservice;
    private final Studentservice studentservice;

    @PostMapping
    public ResponseEntity<Api> addClass(@RequestBody @Valid Advisor advisor , Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        if (advisorservice.isAdd(advisor))
            return ResponseEntity.status(200).body(new Api("Added new Advisor",200));

        return ResponseEntity.status(400).body(new Api("This Advisor is already exist",400));

    }

    @GetMapping
    public ResponseEntity<ArrayList<Advisor>> getClasses(){

        return ResponseEntity.status(200).body(advisorservice.getClasses());


    }


    @PutMapping
    public ResponseEntity<Api> updateClass(@RequestBody @Valid Advisor advisor , Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        if (advisorservice.isUpdate(advisor))
            return ResponseEntity.status(200).body(new Api("Update the Advisor",200));

        return ResponseEntity.status(400).body(new Api("This Advisor is not exist",400));

    }


    @DeleteMapping
    public ResponseEntity<Api> deleteClass(@RequestBody @Valid Advisor advisor , Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        if (advisorservice.isDelete(advisor))
            return ResponseEntity.status(200).body(new Api("The Advisor Deleted",200));

        return ResponseEntity.status(400).body(new Api("This Advisor is not exist",400));

    }


    @GetMapping("/name/{AdvisorName}")
    public ResponseEntity<ArrayList<Student>> getstudent(@PathVariable String AdvisorName ){
        ArrayList <Student> students=studentservice.Advisorname(AdvisorName);
          if (students!=null)
        return ResponseEntity.status(HttpStatus.OK).body(studentservice.Advisorname(AdvisorName));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(studentservice.Advisorname(AdvisorName));





    }









}
