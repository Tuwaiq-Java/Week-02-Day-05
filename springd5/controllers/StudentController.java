package com.example.springd5.controllers;

import com.example.springd5.model.Api;
import com.example.springd5.model.Student;
import com.example.springd5.services.ClassesService;
import com.example.springd5.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("student")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final ClassesService classesService;

    @GetMapping
    public ResponseEntity<ArrayList<Student>> getStudent() {
        return ResponseEntity.status(200).body(studentService.getStudent());
    }

    @PostMapping
    public ResponseEntity<Api> addStudent(@RequestBody @Valid Student student, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean add = studentService.addStudent(student);
        if (!add) {
            return ResponseEntity.status(400).body(new Api("Error adding student", 400));
        }
        return ResponseEntity.status(200).body(new Api("Student added", 200));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity<Api> update(@RequestBody @Valid Student student, @PathVariable Integer index, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean isUpdate = studentService.updateStudent(index, student);
        if (!isUpdate) {
            return ResponseEntity.status(400).body(new Api(" index not found ", 400));
        }
        return ResponseEntity.status(200).body(new Api(" Student updated ", 200));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity<Api> delete(@PathVariable Integer index) {
        boolean isDelete = studentService.deleteStudent(index);
        if (!isDelete) {
            return ResponseEntity.status(400).body(new Api(" index not found ", 400));
        }
        return ResponseEntity.status(200).body(new Api(" Student deleted ", 200));
    }

    @PostMapping ("/addclasses/{studentid}/{classid}")
    public ResponseEntity<Api> addClasses(@PathVariable String studentid,@PathVariable String classid) {
        int classStatus = studentService.addClasses(studentid, classid);
        switch (classStatus) {
            case -1:
                return ResponseEntity.status(400).body(new Api(" Student id not found ", 400));
            case 0:
                return ResponseEntity.status(400).body(new Api(" Class id not found ", 400));
            case 1:
                return ResponseEntity.status(200).body(new Api(" Student add class  ", 200));
            default:
                return ResponseEntity.status(500).body(new Api(" Server error ", 500));
        }
    }

    @PostMapping("/change/{studentid}/{major}")
    public ResponseEntity<String> changeMajor(@PathVariable String studentid,@PathVariable String major){
        int majorStatus = studentService.changeMajor(studentid, major);
        switch (majorStatus){
            case 0:
                return ResponseEntity.status(400).body("Student ID not found");
            case 1:
                return ResponseEntity.status(200).body("major changed");
            default:
                return ResponseEntity.status(500).body("some error");
        }
    }

}
