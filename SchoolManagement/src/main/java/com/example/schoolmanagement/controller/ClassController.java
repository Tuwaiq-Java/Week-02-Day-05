package com.example.schoolmanagement.controller;

import com.example.schoolmanagement.model.Class;
import com.example.schoolmanagement.model.ResponseAPI;
import com.example.schoolmanagement.model.Student;
import com.example.schoolmanagement.model.Teacher;
import com.example.schoolmanagement.service.ClassService;
import com.example.schoolmanagement.service.StudentService;
import com.example.schoolmanagement.service.TeacherService;
import lombok.RequiredArgsConstructor;
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
    private final TeacherService teacherService;
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<ArrayList<Class>> getClasses(){
        return ResponseEntity.status(200).body(classService.getClasses());
    }

    @PostMapping
    public ResponseEntity<ResponseAPI> addClass(@RequestBody @Valid Class clasS, Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ResponseAPI(errors.getFieldError().getDefaultMessage(),400));
        }
        Boolean response = classService.addClass(clasS);
        if (response) {
            return ResponseEntity.status(201).body(new ResponseAPI("Class added!",201));
        }
        return ResponseEntity.status(400).body(new ResponseAPI("Class already registered!",400));
    }

    @PutMapping
    public ResponseEntity<ResponseAPI> updateClass(@RequestBody @Valid Class clasS, Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ResponseAPI(errors.getFieldError().getDefaultMessage(),400));
        }
        Boolean response = classService.editClass(clasS);
        if (response) {
            return ResponseEntity.status(200).body(new ResponseAPI("Class edited!",200));
        }
        return ResponseEntity.status(400).body(new ResponseAPI("Class doesn't exists!",400));
    }

    @DeleteMapping("/{classID}")
    public ResponseEntity<ResponseAPI> deleteClass(@PathVariable String classID) {
        Boolean response = classService.deleteClass(classID);
        if (!response) {
            return ResponseEntity.status(400).body(new ResponseAPI("classID doesn't exists!",400));
        }
        return ResponseEntity.status(200).body(new ResponseAPI("Class deleted!",200));
    }


    @GetMapping("/teacherClasses/{classID}")
    public ResponseEntity<ResponseAPI> getClassByTeacherID(@PathVariable String classID) {
        Class clsss = classService.isClassExist(classID);
        if (clsss == null) {
            return ResponseEntity.status(400).body(new ResponseAPI("classID doesn't exists!",400));
        }
        Teacher teacher = teacherService.getTeacherClass(classID);
        if (teacher == null) {
            return ResponseEntity.status(400).body(new ResponseAPI("Class doesn't have Teacher!",400));
        }

        return ResponseEntity.status(200).body(new ResponseAPI(String.format("Class: %s has teacher %s.", clsss.getName(),teacher.getName()),200));
    }

    @GetMapping("/getStudentList/{classID}")
    public ResponseEntity<Object> getStudentListOfClassID(@PathVariable String classID) {
        Class clsss = classService.isClassExist(classID);
        if (clsss == null) {
            return ResponseEntity.status(400).body(new ResponseAPI("classID doesn't exists!",400));
        }

        ArrayList<Student> studentWithThisClass = new ArrayList<>();
        studentWithThisClass = studentService.getStudentsWithThisClass(classID);
        if (studentWithThisClass.size() == 0) {
            return ResponseEntity.status(400).body(new ResponseAPI("no student are in this class!",400));
        }
        return ResponseEntity.status(200).body(studentWithThisClass);

    }

}
