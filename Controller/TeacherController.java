package com.example.schoolmanagementsoftware.controllers;


import com.example.schoolmanagementsoftware.moles.Api;
import com.example.schoolmanagementsoftware.moles.Student;
import com.example.schoolmanagementsoftware.moles.Teacher;
import com.example.schoolmanagementsoftware.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<ArrayList<Teacher>> getTeachers(){
        return ResponseEntity.status(200).body(teacherService.getTeachers());
    }


    @PostMapping
    public ResponseEntity<Api> addTeachers(@RequestBody @Valid Teacher teacher, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new Api(message, 400));
        }
        boolean isAddTeachers = teacherService.addTeachers(teacher);

        if(!isAddTeachers){
            return ResponseEntity.status(500).body(new Api("Server error", 500));
        }

        return ResponseEntity.status(201).body(new Api("Student added", 201));
    }


    @PutMapping("/update")
    public ResponseEntity<Api> updateTeachers(@RequestBody @Valid Teacher teacher, Errors errors) {


        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new Api(message, 400));
        }
        boolean isUpdateStudents = teacherService.updateTeachers(String.valueOf(teacher));

        if (!isUpdateStudents) {
            return ResponseEntity.status(500).body(new Api("Server error", 500));
        }

        return ResponseEntity.status(201).body(new Api("Student update", 201));
    }


    @DeleteMapping()
    public ResponseEntity deleteTeacher(@RequestParam String adv_id) {
        Boolean isTeacherDelete = teacherService.deleteTeacher(adv_id);
        if (!isTeacherDelete) {
            return ResponseEntity.status(400).body(new Api("Teacher not deleted", 400));
        }
        return ResponseEntity.status(201).body(new Api("Teacher has successfully been deleted", 201));
    }
    @PutMapping("class/add")
    public ResponseEntity addTeacherClass(@RequestParam String idTeacher,@RequestParam String idClass){
        Boolean isTeacherAdded=teacherService.addTeacherClass(idTeacher,idClass);
        if(!isTeacherAdded){
            return ResponseEntity.status(400).body(new Api("Not deleted", 400));
        }
        return ResponseEntity.status(201).body(new Api("Added to the class", 201));

    }
    @GetMapping("class")
    public ResponseEntity getClassTeacher(String idClass){
        String target_teacher= teacherService.getClassTeacher(idClass);
        if(target_teacher==null){
            return ResponseEntity.status(400).body(new Api("Not deleted", 400));

        }
        return ResponseEntity.status(201).body(target_teacher);

    }
}
