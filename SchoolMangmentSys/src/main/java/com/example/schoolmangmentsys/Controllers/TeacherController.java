package com.example.schoolmangmentsys.Controllers;


import com.example.schoolmangmentsys.Services.AdvisorService;
import com.example.schoolmangmentsys.Services.TeacherService;
import com.example.schoolmangmentsys.model.Advisor;
import com.example.schoolmangmentsys.model.Api;
import com.example.schoolmangmentsys.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.PanelUI;
import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {

   private final TeacherService teacherService;


    @GetMapping
    public ResponseEntity<ArrayList<Teacher>> getTeachers(){
        return ResponseEntity.status(200).body(teacherService.getTeachers());
    }


    @PostMapping
    public ResponseEntity<Api> addTeachers(@RequestBody @Valid Teacher teacher , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage() , 400));
        }
        Boolean isStuAdded = teacherService.addTeachers(teacher);
        if(!isStuAdded ){

            return ResponseEntity.status(500).body(new Api("error adding a teacher",500));
        }

        return ResponseEntity.status(201).body(new Api("new teacher is added",201));
    }



    @DeleteMapping("/{index}")
    public ResponseEntity<ArrayList<Teacher>> delTeachers(@PathVariable Integer index){
        return ResponseEntity.status(200).body(teacherService.delTeachers(index));

    }


    @PutMapping("index")
    public ResponseEntity<ArrayList<Teacher>> updTeachers(@RequestBody @Valid  ArrayList<String> TeacheraNames, Integer index )
    {
        return ResponseEntity.status(200).body(teacherService.updTeachers(TeacheraNames , index));
    }



    @PostMapping("/add/teacher/classlist")
    public ResponseEntity<Api> addTeacherToClassList(@RequestBody @Valid String teacherId , String classId){
        Integer teacherCase = teacherService.addTeacherToClassList(teacherId, classId);

        return switch (teacherCase) {
            case -1 -> ResponseEntity.status(400).body(new Api("student id or class id should not be null", 400));
            case 0 -> ResponseEntity.status(200).body(new Api("student added succesuly to class ", 200));
            default -> ResponseEntity.status(500).body(new Api("Server erroe", 500));
        };



    }




    @GetMapping("/teachername/class")
    public ResponseEntity<Api> teacherOfClass(String classId , String teacherId){
        Integer teachClassName = teacherService.teacherOfClass(classId , teacherId);

        switch (teachClassName){
            case -1 :
                ResponseEntity.status(400).body(new Api("class id or teacher id should not be null",400));
            case 0 :
                ResponseEntity.status(200).body(new Api("the name of teacher" , 200));
            default:
                ResponseEntity.status(500).body(new Api("server error!!" ,500));
        }
return null;
    }














}
