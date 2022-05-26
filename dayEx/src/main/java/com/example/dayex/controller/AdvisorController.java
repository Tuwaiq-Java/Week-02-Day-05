package com.example.dayex.controller;

import com.example.dayex.model.Advisor;
import com.example.dayex.model.ResponseAPI;
import com.example.dayex.model.Student;
import com.example.dayex.service.AdvisorService;
import com.example.dayex.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/advisor")
@RequiredArgsConstructor
public class AdvisorController {
    private final AdvisorService advisorService;
    @GetMapping
    public ResponseEntity getAdvisors(){
        return ResponseEntity.status(200).body(advisorService.getAdvisors());
    }
    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid Advisor advisor, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ResponseAPI(errors.getFieldError().getDefaultMessage(),400));
        }
        advisorService.addAdvisor(advisor);
        return ResponseEntity.status(200).body(new ResponseAPI("Student added",200));
    }
    @PutMapping("/update")
    public ResponseEntity updateStudent(@RequestBody @Valid Advisor advisor, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ResponseAPI(errors.getFieldError().getDefaultMessage(),400));
        }
        if(advisorService.updateAdvisor(advisor)){
            return ResponseEntity.status(200).body(new ResponseAPI("Student updated",200));
        }
        return ResponseEntity.status(400).body(new ResponseAPI("Student not found",400));
    }
    @DeleteMapping("/delete")
    public ResponseEntity deleteStudent(@PathVariable String id){
        if(advisorService.deleteAdvisor(id)){
            return ResponseEntity.status(200).body(new ResponseAPI("Studen deleted",200));
        }
        return ResponseEntity.status(400).body(new ResponseAPI("Student not found",400));
    }
    @GetMapping("/getstudents/{advisorID}")
    public ResponseEntity getStudents(@PathVariable String advisorID){
        ArrayList<Student> student = advisorService.getStudentsAdvisor(advisorID);
        if(student == null){
            return ResponseEntity.status(400).body(new ResponseAPI("Class not found",400));
        }
        return ResponseEntity.status(200).body(student);
    }

}
