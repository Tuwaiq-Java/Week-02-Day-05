package com.example.springday05.controller;

import com.example.springday05.model.Classes;
import com.example.springday05.model.Student;
import com.example.springday05.service.ClassesService;
import com.example.springday05.service.StudentService;
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
        return ResponseEntity.status(200).body(studentService.getStudent());
    }

    @PostMapping
    public ResponseEntity<String> addStudent(@RequestBody @Valid Student student, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        return ResponseEntity.status(200).body(studentService.addStudent(student));
    }

    @PutMapping("/{index}")
    public ResponseEntity<String> editStudent(@PathVariable int index ,@RequestBody @Valid Student student,
                                              Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        return ResponseEntity.status(200).body(studentService.editStudent(index,student));
    }

    @DeleteMapping("/{index}")
    public ResponseEntity<String> deleteStudent(@PathVariable int index){

        return ResponseEntity.status(200).body(studentService.deleteStudent(index));
    }

    @PutMapping("/addClass/{studintID}/{classID}")
    public ResponseEntity<String> addStudentClass(@PathVariable String studintID, @PathVariable String classID){
        int classStatus = studentService.addStudentClass(studintID,classID);
        switch (classStatus){
            case -1:
               return ResponseEntity.status(400).body("Student ID dosen't exist");
            case 0:
               return ResponseEntity.status(400).body("Class ID dosen't exist");
            case 1:
               return ResponseEntity.status(200).body("Student add class");
            default:
                return ResponseEntity.status(500).body("some error");
        }
    }

    @PutMapping("/changeMajor/{studentID}/{major}")
    public ResponseEntity<String> changeMajor(@PathVariable String studentID, @PathVariable String major){
        int majorStatus = studentService.changeMajor(studentID,major);
        switch (majorStatus){
            case 0:
                return ResponseEntity.status(400).body("Student ID dosen't exist");
            case 1:
                return ResponseEntity.status(200).body("major");
            default:
                return ResponseEntity.status(500).body("some error");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ArrayList<Student>> getStudentName(@PathVariable String id){
        ArrayList<Student> classTeacher = studentService.getStudenList(id);
        return ResponseEntity.status(200).body(classTeacher);
    }
}
