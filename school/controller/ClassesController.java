package com.example.school.controller;

import com.example.school.model.Api;
import com.example.school.model.Classes;
import com.example.school.servise.ClassesService;
import com.example.school.servise.StudentService;
import com.example.school.servise.TeacherService;
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
    private final TeacherService teacherService;
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<ArrayList<Classes>> getClasses() {
        return ResponseEntity.status(200).body(classesService.getClasses());
    }

    @PostMapping("/add")
    public ResponseEntity<Api> addClasses(@RequestBody @Valid Classes classes, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean isClasses = classesService.addClasses(classes);
        if (!isClasses) {
            return ResponseEntity.status(400).body(new Api("Not Found Class !", 400));
        }
        return ResponseEntity.status(400).body(new Api("Class added!", 201));
    }

    @PutMapping("/update")
    public ResponseEntity<Api> updateClass(@RequestBody @Valid Classes classes,@RequestParam Integer index) {
        boolean isClasses = classesService.updateClass(classes, index);
        if (!isClasses) {
            return ResponseEntity.status(400).body(new Api(" Not found class", 400));
        }
        return ResponseEntity.status(200).body(new Api("update class", 200));
    }
    @DeleteMapping("/delete")

    public ResponseEntity<Api> deleteClass(@RequestParam String classID) {
        Boolean isClasses =classesService.deleteClass(classID);
        if (!isClasses) {
            return ResponseEntity.status(400).body(new Api("not found class",400));
        }
        return ResponseEntity.status(200).body(new Api("Class deleted!",200));
    }
    ///////////////////=============================/////////////////

    @PutMapping
    public ResponseEntity<Api> Class(@RequestParam String classID) {
        return null;
    }
}
