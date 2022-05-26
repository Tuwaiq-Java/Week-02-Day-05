package com.demo.Ex5W2.Controller;

import com.demo.Ex5W2.Model.Student;
import com.demo.Ex5W2.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    //Get
    @GetMapping
    public ResponseEntity<ArrayList<Student>> getStudents() {
        return ResponseEntity.status(200).body(studentService.getStudents());
    }

    //Add
    @PostMapping
    public ResponseEntity<String> addStudents(@RequestBody @Valid Student student, Errors errors) {

        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isStudentAdded = studentService.addStudents(student);
        if(!isStudentAdded){
            return ResponseEntity.status(500).body("Error adding a student");
        }
        return ResponseEntity.status(200).body("New student added");
    }

    //Update
    @PutMapping("/{index}")
    public ResponseEntity<String> updateStudents(@PathVariable Integer index, @RequestBody Student student){
        boolean isUpdated = studentService.updateStudents(index,student);
        if (!isUpdated){
            return ResponseEntity.status(400).body("Invalid index ");
        }
        return ResponseEntity.status(200).body("Student updated ");
    }


    //Delete
    @DeleteMapping("/{index}")
    public ResponseEntity<String> removeStudents(@PathVariable Integer index){
       boolean isDeleted = studentService.removeStudents(index);
        if (!isDeleted){
            return ResponseEntity.status(400).body("error ");
        }
        return ResponseEntity.status(200).body("Student deleted ");
    }
}
