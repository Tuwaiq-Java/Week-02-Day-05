package com.example.schoolmanagement.conteoller;

import com.example.schoolmanagement.model.Api;
import com.example.schoolmanagement.model.Teacher;
import com.example.schoolmanagement.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RequestMapping("api/v1/teacher")
@RestController
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
    @GetMapping
    public ResponseEntity<ArrayList<Teacher>> getTeacher(){

        return ResponseEntity.status(200).body(teacherService.getTeacher());

    }
    @PostMapping
    public ResponseEntity<Api> addTeacher(@RequestBody@Valid Teacher teacher, Errors errors){

        if (errors.hasErrors()){

            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }

        boolean isTeacheradd=teacherService.addTeacher(teacher);
        if(!isTeacheradd){
            return ResponseEntity.status(500).body(new Api("Error adding a Teacher", 500));

        }
        return ResponseEntity.status(200).body(new Api("New Teacher added", 200));
    }
     @PutMapping("{index}")
     public ResponseEntity<Api> editTeacher(@PathVariable int index,@RequestBody@Valid Teacher teacher, Errors errors){

         if (errors.hasErrors()){

             return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
         }

       teacherService.updateTeacher(index,teacher);

         return ResponseEntity.status(200).body(new Api(" Teacher update", 200));
     }
     @DeleteMapping("{index}")
     public ResponseEntity<Api> deleteTeacher(@PathVariable int index){

         teacherService.deleteTeacher(index);

         return ResponseEntity.status(200).body(new Api(" Teacher delete", 200));
     }
    @PutMapping("/classlistteachear")
    public ResponseEntity<Api> classList(@RequestParam String teacherid, @RequestParam String classid) {
        Integer classList = teacherService.classList(teacherid, classid);

        switch (classList) {
            case -1:
                return ResponseEntity.status(400).body(new Api("class is not valid", 400));
            case 0:
                return ResponseEntity.status(400).body(new Api("teacher id is wrong", 400));

            case 1:
                return ResponseEntity.status(200).body(new Api("class list !" , 200));
            default:
                return ResponseEntity.status(500).body(new Api("Server error", 500));
        }

    }

    @PutMapping("/list/{coursid}")
    public ResponseEntity<Api> techeartList(@PathVariable String courseid) {

        String t1=teacherService.teacherList(courseid);

        return ResponseEntity.status(200).body(new Api("The Teacher of list type"+t1,200));


    }
        }








