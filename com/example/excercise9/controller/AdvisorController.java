package com.example.excercise9.controller;

import com.example.excercise9.model.Advisor;
import com.example.excercise9.model.Api;
import com.example.excercise9.model.Course;
import com.example.excercise9.model.Student;
import com.example.excercise9.service.AdvisorService;
import com.example.excercise9.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/advisor")
public class AdvisorController {

    private final AdvisorService advisorService;

    @GetMapping
    public ResponseEntity<ArrayList<Advisor>> getAdvisors(){
        return ResponseEntity.status(200).body(advisorService.getAdvisors());
    }

    @PostMapping
    public ResponseEntity<Api> addAdvisor(@RequestBody @Valid Advisor advisor, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(500).body(new Api(errors.getFieldError().getDefaultMessage(),500));
        }
        if(!(advisorService.isAdd(advisor))){
            return ResponseEntity.status(500).body(new Api("Error to add advisor",500));
        }
        return ResponseEntity.status(200).body(new Api("Advisor is added",200));
    }

    @PutMapping
    public ResponseEntity<Api> updateAdvisor(@RequestBody @Valid Advisor advisor, Errors errors) {
        if(errors.hasErrors()){
            return ResponseEntity.status(500).body(new Api(errors.getFieldError().getDefaultMessage(),500));
        }
        if(!(advisorService.isUpdate(advisor))){
            return ResponseEntity.status(400).body(new Api("Error to update advisor",400));
        }
        return ResponseEntity.status(200).body(new Api("Advisor is updated",200));
    }

    @DeleteMapping
    public ResponseEntity<Api> deleteAdvisor(@RequestBody @Valid Advisor advisor, Errors errors) {
        if(errors.hasErrors()){
            return ResponseEntity.status(500).body(new Api(errors.getFieldError().getDefaultMessage(),500));
        }
        if(!(advisorService.isDelete(advisor))){
            return ResponseEntity.status(500).body(new Api("Error to delete advisor",500));
        }
        return ResponseEntity.status(200).body(new Api("Advisor is deleted",200));
    }


}
