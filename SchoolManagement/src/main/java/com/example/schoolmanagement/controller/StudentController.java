package com.example.schoolmanagement.controller;

import com.example.schoolmanagement.model.Class;
import com.example.schoolmanagement.model.ResponseAPI;
import com.example.schoolmanagement.model.Student;
import com.example.schoolmanagement.service.ClassService;
import com.example.schoolmanagement.service.StudentService;
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
    private final ClassService classService;

    @GetMapping
    public ResponseEntity<ArrayList<Student>> getStudents(){
        return ResponseEntity.status(200).body(studentService.getStudents());
    }

    @PostMapping
    public ResponseEntity<ResponseAPI> addStudent(@RequestBody @Valid Student student, Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ResponseAPI(errors.getFieldError().getDefaultMessage(),400));
        }
        Boolean response = studentService.addStudent(student);
        if (response) {
            return ResponseEntity.status(201).body(new ResponseAPI("Student added!",201));
        }
        return ResponseEntity.status(400).body(new ResponseAPI("Student already registered!",400));
    }

    @PutMapping
    public ResponseEntity<ResponseAPI> updateStudent(@RequestBody @Valid Student student, Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ResponseAPI(errors.getFieldError().getDefaultMessage(),400));
        }
        Boolean response = studentService.editStudent(student);
        if (response) {
            return ResponseEntity.status(200).body(new ResponseAPI("Student edited!",200));
        }
        return ResponseEntity.status(400).body(new ResponseAPI("studentID doesn't exists!",400));
    }

    @DeleteMapping("/{studentID}")
    public ResponseEntity<ResponseAPI> deleteStudent(@PathVariable String studentID) {
        Boolean response = studentService.deleteStudent(studentID);
        if (!response) {
            return ResponseEntity.status(400).body(new ResponseAPI("studentID doesn't exists!",400));
        }
        return ResponseEntity.status(200).body(new ResponseAPI("Student deleted!",200));
    }

    @PutMapping("/addClass/{studentID}/{classID}")
    public ResponseEntity<ResponseAPI> addClass(@PathVariable String studentID, @PathVariable String classID) {
        Student student = studentService.isStudentExist(studentID);
        if (student == null) {
            return ResponseEntity.status(400).body(new ResponseAPI("studentID doesn't exists!",400));
        }
        Class classs = classService.isClassExist(classID);
        if (classs == null) {
            return ResponseEntity.status(400).body(new ResponseAPI("classID doesn't exists!",400));
        }
        ArrayList<Class> newStudentClasses= student.getClassList();
        newStudentClasses.add(classs);
        student.setClassList(newStudentClasses);
        return ResponseEntity.status(200).body(new ResponseAPI(String.format("Class: %s has been added to %s.", classs.getName(),student.getName()),200));
    }

    @PutMapping("/changeMajor/{studentID}/{major}")
    public ResponseEntity<ResponseAPI> changeMajor(@PathVariable String studentID, @PathVariable String major) {
        Student student = studentService.isStudentExist(studentID);
        if (student == null) {
            return ResponseEntity.status(400).body(new ResponseAPI("studentID doesn't exists!",400));
        }
        student.setMajor(major);
        student.setClassList(new ArrayList<>());
        return ResponseEntity.status(200).body(new ResponseAPI(String.format("Student: %s has changed his major to %s.", student.getName(),major),200));
    }

}
