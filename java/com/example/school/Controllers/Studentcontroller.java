package com.example.school.Controllers;


import com.example.school.Models.Api;
import com.example.school.Models.Classes;
import com.example.school.Models.Student;
import com.example.school.Models.Teacher;
import com.example.school.Services.Advisorservice;
import com.example.school.Services.Studentservice;
import com.example.school.Services.Teacherservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
public class Studentcontroller {
    private final Studentservice studentservice;



    @GetMapping
    public ResponseEntity<ArrayList<Student>> getStudents(){

        return ResponseEntity.status(200).body(studentservice.getStudents());


    }

    @PostMapping
    public ResponseEntity<Api> addStudent(@RequestBody @Valid Student student , Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));

        if (studentservice.isAdd(student))
            return ResponseEntity.status(200).body(new Api("Added new Student",200));

        return ResponseEntity.status(400).body(new Api("This Student is already exist",400));

    }





    @PutMapping
    public ResponseEntity<Api> updateClass(@RequestBody @Valid Student student , Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        if (studentservice.isUpdate(student))
            return ResponseEntity.status(200).body(new Api("Update the teacher",200));

        return ResponseEntity.status(400).body(new Api("This teacher is not exist",400));

    }


    @DeleteMapping
    public ResponseEntity<Api> deleteClass(@RequestBody @Valid Student student , Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        if (studentservice.isDelete(student))
            return ResponseEntity.status(200).body(new Api("The Student Deleted",200));

        return ResponseEntity.status(400).body(new Api("This Student is not exist",400));

    }

    @PutMapping("/addcourse/{studentId}/{classId}")
    public ResponseEntity<Api> addCourse(@PathVariable String studentId, @PathVariable String classId){

        Integer number=studentservice.addCourses(studentId,classId);
        switch (number){
            case -1:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("This student is not exit",400));
            case 0:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("This class is not exit",400));
            case 2:
                return ResponseEntity.status(HttpStatus.CREATED).body(new Api("This class added to student",200));
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("This student is not exit",400));
        }

        }



    @DeleteMapping("/deletemajor/{major}")
    public ResponseEntity<Api> deleteClass(@PathVariable String major){
        if(studentservice.isDelete(major))
            return ResponseEntity.status(200).body(new Api("The Student Deleted",200));

        return ResponseEntity.status(400).body(new Api("The Major is not exist",400));


    }


    @GetMapping("/studentname/{classid}")
    public ResponseEntity<ArrayList<Student>> StudentsName(@PathVariable String classid){
        ArrayList<Student> students=new ArrayList<>();
        for (Student student: studentservice.getStudents() ) {
            if (student.StudentByid(classid)!=null)
                students.add(student.StudentByid(classid));
        }
        if (students==null)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(students);

        return ResponseEntity.status(HttpStatus.OK).body(students);



    }







}






