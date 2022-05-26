package com.example.schoolmanagement.controllers;

import com.example.schoolmanagement.models.Api;
import com.example.schoolmanagement.models.Student;
import com.example.schoolmanagement.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController @RequestMapping("api/v1/student") @RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/")
    public ResponseEntity getStudents(){
        return ResponseEntity.status(200).body(studentService.getStudents());
    }
    @PostMapping("/")
    public ResponseEntity addStudent(@RequestBody @Valid Student stu, Errors error){
        if(error.hasErrors()){
            return ResponseEntity.status(400).body(new Api(error.getFieldError().getDefaultMessage(),400));
        }
        studentService.addStudent(stu);
        return ResponseEntity.status(201).body(new Api("Successfully added.",201));
    }
    @PutMapping("/update/{index}")
    public ResponseEntity updateStudent(@RequestBody @Valid Student stu, Errors error, @PathVariable Integer index){
        if(error.hasErrors()){
            return ResponseEntity.status(400).body(new Api(error.getFieldError().getDefaultMessage(),400));
        }
        if(studentService.updateStudent(stu, index)){
            return ResponseEntity.status(201).body(new Api("Successfully updated.",201));
        }
        return ResponseEntity.status(400).body(new Api("Invalid Index.",400));
    }

    @DeleteMapping ("/{index}")
    public ResponseEntity deleteStudent(@PathVariable Integer index){
        if(studentService.deleteStudent(index)){
            return ResponseEntity.status(201).body(new Api("Successfully deleted.",201));
        }
        return ResponseEntity.status(400).body(new Api("Invalid Index.",400));
    }

    @PostMapping("/add/class")
    public ResponseEntity addClass(@RequestParam String classId,@RequestParam String stuId){
        Integer addStatus = studentService.addClass(classId, stuId);
        if(addStatus == 0){
            return ResponseEntity.status(201).body(new Api("Successfully added class.",201));
        }else if(addStatus == -1){
            return ResponseEntity.status(400).body(new Api("Invalid class id.",201));
        }
        return ResponseEntity.status(400).body(new Api("Invalid student id.",400));
    }

    @PutMapping("/major")
    public ResponseEntity changeMajor(@RequestParam String stuId, @RequestParam String major){
        if(studentService.changeMajor(stuId, major) == 0){
            return ResponseEntity.status(201).body(new Api("Successfully changed major.",201));
        }
        return ResponseEntity.status(400).body(new Api("Invalid student id.",400));
    }


}
