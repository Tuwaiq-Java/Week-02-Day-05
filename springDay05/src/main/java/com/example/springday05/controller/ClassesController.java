package com.example.springday05.controller;

import com.example.springday05.model.Advisor;
import com.example.springday05.model.Classes;
import com.example.springday05.service.ClassesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/class")
@RequiredArgsConstructor
public class ClassesController {

    private final ClassesService classesService;

    @GetMapping
    public ResponseEntity<ArrayList<Classes>> getClasses(){
        return ResponseEntity.status(200).body(classesService.getClasses());
    }

    @PostMapping
    public ResponseEntity<String> addClasses(@RequestBody @Valid Classes classes, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        return ResponseEntity.status(200).body(classesService.addClasses(classes));
    }

    @PutMapping("/{index}")
    public ResponseEntity<String> editClasses(@PathVariable int index ,@RequestBody @Valid Classes classes,
                                              Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        return ResponseEntity.status(200).body(classesService.editClasses(index,classes));
    }

    @DeleteMapping("/{index}")
    public ResponseEntity<String> deleteClasses(@PathVariable int index){

        return ResponseEntity.status(200).body(classesService.deleteClasses(index));
    }

}
