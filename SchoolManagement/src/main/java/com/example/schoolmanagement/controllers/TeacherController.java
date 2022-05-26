package com.example.schoolmanagement.controllers;

import com.example.schoolmanagement.models.Api;
import com.example.schoolmanagement.models.Student;
import com.example.schoolmanagement.models.Teacher;
import com.example.schoolmanagement.service.StudentService;
import com.example.schoolmanagement.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController @RequestMapping("api/v1/teacher") @RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("/")
    public ResponseEntity getTeachers(){
        return ResponseEntity.status(200).body(teacherService.getTeachers());
    }
    @PostMapping("/")
    public ResponseEntity addTeacher(@RequestBody @Valid Teacher tea, Errors error){
        if(error.hasErrors()){
            return ResponseEntity.status(400).body(new Api(error.getFieldError().getDefaultMessage(),400));
        }
        teacherService.addTeacher(tea);
        return ResponseEntity.status(201).body(new Api("Successfully added.",201));
    }
    @PutMapping("/update/{index}")
    public ResponseEntity updateTeacher(@RequestBody @Valid Teacher tea, Errors error, @PathVariable Integer index){
        if(error.hasErrors()){
            return ResponseEntity.status(400).body(new Api(error.getFieldError().getDefaultMessage(),400));
        }
        if(teacherService.updateTeacher(tea, index)){
            return ResponseEntity.status(201).body(new Api("Successfully updated.",201));
        }
        return ResponseEntity.status(400).body(new Api("Invalid Index.",400));
    }

    @DeleteMapping ("/{index}")
    public ResponseEntity deleteStudent(@PathVariable Integer index){
        if(teacherService.deleteTeacher(index)){
            return ResponseEntity.status(201).body(new Api("Successfully deleted.",201));
        }
        return ResponseEntity.status(400).body(new Api("Invalid Index.",400));
    }

    @PostMapping("/add/class")
    public ResponseEntity addClass(@RequestParam String classId,@RequestParam String teaId){
        Integer addStatus = teacherService.addClass(classId, teaId);
        if(addStatus == 0){
            return ResponseEntity.status(201).body(new Api("Successfully added class.",201));
        }else if(addStatus == -1){
            return ResponseEntity.status(400).body(new Api("Invalid class id.",201));
        }
        return ResponseEntity.status(400).body(new Api("Invalid student id.",400));
    }


}
