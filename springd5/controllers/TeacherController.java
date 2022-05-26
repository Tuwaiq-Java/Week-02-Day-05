package com.example.springd5.controllers;

import com.example.springd5.model.Api;
import com.example.springd5.model.Student;
import com.example.springd5.model.Teacher;
import com.example.springd5.services.StudentService;
import com.example.springd5.services.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("teacher")
@AllArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<ArrayList<Teacher>> getTeacher() {
        return ResponseEntity.status(200).body(teacherService.getTeachers());
    }

    @PostMapping
    public ResponseEntity<Api> addTeacher(@RequestBody @Valid Teacher teacher, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean add = teacherService.addTeacher(teacher);
        if (!add) {
            return ResponseEntity.status(400).body(new Api("Error adding Teacher", 400));
        }
        return ResponseEntity.status(200).body(new Api("Teacher added", 200));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity<Api> update(@RequestBody @Valid Teacher teacher, @PathVariable Integer index, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean isUpdate = teacherService.updateTeacher(index, teacher);
        if (!isUpdate) {
            return ResponseEntity.status(400).body(new Api(" index not found ", 400));
        }
        return ResponseEntity.status(200).body(new Api(" Teacher updated ", 200));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity<Api> delete(@PathVariable Integer index) {
        boolean isDelete = teacherService.deleteTeacher(index);
        if (!isDelete) {
            return ResponseEntity.status(400).body(new Api(" index not found ", 400));
        }
        return ResponseEntity.status(200).body(new Api(" Student deleted ", 200));
    }

    @PostMapping ("/addclasses/{teacherid}/{classid}")
    public ResponseEntity<Api> addClasses(@PathVariable String teacherid,@PathVariable String classid){
        int classStatus = teacherService.addClasses(teacherid, classid);
        switch (classStatus) {
            case -1:
                return ResponseEntity.status(400).body(new Api(" Teacher id not found ", 400));
            case 0:
                return ResponseEntity.status(400).body(new Api(" Class id not found ", 400));
            case 1:
                return ResponseEntity.status(200).body(new Api(" Teacher add class  ", 200));
            default:
                return ResponseEntity.status(500).body(new Api(" Server error ", 500));
        }
    }

    @PostMapping("/teacherclass/{classid}/{teacherid}")
    public ResponseEntity<String> teacherClass(@PathVariable String classid,@PathVariable String teacherid){
     int classStatus = teacherService.teacherClass(classid,classid);
        switch (classStatus) {
            case -1:
                return ResponseEntity.status(400).body(new Api(" Teacher id not found ", 400));
            case 0:
                return ResponseEntity.status(400).body(new Api(" Class id not found ", 400));
            case 1:
                return ResponseEntity.status(200).body(new Api(" Teacher add class  ", 200));
            default:
                return ResponseEntity.status(500).body(new Api(" Server error ", 500));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getTeacherName(@PathVariable String id){
        String classTeacher = teacherService.getTeacherName(id);
        return ResponseEntity.status(200).body(classTeacher);
    }
}
