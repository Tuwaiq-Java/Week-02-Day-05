package com.demo.Ex5W2.Controller;

import com.demo.Ex5W2.Model.Class;
import com.demo.Ex5W2.Service.ClassService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/class")
@RequiredArgsConstructor
public class ClassController {

    private final ClassService classService;

    //Get
    @GetMapping
    public ResponseEntity<ArrayList<Class>> getClasses() {
        return ResponseEntity.status(200).body(classService.getClasses());
    }

    //Add
    @PostMapping
    public ResponseEntity<String> addClasses(@RequestBody @Valid Class c, Errors errors) {

        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isClassAdded = classService.addClasses(c);
        if(!isClassAdded){
            return ResponseEntity.status(500).body("Error adding a class");
        }
        return ResponseEntity.status(200).body("New class added");
    }

    //Update
    @PutMapping("/{index}")
    public ResponseEntity<String> updateClasses(@PathVariable Integer index, @RequestBody Class c){
        boolean isUpdated = classService.updateClasses(index,c);
        if (!isUpdated){
            return ResponseEntity.status(400).body("Invalid index ");
        }
        return ResponseEntity.status(200).body("class updated ");
    }

    //Delete
    @DeleteMapping("/{index}")
    public ResponseEntity<String> removeClasses(@PathVariable Integer index){
       boolean isDeleted = classService.removeClasses(index);
        if (!isDeleted){
            return ResponseEntity.status(400).body("error ");
        }
        return ResponseEntity.status(200).body("Class deleted ");
    }
}
