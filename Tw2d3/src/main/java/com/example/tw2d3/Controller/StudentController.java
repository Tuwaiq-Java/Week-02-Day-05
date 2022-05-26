package com.example.tw2d3.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
@RestController
@RequestMapping("api/v1/student")
 class StudentController {
        ArrayList<Student> students = new ArrayList<>();

        @GetMapping
        public ResponseEntity getStudent() {
            return ResponseEntity.status(200).body(students);
        }

        @PostMapping
        public ResponseEntity addStudent(@RequestBody @Valid Student student, Errors errors) {
            if(errors.hasErrors()){

            String defult=errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(defult);
            }

            students.add(student);
            return ResponseEntity.status(200).body(students);
        }

}
