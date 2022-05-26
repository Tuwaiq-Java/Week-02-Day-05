package com.example.excercise9.controller;

import com.example.excercise9.model.Api;
import com.example.excercise9.model.Teacher;
import com.example.excercise9.service.CourseService;
import com.example.excercise9.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<ArrayList<Teacher>> getTeachers() {
        return ResponseEntity.status(200).body(teacherService.getTeachers());
    }

    @PostMapping
    public ResponseEntity<Api> addTeachers(@RequestBody @Valid Teacher teacher, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(500).body(new Api(errors.getFieldError().getDefaultMessage(), 500));
        }
        if (!(teacherService.isAdd(teacher))) {
            return ResponseEntity.status(500).body(new Api("Error to add teacher", 500));
        }
        return ResponseEntity.status(200).body(new Api("Teacher is added", 200));
    }

    @PutMapping
    public ResponseEntity<Api> updateTeachers(@RequestBody @Valid Teacher teacher, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(500).body(new Api(errors.getFieldError().getDefaultMessage(), 500));
        }
        if (!(teacherService.isUpdate(teacher))) {
            return ResponseEntity.status(500).body(new Api("Error to update teacher", 500));
        }
        return ResponseEntity.status(200).body(new Api("Teacher is updated", 200));
    }

    @DeleteMapping
    public ResponseEntity<Api> deleteTeachers(@RequestBody @Valid Teacher teacher, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(500).body(new Api(errors.getFieldError().getDefaultMessage(), 500));
        }
        if (!(teacherService.isDelete(teacher))) {
            return ResponseEntity.status(500).body(new Api("Error to delete teacher", 500));
        }
        return ResponseEntity.status(200).body(new Api("Teacher is deleted", 200));
    }

}
