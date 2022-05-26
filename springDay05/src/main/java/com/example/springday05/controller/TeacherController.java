package com.example.springday05.controller;

import com.example.springday05.model.Student;
import com.example.springday05.model.Teacher;
import com.example.springday05.service.TeacherService;
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
    public ResponseEntity<ArrayList<Teacher>> getTeacher(){
        return ResponseEntity.status(200).body(teacherService.getTeacher());
    }

    @PostMapping
    public ResponseEntity<String> addTeacher(@RequestBody @Valid Teacher teacher, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        return ResponseEntity.status(200).body(teacherService.addTeacher(teacher));
    }

    @PutMapping("/{index}")
    public ResponseEntity<String> editTeacher(@PathVariable int index ,@RequestBody @Valid Teacher teacher,
                                              Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        return ResponseEntity.status(200).body(teacherService.editTeacher(index,teacher));
    }

    @DeleteMapping("/{index}")
    public ResponseEntity<String> deleteTeacher(@PathVariable int index){

        return ResponseEntity.status(200).body(teacherService.deleteTeacher(index));
    }

    @PutMapping("/addClass/{teacherID}/{classID}")
    public ResponseEntity<String> addTeacherClass(@PathVariable String teacherID, @PathVariable String classID){
        int classStatus = teacherService.addTeacherClass(teacherID,classID);
        switch (classStatus){
            case -1:
                return ResponseEntity.status(400).body("Teacher ID dosen't exist");
            case 0:
                return ResponseEntity.status(400).body("Class ID dosen't exist");
            case 1:
                return ResponseEntity.status(200).body("Teacher add class");
            default:
                return ResponseEntity.status(500).body("some error");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<String> getTeacherName(@PathVariable String id){
        String classTeacher = teacherService.getTeacherName(id);
        return ResponseEntity.status(200).body(classTeacher);
    }

}
