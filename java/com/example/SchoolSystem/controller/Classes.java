package com.example.SchoolSystem.controller;

import com.example.SchoolSystem.model.Api;
import com.example.SchoolSystem.service.ClassesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor

public class Classes {
    private final ClassesService classesService;
    @GetMapping
    public ResponseEntity getClasses() {
        return ResponseEntity.status(201).body(classesService.getCourse());
    }
    @PostMapping
    public ResponseEntity addClass(@RequestBody @Valid Classes course, Errors error) {
        if (error.hasFieldErrors()) {
            String err_msg = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new Api(err_msg, 400));
        }
        Boolean isClassAdded = classesService.addClass(course);
        if (!isClassAdded) {
            return ResponseEntity.status(400).body(new Api("Class not added", 400));
        }
        return ResponseEntity.of().status(200).body(new Api("Class has successfully been added", 201));
    }

    @PutMapping("{class_id}")
    public ResponseEntity updateClass(@RequestBody @Valid Classes course, @PathVariable String class_id, Errors error) {
        if (error.hasFieldErrors()) {
            String err_msg = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new Api(err_msg, 400));
        }
        Boolean isClassUpdated = classesService.updateClass(course, class_id);
        if (!isClassUpdated) {
            return ResponseEntity.status(400).body(new Api("Class not updated", 400));
        }
        return ResponseEntity.status(201).body(new Api("Class has successfully been updated", 201));

    }

    @DeleteMapping()
    public ResponseEntity deleteClass(@RequestPart String class_id) {
        Boolean isClassDelete = classesService.deleteClass(class_id);
        if (!isClassDelete) {
            return ResponseEntity.status(400).body(new Api("Class not deleted", 400));
        }
        return ResponseEntity.status(201).body(new Api("Class has successfully been deleted", 201));
    }
}
