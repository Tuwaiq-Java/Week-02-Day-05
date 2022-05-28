package com.example.school.controller;

import com.example.school.model.Api;
import com.example.school.model.Classes;
import com.example.school.model.Student;
import com.example.school.servise.ClassesService;
import com.example.school.servise.StudentService;
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
    private final ClassesService classesService;

    @GetMapping
    public ResponseEntity<ArrayList<Student>> getStudents() {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudents());
    }

    @PostMapping
    public ResponseEntity<Api> addStudent(@RequestBody @Valid Student student, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean isStudentAdded = studentService.addStudent(student);
        if (!isStudentAdded) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Api("Server error", 500));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new Api("Student added", 201));
    }

    @PutMapping("/update")
    public ResponseEntity<Api> updateStudent(@RequestBody @Valid Student student, @RequestParam Integer index, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean isStudentUpdate = studentService.updateStudent(index, student);
        if (!isStudentUpdate) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Not found ", 400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Student update", 200));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Api> deleteStudent(@RequestParam String studentId) {
        boolean isStudentDelete = studentService.deleteStudent(studentId);
        if (!isStudentDelete) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Not found Student", 400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Student deleted", 200));
    }
    /////////////===================================////////////////

//    @PutMapping
//    public ResponseEntity<Api> Student(@RequestParam String studentId ,@RequestParam String classId) {
//        return
//    }
}