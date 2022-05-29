package com.example.schoolmanagementsoftware.controller;


import com.example.schoolmanagementsoftware.model.Student;
import com.example.schoolmanagementsoftware.service.StudentService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<ArrayList<Student>> getStudent(){
        return ResponseEntity.status(200).body(studentService.getAllStudent());
    }

    @PostMapping
    public ResponseEntity postStudent(@RequestBody @Valid Student student, Errors error){

        if (error.hasErrors()){
            return ResponseEntity.status(400).body(error.getFieldError().getDefaultMessage());
        }
        studentService.addStudent(student);
        return ResponseEntity.status(201).body("Welcome to our school!!!!");
    }

    @PutMapping
    public ResponseEntity updateStudent(@RequestBody @Valid Student student, Errors errors ){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        studentService.updateStudent(student);
        return ResponseEntity.status(200).body("Student has updated");
    }

    @PutMapping("/{studentId}/{courseId}")
    public ResponseEntity addCourseToStudent(@PathVariable String studentId,@PathVariable String courseId){
        if(!studentService.addCourseToStudent(studentId,courseId)){
            return ResponseEntity.status(200).body("XXXX invalid adding XXXXX");
        }
        return ResponseEntity.status(200).body("Student has updated his course list");
    }

    @PutMapping("/{courseId}")
    public ResponseEntity getAllStudentsByCourse(@PathVariable String courseId){
        if(studentService.getStudentsByCourse(courseId).size()<0){
            return ResponseEntity.status(200).body("XXXX invalid searching for course XXXXX");
        }
        return ResponseEntity.status(200).body(studentService.getStudentsByCourse(courseId));
    }

    @PutMapping("major/{studentId}")
    public ResponseEntity changeMajor(@RequestBody String major,@PathVariable String studentId){
        if(!studentService.changeMajor(studentId,major)){
            return ResponseEntity.status(200).body("XXXX invalid change of major  XXXXX");
        }
        return ResponseEntity.status(200).body("Student has update his major");
    }

    @DeleteMapping("/{studentid}")
    public ResponseEntity deleteStudent(@PathVariable String studentid){

        if(!studentService.deleteStudent(studentid)){
            return ResponseEntity.status(400).body("there is no student");
        }
        return ResponseEntity.status(200).body("student has deleted");
    }


}
