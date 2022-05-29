package com.example.schoolmanagementsoftware.controller;

import com.example.schoolmanagementsoftware.model.Course;
import com.example.schoolmanagementsoftware.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/course")
public class CoursesController {
    
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<ArrayList<Course>> getCourse(){
        return ResponseEntity.status(200).body(courseService.getAllcourse());
    }

    @PostMapping
    public ResponseEntity postCourse(@RequestBody @Valid Course course, Errors error){

        if (error.hasErrors()){
            return ResponseEntity.status(400).body(error.getFieldError().getDefaultMessage());
        }
        courseService.addCourse(course);
        return ResponseEntity.status(201).body("Welcome to our school!!!!");
    }

    @PutMapping
    public ResponseEntity updateCourse(@RequestBody @Valid Course course, Errors errors ){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        courseService.updateCourse(course);
        return ResponseEntity.status(200).body("Course has updated");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteCourse(@PathVariable String id){

        if(!courseService.deleteCourse(id)){
            return ResponseEntity.status(400).body("there is no course");
        }
        return ResponseEntity.status(200).body("course has deleted");
    }

}
