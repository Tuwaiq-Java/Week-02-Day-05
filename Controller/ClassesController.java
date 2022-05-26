package com.example.schoolmanagementsoftware.controllers;

import com.example.schoolmanagementsoftware.moles.Api;
import com.example.schoolmanagementsoftware.moles.Classes;
import com.example.schoolmanagementsoftware.moles.Teacher;
import com.example.schoolmanagementsoftware.services.ClassesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("Class")
@RequiredArgsConstructor
public class ClassesController {

    private final ClassesService classesService;

    @GetMapping
    public ResponseEntity<ArrayList<Classes>> getClasses1(){
        return ResponseEntity.status(200).body(classesService.getClasses1());
    }

    @PostMapping
    public ResponseEntity<Api> addClasses1(@RequestBody @Valid Classes classes, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new Api(message, 400));
        }
        boolean isAddClasses1 = classesService.addClasses1(classes);

        if(!isAddClasses1){
            return ResponseEntity.status(500).body(new Api("Server error", 500));
        }

        return ResponseEntity.status(201).body(new Api("Student added", 201));
    }

    @PutMapping("/update")
    public ResponseEntity<Api> updateClasses1(@RequestBody @Valid Classes classes, Errors errors) {


        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new Api(message, 400));
        }
        boolean isUpdateStudents = classesService.updateClasses1(String.valueOf(classes));

        if (!isUpdateStudents) {
            return ResponseEntity.status(500).body(new Api("Server error", 500));
        }

        return ResponseEntity.status(201).body(new Api("Student update", 201));
    }

    @DeleteMapping()
    public ResponseEntity deleteClasses1(@RequestParam String idClass) {
        Boolean isClassDelete = classesService.deleteClasses1(idClass);
        if (!isClassDelete) {
            return ResponseEntity.status(400).body(new Api("Not deleted", 400));
        }
        return ResponseEntity.status(201).body(new Api("Deleted", 201));
    }
}
