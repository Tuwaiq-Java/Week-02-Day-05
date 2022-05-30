package com.example.schoolmangmentsys.Controllers;


import com.example.schoolmangmentsys.Services.StudyClassestService;
import com.example.schoolmangmentsys.model.Api;
import com.example.schoolmangmentsys.model.StudyClasses;
import com.example.schoolmangmentsys.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/studyClass")
@RequiredArgsConstructor
public class StudyClassesController {

  private final StudyClassestService studyClassestService ;


    @GetMapping
    public ResponseEntity<ArrayList<StudyClasses>> getStuClass(){
        return ResponseEntity.status(200).body(studyClassestService.getStuClass());
    }


    @PostMapping
    public ResponseEntity<Api> addStuClass(@RequestBody @Valid StudyClasses studyClasses , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage() , 400));
        }
        Boolean isStuAdded = studyClassestService.addStuClass(studyClasses);
        if(!isStuAdded ){

            return ResponseEntity.status(500).body(new Api("error adding a teacher",500));
        }

        return ResponseEntity.status(201).body(new Api("new teacher is added",201));
    }



    @DeleteMapping("/{index}")
    public ResponseEntity<ArrayList<StudyClasses>> delStuClass(@PathVariable Integer index){
        return ResponseEntity.status(200).body(studyClassestService.delStuClass(index));

    }


    @PutMapping("index")
    public ResponseEntity<ArrayList<StudyClasses>> updStuClass(@RequestBody @Valid  ArrayList<String> classesNames, Integer index )
    {
        return ResponseEntity.status(200).body(studyClassestService.updStuClass(classesNames , index));
    }



    @GetMapping("/classlist")
    public ResponseEntity<Api> stuOfClassList(String classId , String stuId){
        
    }











}
