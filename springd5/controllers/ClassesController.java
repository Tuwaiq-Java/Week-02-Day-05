package com.example.springd5.controllers;

import com.example.springd5.model.Api;
import com.example.springd5.model.Classes;
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
@RequestMapping("classes")
@AllArgsConstructor
public class ClassesController {

    private final ClassesService classesService;

    @GetMapping
    public ResponseEntity<ArrayList<Classes>> getClasses() {
        return ResponseEntity.status(200).body(classesService.getClasses());
    }

    @PostMapping
    public ResponseEntity<Api> addStudent(@RequestBody @Valid Classes classes1, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean add = classesService.addClasses(classes1);
        if (!add) {
            return ResponseEntity.status(400).body(new Api("Error adding class", 400));
        }
        return ResponseEntity.status(200).body(new Api("Class added", 200));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity<Api> update(@RequestBody @Valid Classes classes1, @PathVariable Integer index, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean isUpdate = classesService.updateClasses(index, classes1);
        if (!isUpdate) {
            return ResponseEntity.status(400).body(new Api(" index not found ", 400));
        }
        return ResponseEntity.status(200).body(new Api(" Class updated ", 200));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity<Api> delete(@PathVariable Integer index) {
        boolean isDelete = classesService.deleteClasses(index);
        if (!isDelete) {
            return ResponseEntity.status(400).body(new Api(" index not found ", 400));
        }
        return ResponseEntity.status(200).body(new Api(" Student deleted ", 200));
    }


}
