package com.example.school.Controllers;


import com.example.school.Models.Api;
import com.example.school.Models.Student;
import com.example.school.Models.Teacher;
import com.example.school.Services.Teacherservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/teacher")
@RequiredArgsConstructor
public class Teachercontroller {
    private final Teacherservice teacherservice;

    @PostMapping
    public ResponseEntity<Api> addStudent(@RequestBody @Valid Teacher teacher , Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(500).body(new Api(errors.getFieldError().getDefaultMessage(),500));

        if (teacherservice.isAdd(teacher))
            return ResponseEntity.status(200).body(new Api("Added new Teacher",200));

        return ResponseEntity.status(400).body(new Api("This Teacher is already exist",400));

    }


    @GetMapping
    public ResponseEntity<ArrayList<Teacher>> getClasses(){

        return ResponseEntity.status(200).body(teacherservice.getTeachers());


    }


    @PutMapping
    public ResponseEntity<Api> updateClass(@RequestBody @Valid Teacher teacher , Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        if (teacherservice.isUpdate(teacher))
            return ResponseEntity.status(200).body(new Api("Update the teacher",200));

        return ResponseEntity.status(400).body(new Api("This teacher is not exist",400));

    }


    @DeleteMapping
    public ResponseEntity<Api> deleteClass(@RequestBody @Valid Teacher teacher , Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        if (teacherservice.isDelete(teacher))
            return ResponseEntity.status(200).body(new Api("The teacher Deleted",200));

        return ResponseEntity.status(400).body(new Api("This teacher is not exist",400));

    }

//    @PostMapping("/addcourse/{teachertId}/{classId}")
//    public ResponseEntity<Api> addCourse(@PathVariable String teachertId, @PathVariable String classId){
//
//        teacherservice.addCourses(teachertId,classId);
//        return ResponseEntity.status(HttpStatus.OK).body(new Api("Added new course ",200));
//
//    }

    @PutMapping("/addcourse/{teachertId}/{classId}")
    public ResponseEntity<Api> addCourse(@PathVariable String teachertId, @PathVariable String classId){

        Integer number=teacherservice.addCourses(teachertId,classId);
        switch (number){
            case -1:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("This teacher is not exit",400));
            case 0:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("This class is not exit",400));
            case 2:
                return ResponseEntity.status(HttpStatus.CREATED).body(new Api("This class added to teacher",200));
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("This teacher is not exit",400));
        }

    }



    @GetMapping("/teachename{classid}")
    public ResponseEntity<Api> TeacherName(@PathVariable String classid){
       String TeacherName= teacherservice.TeacherName(classid);
       if (TeacherName==null)
           return ResponseEntity.status(400).body(new Api("This class no have a teacher ",400));
       else
           return ResponseEntity.status(200).body(new Api("Teacher Name is :"+ TeacherName +"",200));



    }




}

