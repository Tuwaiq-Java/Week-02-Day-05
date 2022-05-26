package com.example.excercise9.controller;

import com.example.excercise9.model.Api;
import com.example.excercise9.model.Course;
import com.example.excercise9.service.CourseService;
import com.example.excercise9.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/course")
public class CourseController {

    private final CourseService courseService;


    @GetMapping
    public ResponseEntity<ArrayList<Course>> getCourses() {
        return ResponseEntity.status(200).body(courseService.getCourses());
    }

    @PostMapping
    public ResponseEntity<Api> addCourse(@RequestBody @Valid Course course, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(500).body(new Api(errors.getFieldError().getDefaultMessage(), 500));
        }
        if (!(courseService.isAdd(course))) {
            return ResponseEntity.status(400).body(new Api("Error to add course", 400));
        }
        return ResponseEntity.status(200).body(new Api("Course is added", 200));
    }

    @PutMapping
    public ResponseEntity<Api> updateCourses(@RequestBody @Valid Course course, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(500).body(new Api(errors.getFieldError().getDefaultMessage(), 500));
        }
        if (!(courseService.isUpdate(course))) {
            return ResponseEntity.status(400).body(new Api("Error to update course", 400));
        }
        return ResponseEntity.status(200).body(new Api("Course is updated", 200));
    }

    @DeleteMapping
    public ResponseEntity<Api> deleteCourses(@RequestBody @Valid Course course, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(500).body(new Api(errors.getFieldError().getDefaultMessage(), 500));
        }
        if (!(courseService.isDelete(course))) {
            return ResponseEntity.status(400).body(new Api("Error to delete course", 400));
        }
        return ResponseEntity.status(200).body(new Api("Course is deleted", 200));
    }

    @PostMapping("/add/student")
    public ResponseEntity<Api> addStudentToList(@RequestParam String studentId, @RequestParam String courseId) {

        Integer addCase = courseService.addStudentToList(studentId, courseId);
        switch (addCase) {
            case -1:
                return ResponseEntity.status(400).body(new Api("The user id wrong", 400));
            case 0:
                return ResponseEntity.status(400).body(new Api("The course id wrong", 400));
            case 1:
                return ResponseEntity.status(200).body(new Api("Student is added to the list", 200));
            default:
                return ResponseEntity.status(500).body(new Api("Server error", 500));
        }
    }

    @PostMapping("/add/teacher")
    public ResponseEntity<Api> addTeacherToList(@RequestParam String teacherId, @RequestParam String courseId) {

        Integer addCase = courseService.addTeacherToList(teacherId, courseId);
        switch (addCase) {
            case -1:
                return ResponseEntity.status(400).body(new Api("The teacher id wrong", 400));
            case 0:
                return ResponseEntity.status(400).body(new Api("The course id wrong", 400));
            case 1:
                return ResponseEntity.status(200).body(new Api("Teacher is added to the list", 200));
            default:
                return ResponseEntity.status(500).body(new Api("Server error", 500));
        }
    }

    @PostMapping("/teacher/name")
    public ResponseEntity<Api> getNameTeacher(@RequestParam String coursedId) {

        Integer addCase = courseService.getNameTeacher(coursedId);
        switch (addCase) {
            case 0:
                return ResponseEntity.status(400).body(new Api("The course id wrong", 400));
            case 1:
                return ResponseEntity.status(200).body(new Api("Teacher is added to the list", 200));
            default:
                return ResponseEntity.status(500).body(new Api("Server error", 500));
        }
    }

}
