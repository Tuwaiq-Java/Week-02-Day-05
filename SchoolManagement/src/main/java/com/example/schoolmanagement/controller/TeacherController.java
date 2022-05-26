package com.example.schoolmanagement.controller;

import com.example.schoolmanagement.model.Class;
import com.example.schoolmanagement.model.ResponseAPI;
import com.example.schoolmanagement.model.Student;
import com.example.schoolmanagement.model.Teacher;
import com.example.schoolmanagement.service.ClassService;
import com.example.schoolmanagement.service.StudentService;
import com.example.schoolmanagement.service.TeacherService;
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
    private final ClassService classService;

    @GetMapping
    public ResponseEntity<ArrayList<Teacher>> getTeachers(){
        return ResponseEntity.status(200).body(teacherService.getTeacher());
    }

    @PostMapping
    public ResponseEntity<ResponseAPI> addTeacher(@RequestBody @Valid Teacher teacher, Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ResponseAPI(errors.getFieldError().getDefaultMessage(),400));
        }
        Boolean response = teacherService.addTeacher(teacher);
        if (response) {
            return ResponseEntity.status(201).body(new ResponseAPI("Teacher added!",201));
        }
        return ResponseEntity.status(400).body(new ResponseAPI("Teacher already registered!",400));
    }

    @PutMapping
    public ResponseEntity<ResponseAPI> updateTeacher(@RequestBody @Valid Teacher teacher, Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ResponseAPI(errors.getFieldError().getDefaultMessage(),400));
        }
        Boolean response = teacherService.editTeacher(teacher);
        if (response) {
            return ResponseEntity.status(200).body(new ResponseAPI("Teacher edited!",200));
        }
        return ResponseEntity.status(400).body(new ResponseAPI("teacherID doesn't exists!",400));
    }

    @DeleteMapping("/{teacherID}")
    public ResponseEntity<ResponseAPI> deleteTeacher(@PathVariable String teacherID) {
        Boolean response = teacherService.deleteTeacher(teacherID);
        if (!response) {
            return ResponseEntity.status(400).body(new ResponseAPI("teacherID doesn't exists!",400));
        }
        return ResponseEntity.status(200).body(new ResponseAPI("Teacher deleted!",200));
    }

    @PutMapping("/addClass/{teacherID}/{classID}")
    public ResponseEntity<ResponseAPI> addClass(@PathVariable String teacherID, @PathVariable String classID) {
        Teacher teacher = teacherService.isTeacherExist(teacherID);
        if (teacher == null) {
            return ResponseEntity.status(400).body(new ResponseAPI("teacherID doesn't exists!",400));
        }
        Class classs = classService.isClassExist(classID);
        if (classs == null) {
            return ResponseEntity.status(400).body(new ResponseAPI("classID doesn't exists!",400));
        }
        ArrayList<Class> newTeacherClasses= teacher.getClassList();
        newTeacherClasses.add(classs);
        teacher.setClassList(newTeacherClasses);
        return ResponseEntity.status(200).body(new ResponseAPI(String.format("Class: %s has been added to %s.", classs.getName(),teacher.getName()),200));
    }
}
