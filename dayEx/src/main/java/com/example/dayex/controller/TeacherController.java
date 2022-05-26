package com.example.dayex.controller;

import com.example.dayex.model.Advisor;
import com.example.dayex.model.ResponseAPI;
import com.example.dayex.model.Teacher;
import com.example.dayex.service.AdvisorService;
import com.example.dayex.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;
    @GetMapping
    public ResponseEntity getTeacher(){
        return ResponseEntity.status(200).body(teacherService.getTeachers());
    }
    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid Teacher teacher, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ResponseAPI(errors.getFieldError().getDefaultMessage(),400));
        }
        teacherService.addTeacher(teacher);
        return ResponseEntity.status(200).body(new ResponseAPI("Teacher added",200));
    }
    @PutMapping("/update")
    public ResponseEntity updateTeacher(@RequestBody @Valid Teacher teacher, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ResponseAPI(errors.getFieldError().getDefaultMessage(),400));
        }
        if(teacherService.updateTeacher(teacher)){
            return ResponseEntity.status(200).body(new ResponseAPI("Teacher updated",200));
        }
        return ResponseEntity.status(400).body(new ResponseAPI("Teacher not found",400));
    }
    @DeleteMapping("/delete")
    public ResponseEntity deleteTeacher(@PathVariable String id){
        if(teacherService.deleteTeacher(id)){
            return ResponseEntity.status(200).body(new ResponseAPI("Teacher deleted",200));
        }
        return ResponseEntity.status(400).body(new ResponseAPI("Teacher not found",400));
    }
    @PutMapping("/addTeacherClass")
    public ResponseEntity addStudentToClass(@RequestParam String teacherID,@RequestParam String classID){
        Integer addStatus = teacherService.addTeacherToClass(teacherID,classID);
        switch (addStatus){
            case -1:
                return ResponseEntity.status(400).body(new ResponseAPI("Teacher not found",400));
            case 0:
                return ResponseEntity.status(400).body(new ResponseAPI("Class not found",400));
            case 1:
                return ResponseEntity.status(200).body(new ResponseAPI("Teacher added to class",200));

        }
        return ResponseEntity.status(500).body(new ResponseAPI("Server Error",500));
    }
    @GetMapping("/getteachers/{classID}")
    public ResponseEntity getTeachers(@PathVariable String classID){
        Teacher teacher = teacherService.getTeachers(classID);
        if(teacher == null){
            return ResponseEntity.status(400).body(new ResponseAPI("Class not found",400));
        }
        return ResponseEntity.status(200).body(teacher);
    }

}
