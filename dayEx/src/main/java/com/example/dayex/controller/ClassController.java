package com.example.dayex.controller;

import com.example.dayex.model.Advisor;
import com.example.dayex.model.Classes;
import com.example.dayex.model.ResponseAPI;
import com.example.dayex.service.AdvisorService;
import com.example.dayex.service.ClassesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/class")
@RequiredArgsConstructor
public class ClassController {
    private final ClassesService classesService;
    @GetMapping
    public ResponseEntity getClasses(){
        return ResponseEntity.status(200).body(classesService.getClasses());
    }
    @PostMapping("/add")
    public ResponseEntity addClasses(@RequestBody @Valid Classes classes, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ResponseAPI(errors.getFieldError().getDefaultMessage(),400));
        }
        classesService.addClasses(classes);
        return ResponseEntity.status(200).body(new ResponseAPI("Class added",200));
    }
    @PutMapping("/update")
    public ResponseEntity updateClasses(@RequestBody @Valid Classes classes, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ResponseAPI(errors.getFieldError().getDefaultMessage(),400));
        }
        if(classesService.updateClass(classes)){
            return ResponseEntity.status(200).body(new ResponseAPI("Class updated",200));
        }
        return ResponseEntity.status(400).body(new ResponseAPI("Class not found",400));
    }
    @DeleteMapping("/delete")
    public ResponseEntity deleteClass(@PathVariable String id){
        if(classesService.deleteClasses(id)){
            return ResponseEntity.status(200).body(new ResponseAPI("Class deleted",200));
        }
        return ResponseEntity.status(400).body(new ResponseAPI("Class not found",400));
    }

}
