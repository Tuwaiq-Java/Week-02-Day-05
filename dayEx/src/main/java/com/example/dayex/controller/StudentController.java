package com.example.dayex.controller;

import com.example.dayex.model.ResponseAPI;
import com.example.dayex.model.Student;
import com.example.dayex.model.Teacher;
import com.example.dayex.service.StudentService;
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
    public ResponseEntity getStudents(){
        return ResponseEntity.status(200).body(studentService.getStudents());
    }
    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid Student student, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ResponseAPI(errors.getFieldError().getDefaultMessage(),400));
        }
        studentService.addStudent(student);
        return ResponseEntity.status(200).body(new ResponseAPI("Student added",200));
    }
    @PutMapping("/update")
    public ResponseEntity updateStudent(@RequestBody @Valid Student student, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ResponseAPI(errors.getFieldError().getDefaultMessage(),400));
        }
        if(studentService.updateStudent(student)){
            return ResponseEntity.status(200).body(new ResponseAPI("Student updated",200));
        }
        return ResponseEntity.status(400).body(new ResponseAPI("Student not found",400));
    }
    @DeleteMapping("/delete")
    public ResponseEntity deleteStudent(@PathVariable String id){
        if(studentService.deleteStudent(id)){
            return ResponseEntity.status(200).body(new ResponseAPI("Studen deleted",200));
        }
        return ResponseEntity.status(400).body(new ResponseAPI("Student not found",400));
    }
    @PutMapping("/addStudentClass")
    public ResponseEntity addStudentToClass(@RequestParam String studentID,@RequestParam String classID){
        Integer addStatus = studentService.addStudentToClass(studentID,classID);
        switch (addStatus){
            case -1:
                return ResponseEntity.status(400).body(new ResponseAPI("Student not found",400));
            case 0:
                return ResponseEntity.status(400).body(new ResponseAPI("Class not found",400));
            case 1:
                return ResponseEntity.status(200).body(new ResponseAPI("Student added to class",200));

        }
        return ResponseEntity.status(500).body(new ResponseAPI("Server Error",500));
    }
    @PutMapping("/changemajor")
    public ResponseEntity changeMajor(@RequestParam String studentID,@RequestParam String major){
        if(!studentService.changeMajor(studentID,major)){
            return ResponseEntity.status(400).body(new ResponseAPI("Student not found",400));
        }
        return ResponseEntity.status(200).body(new ResponseAPI("Major changed!",200));
    }
    @GetMapping("/getstudents/{classID}")
    public ResponseEntity getStudents(@PathVariable String classID){
        ArrayList<Student> student = studentService.getStudentsClasses(classID);
        if(student == null){
            return ResponseEntity.status(400).body(new ResponseAPI("Class not found",400));
        }
        return ResponseEntity.status(200).body(student);
    }
}
