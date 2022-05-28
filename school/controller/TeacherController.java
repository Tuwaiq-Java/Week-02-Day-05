package com.example.school.controller;

import com.example.school.model.Api;
import com.example.school.model.Student;
import com.example.school.model.Teacher;
import com.example.school.servise.ClassesService;
import com.example.school.servise.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private final ClassesService classesService;

    @GetMapping
    public ResponseEntity<ArrayList<Teacher>> getTeachers(){
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.getTeacher());
    }

    @PostMapping
    public ResponseEntity<Api> addTeacher(@RequestBody @Valid  Teacher teacher, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }
        boolean isTeacher=teacherService.addTeacher(teacher);
        if (!isTeacher){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Api("Server error",500));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new Api("Teacher added",201));
    }
    @PutMapping("/update")
    public ResponseEntity<Api> updateTeacher(@RequestBody @Valid Teacher teacher ,@RequestParam Integer index, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }
        boolean isTeacher=teacherService.updateTeacher(index,teacher);
        if (!isTeacher){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Not found ",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Teacher update",200));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Api> deleteTeacher(@RequestParam String teacherId){
        boolean isTeacher=teacherService.deleteTeacher(teacherId);
        if (!isTeacher){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Not found Teacher Id ",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Teacher deleted",200));
    }
    ////////////////===========================//////////////////

//    @PutMapping("/classesTeacher")
//    public ResponseEntity<Api> addTeacher(@RequestParam String teacherID ,@RequestParam String classId) {
//        Boolean isTeacher =teacherService.addTeacher(teacherID);
//        if (!isTeacher) {
//            return ResponseEntity.status(500).body(new Api("Not available", 500));
//        }
//        return ResponseEntity.status(200).body(new Api("Teacher added", 200));
//    }
}
