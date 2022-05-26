package com.example.schoolmanagement.controllers;

import com.example.schoolmanagement.models.Api;
import com.example.schoolmanagement.models.Classes;
import com.example.schoolmanagement.models.Student;
import com.example.schoolmanagement.service.ClassService;
import com.example.schoolmanagement.service.StudentService;
import com.example.schoolmanagement.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController @RequestMapping("api/v1/class") @RequiredArgsConstructor
public class ClassController {
    private final ClassService classesService;
    private final StudentService studentService;
    private final TeacherService teacherService;

    @GetMapping("/")
    public ResponseEntity getClasses(){
        return ResponseEntity.status(200).body(classesService.getClasses());
    }
    @PostMapping("/")
    public ResponseEntity addClass(@RequestBody @Valid Classes myClass, Errors error){
        if(error.hasErrors()){
            return ResponseEntity.status(400).body(new Api(error.getFieldError().getDefaultMessage(),400));
        }
        classesService.addClass(myClass);
        return ResponseEntity.status(201).body(new Api("Successfully added.",201));
    }
    @PutMapping("/update/{index}")
    public ResponseEntity updateClass(@RequestBody @Valid Classes myClass, Errors error, @PathVariable Integer index){
        if(error.hasErrors()){
            return ResponseEntity.status(400).body(new Api(error.getFieldError().getDefaultMessage(),400));
        }
        if(classesService.updateClass(myClass, index)){
            return ResponseEntity.status(201).body(new Api("Successfully updated.",201));
        }
        return ResponseEntity.status(400).body(new Api("Invalid Index.",400));
    }

    @DeleteMapping ("/{index}")
    public ResponseEntity deleteClass(@PathVariable Integer index){
        if(classesService.deleteClass(index)){
            return ResponseEntity.status(201).body(new Api("Successfully deleted.",201));
        }
        return ResponseEntity.status(400).body(new Api("Invalid Index.",400));
    }
    @GetMapping("find/teachers")
    public ResponseEntity findTeacher(String classId){
        if(teacherService.findTeacherByClass(classId).equals("No teacher was found")){
            return ResponseEntity.status(400).body(new Api("No teacher was found.",400));
        }
        return ResponseEntity.status(200).body(new Api(teacherService.findTeacherByClass(classId)+ " was found",200));

    }
    @GetMapping("find/students")
    public ResponseEntity getStudentsById(String classId){
        if(studentService.getStudentsByClassId(classId) == null){
            return ResponseEntity.status(400).body(new Api("No student was found.",400));
        }
        return ResponseEntity.status(200).body(studentService.getStudentsByClassId(classId));

    }



}
