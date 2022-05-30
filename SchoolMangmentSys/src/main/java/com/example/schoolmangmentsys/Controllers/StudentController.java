package com.example.schoolmangmentsys.Controllers;


import com.example.schoolmangmentsys.Services.StudentService;
import com.example.schoolmangmentsys.model.Api;
import com.example.schoolmangmentsys.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor

public class StudentController {
    private final StudentService studentService;


    @GetMapping
    public ResponseEntity<ArrayList<Student>> getStu(){
        return ResponseEntity.status(200).body(studentService.getStu());
    }


    @PostMapping
    public ResponseEntity<Api> addStu(@RequestBody @Valid Student student , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage() , 400));
        }
        Boolean isStuAdded = studentService.addStu(student);
        if(!isStuAdded ){

            return ResponseEntity.status(500).body(new Api("error adding a student",500));
        }

        return ResponseEntity.status(201).body(new Api("new student is added",201));
    }



    @DeleteMapping("/{index}")
    public ResponseEntity<ArrayList<Student>> delStu(@PathVariable Integer index){
        return ResponseEntity.status(200).body(studentService.delStu(index));

    }


    @PutMapping("index")
    public ResponseEntity<ArrayList<Student>> updStu(@PathVariable ArrayList<String> studentsNames, Integer index )
    {
        return ResponseEntity.status(200).body(studentService.updStu(studentsNames , index));
    }


    @PostMapping("/add/stu/classlist")
     public ResponseEntity<Api> addStuToClassList(@RequestBody @Valid String stuId , String classId){
           Integer stuCase = studentService.addStuToClassList(stuId, classId);

        return switch (stuCase) {
            case -1 -> ResponseEntity.status(400).body(new Api("student id or class id should not be null", 400));
            case 0 -> ResponseEntity.status(200).body(new Api("student added successfully to class ", 200));
            default -> ResponseEntity.status(500).body(new Api("Server erroe", 500));
        };
     }


     @PutMapping
    public ResponseEntity<Api> changeStuMajor(@PathVariable String stuId ,@RequestBody String major) {
        Integer majorCase = studentService.changeStuMajor(stuId , major);

        switch (majorCase){
            case -1 :
                return ResponseEntity.status(400).body(new Api("student id or major should not be null" , 400));
            case 0 :
                return ResponseEntity.status(400).body(new Api("the student must have no classes to attend to" ,400));
            case 1 :
                ResponseEntity.status(200).body(new Api("student successfully changed major" ,200));
            default:
                ResponseEntity.status(500).body(new Api("Server Error" ,500));

        }

        return null;

     }








}
