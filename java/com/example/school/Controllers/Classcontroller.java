package com.example.school.Controllers;


import com.example.school.Models.Api;
import com.example.school.Models.Classes;
import com.example.school.Models.Teacher;
import com.example.school.Services.Classservice;
import com.example.school.Services.Teacherservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/classes")
@RequiredArgsConstructor
public class Classcontroller {
    private final Classservice classservice;
    private final Teacherservice teacherservice;

    @PostMapping
    public ResponseEntity<Api> addClass(@RequestBody @Valid Classes classes , Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        if (classservice.isAdd(classes))
        return ResponseEntity.status(200).body(new Api("Added new class",200));

        return ResponseEntity.status(400).body(new Api("This class is already exist",400));

    }

    @GetMapping
    public ResponseEntity<ArrayList<Classes>> getClasses(){

        return ResponseEntity.status(200).body(classservice.getClasses());


    }


    @PutMapping
    public ResponseEntity<Api> updateClass(@RequestBody @Valid Classes classes , Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        if (classservice.isUpdate(classes))
            return ResponseEntity.status(200).body(new Api("Update the class",200));

        return ResponseEntity.status(400).body(new Api("This class is not exist",400));

    }


    @DeleteMapping
    public ResponseEntity<Api> deleteClass(@RequestBody @Valid Classes classes , Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        if (classservice.isDelete(classes))
            return ResponseEntity.status(200).body(new Api("The class Deleted",200));

        return ResponseEntity.status(400).body(new Api("This class is not exist",400));

    }


    @GetMapping("/teachers")
    public ResponseEntity<ArrayList<Teacher>> getTeacher(@RequestBody @Valid String classId){

        return ResponseEntity.status(200).body(teacherservice.getTeachers());


    }


}
