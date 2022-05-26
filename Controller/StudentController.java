package com.example.schoolmanagementsoftware.controllers;

import com.example.schoolmanagementsoftware.moles.Api;
import com.example.schoolmanagementsoftware.moles.Student;
import com.example.schoolmanagementsoftware.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<ArrayList<Student>> getStudents() {
        return ResponseEntity.status(200).body(studentService.getStudents());
    }


    @PostMapping
    public ResponseEntity<Api> addStudents(@RequestBody @Valid Student student, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new Api(message, 400));
        }
        boolean isAddStudents = studentService.addStudents(student);

        if (!isAddStudents) {
            return ResponseEntity.status(500).body(new Api("Server error", 500));
        }

        return ResponseEntity.status(201).body(new Api("Student added", 201));
    }


    @PutMapping("/update")
    public ResponseEntity<Api> updateStudents(@RequestBody @Valid Student student, Errors errors) {


     if (errors.hasErrors()) {
        String message = errors.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(new Api(message, 400));
    }
    boolean isUpdateStudents = studentService.updateStudents(String.valueOf(student));

        if (!isUpdateStudents) {
        return ResponseEntity.status(500).body(new Api("Server error", 500));
    }

        return ResponseEntity.status(201).body(new Api("Student update", 201));
    }


    @DeleteMapping()
    public ResponseEntity deleteStudent(@RequestParam String stu_id) {
        Boolean isStudentDelete = studentService.deleteStudent(stu_id);
        if (!isStudentDelete) {
            return ResponseEntity.status(400).body(new Api("Student not found", 400));
        }
        return ResponseEntity.status(201).body(new Api("Student has successfully been deleted", 201));
    }


    @PutMapping("class/add")
    public ResponseEntity addStudentClass(@RequestParam String stu_id,@RequestParam String class_id){
        Boolean isStudentAdded = studentService.addStudentClass(stu_id,class_id);
        if(!isStudentAdded){
            return ResponseEntity.status(400).body(new Api("Student not added", 400));
        }
        return  ResponseEntity.status(201).body(new Api("Student has successfully been added", 201));
    }

    @PutMapping("major/change")
    public ResponseEntity changeMajor(@RequestParam String stu_id,@RequestParam String new_major ){
        boolean isMajorChange = studentService.changeMajor(stu_id,new_major);

        if(!isMajorChange){
            return ResponseEntity.status(400).body(new Api("Major not changed", 400));
        }
        return  ResponseEntity.status(201).body(new Api("Major has successfully been added", 201));
    }

    @GetMapping("class/students")
    public ResponseEntity getClassStudents(@RequestParam String class_id){
        ArrayList<Student>filtered_stulist = studentService.getClassStudents(class_id);
        if(filtered_stulist==null){
            return ResponseEntity.status(400).body(new Api("Major not change", 400));
        }
        return ResponseEntity.status(200).body(filtered_stulist);
    }

    @GetMapping("advisor/students")
    public ResponseEntity getAdvisorStudents(@RequestParam String adv_id){
        ArrayList<Student>filtered_stulist = studentService.getAdvisorStudents(adv_id);
        if(filtered_stulist==null){
            return ResponseEntity.status(400).body(new Api("No advisor found", 400));
        }
        return ResponseEntity.status(200).body(filtered_stulist);
    }
}
}
