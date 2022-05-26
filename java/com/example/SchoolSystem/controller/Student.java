package com.example.SchoolSystem.controller;

import com.example.SchoolSystem.model.Api;
import com.example.SchoolSystem.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("student")
public class Student {
    private final studentService studentService;

    @GetMapping
    public ResponseEntity getStudents() {
        return ResponseEntity.status(200).body(studentService.getStudents());
    }

    @PostMapping
    public ResponseEntity addStudent(@RequestBody @Valid Student student, Errors error) {
        if (error.hasFieldErrors()) {
            String err_msg = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new Api(err_msg, 400));
        }
        Boolean isStudentAdded = studentService.addStudent(student);
        if (!isStudentAdded) {
            return ResponseEntity.status(400).body(new Api("Student not added", 400));
        }
        return ResponseEntity.status(201).body(new Api("Student has successfully been added", 201));
    }

    @PutMapping("{stu_id}")
    public ResponseEntity updateStudent(@RequestBody @Valid Student student, @PathVariable String stu_id, Errors error) {
        if (error.hasFieldErrors()) {
            String err_msg = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new Api(err_msg, 400));
        }
        Boolean isStudentUpdated = studentService.updateStudent(student, stu_id);
        if (!isStudentUpdated) {
            return ResponseEntity.status(400).body(new Api("Student not updated", 400));
        }
        return ResponseEntity.status(201).body(new Api("Student has successfully been updated", 201));

    }

    @DeleteMapping()
    public ResponseEntity deleteStudent(@RequestParam String stu_id) {
        Boolean isStudentDelete = studentService.deleteStudent(stu_id);
        if (!isStudentDelete) {
            return ResponseEntity.status(400).body(new Api("Student not found", 400));
        }
        return ResponseEntity.status(201).body(new Api("Student has successfully been deleted", 201));
    }
    @PutMapping("class/add")
    public ResponseEntity addStudentToClass(@RequestParam String stu_id,@RequestParam String class_id){
        Boolean isStudentAdded=studentService.addStudentToClass(stu_id,class_id);
        if(!isStudentAdded){
            return ResponseEntity.status(400).body(new Api("Student not added", 400));
        }
        return  ResponseEntity.status(201).body(new Api("Student has successfully been added", 201));
    }
    @PutMapping("major/change")
    public ResponseEntity changeMajor(@RequestParam String stu_id,@RequestParam String new_major ){
        boolean isMajorChange= studentService.changeMajor(stu_id,new_major);

        if(!isMajorChange){
            return ResponseEntity.status(400).body(new Api("Major not changed", 400));
        }
        return  ResponseEntity.status(201).body(new Api("Major has successfully been added", 201));
    }
    @GetMapping("class/students")
    public ResponseEntity getClassStudents(@RequestParam String class_id){
        ArrayList<Student>filtered_stulist=studentService.getClassStudents(class_id);
        if(filtered_stulist==null){
            return ResponseEntity.status(400).body(new Api("Major not change", 400));
        }
        return ResponseEntity.status(200).body(filtered_stulist);
    }
    @GetMapping("advisor/students")
    public ResponseEntity getAdvisorStudents(@RequestParam String adv_id){
        ArrayList<Student>filtered_stulist=studentService.getAdvisorStudents(adv_id);
        if(filtered_stulist==null){
            return ResponseEntity.status(400).body(new Api("No advisor found", 400));
        }
        return ResponseEntity.status(200).body(filtered_stulist);
    }

}
