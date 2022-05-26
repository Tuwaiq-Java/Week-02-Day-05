package com.example.excercise9.controller;

import com.example.excercise9.model.Api;
import com.example.excercise9.model.Student;
import com.example.excercise9.service.StudentService;
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
    public ResponseEntity<ArrayList<Student>> getStudents(){
        return ResponseEntity.status(200).body(studentService.getStudents());
    }

    @PostMapping
    public ResponseEntity<Api> addStudents(@RequestBody @Valid Student student, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(500).body(new Api(errors.getFieldError().getDefaultMessage(),500));
        }
        if(!(studentService.isAdd(student))){
            return ResponseEntity.status(500).body(new Api("Error to add student",500));
        }
        return ResponseEntity.status(200).body(new Api("Student is added",200));
    }

    @PutMapping
    public ResponseEntity<Api> updateStudents(@RequestBody @Valid Student student, Errors errors) {
        if(errors.hasErrors()){
            return ResponseEntity.status(500).body(new Api(errors.getFieldError().getDefaultMessage(),500));
        }
        if(!(studentService.isUpdate(student))){
            return ResponseEntity.status(500).body(new Api("Error to update student",500));
        }
        return ResponseEntity.status(200).body(new Api("Student is updated",200));
    }

    @DeleteMapping
    public ResponseEntity<Api> deleteStudents(@RequestBody @Valid Student student, Errors errors) {
        if(errors.hasErrors()){
            return ResponseEntity.status(500).body(new Api(errors.getFieldError().getDefaultMessage(),500));
        }
        if(!(studentService.isDelete(student))){
            return ResponseEntity.status(500).body(new Api("Error to delete student",500));
        }
        return ResponseEntity.status(200).body(new Api("Student is deleted",200));
    }

    @PostMapping("/change")
    public ResponseEntity<Api> changeMajor(@RequestParam String studentId, @RequestParam String major) {

        Integer changeCase = studentService.changeMajor(studentId, major);
        switch (changeCase) {
            case -1:
                return ResponseEntity.status(400).body(new Api("The student id wrong", 400));
            case 0:
                return ResponseEntity.status(400).body(new Api("The major is wrong", 400));
            case 1:
                return ResponseEntity.status(200).body(new Api("Major is changed", 200));
            default:
                return ResponseEntity.status(500).body(new Api("Server error", 500));
        }

    }

}
