package com.demo.Ex5W2.Controller;

import com.demo.Ex5W2.Model.Teacher;
import com.demo.Ex5W2.Model.Student;
import com.demo.Ex5W2.Service.TeacherService;
import com.demo.Ex5W2.Service.StudentService;

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

    //Get
    @GetMapping
    public ResponseEntity<ArrayList<Teacher>> getTeachers() {
        return ResponseEntity.status(200).body(teacherService.getTeachers());
    }

    //Add
    @PostMapping
    public ResponseEntity<String> addTeachers(@RequestBody @Valid Teacher teacher, Errors errors) {

        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isTeacherAdded = teacherService.addTeachers(teacher);
        if(!isTeacherAdded){
            return ResponseEntity.status(500).body("Error adding a teacher");
        }
        return ResponseEntity.status(200).body("New teacher added");
    }

    //Update
    @PutMapping("/{index}")
    public ResponseEntity<String> updateTeachers(@PathVariable Integer index, @RequestBody Teacher teacher){
        boolean isUpdated = teacherService.updateTeachers(index,teacher);
        if (!isUpdated){
            return ResponseEntity.status(400).body("Invalid index ");
        }
        return ResponseEntity.status(200).body("Teacher updated ");
    }

    //Delete
    @DeleteMapping("/{index}")
    public ResponseEntity<String> removeTeachers(@PathVariable Integer index){
       boolean isDeleted = teacherService.removeTeachers(index);
        if (!isDeleted){
            return ResponseEntity.status(400).body("error ");
        }
        return ResponseEntity.status(200).body("Teacher deleted ");
    }
}
