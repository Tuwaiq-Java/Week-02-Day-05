package com.example.schoolmanagementsoftware.controller;
import com.example.schoolmanagementsoftware.model.Teacher;
import com.example.schoolmanagementsoftware.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("api/v1/teacher")
@RestController
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity getTeacher(){
        return ResponseEntity.status(200).body(teacherService.getAllTeacher());
    }

    @PostMapping
    public ResponseEntity postTeacher(@RequestBody @Valid Teacher teacher, Errors error){

        if (error.hasErrors()){
            return ResponseEntity.status(400).body(error.getFieldError().getDefaultMessage());
        }
        teacherService.addTeacher(teacher);
        return ResponseEntity.status(201).body("Welcome to our school!!!!");
    }

    @PutMapping
    public ResponseEntity updateTeacher(@RequestBody @Valid Teacher teacher, Errors errors ){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        teacherService.addTeacher(teacher);
        return ResponseEntity.status(200).body("Teacher has updated");
    }

    @PutMapping("/{teacherId}/{courseId}")
    public ResponseEntity addCourseToTeacher(@PathVariable String teacherId,@PathVariable String courseId){
        if(!teacherService.addCourseToTeacher(teacherId,courseId)){
            return ResponseEntity.status(200).body("XXXX invalid adding XXXXX");
        }
        return ResponseEntity.status(200).body("Teacher has updated his course list");
    }

    @PutMapping("/{courseId}")
    public ResponseEntity getTeacherName(@PathVariable String courseId){
        return ResponseEntity.status(200).body(teacherService.getTeacherName(courseId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTeacher(@PathVariable String id){

        if(!teacherService.deleteTeacher(id)){
            return ResponseEntity.status(400).body("there is no Teacher");
        }
        return ResponseEntity.status(200).body("Teacher has deleted");
    }

}
