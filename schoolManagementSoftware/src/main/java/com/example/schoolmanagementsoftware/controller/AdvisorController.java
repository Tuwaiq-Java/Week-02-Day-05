package com.example.schoolmanagementsoftware.controller;

import com.example.schoolmanagementsoftware.model.Advisor;
import com.example.schoolmanagementsoftware.service.AdvisorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/advisor")
@RequiredArgsConstructor
public class AdvisorController {

    private final AdvisorService advisorService;

    @GetMapping
    public ResponseEntity<ArrayList<Advisor>> getAdvisor(){
        return ResponseEntity.status(200).body(advisorService.getAllAdvisors());
    }

    @PostMapping
    public ResponseEntity postAdvisor(@RequestBody @Valid Advisor Advisor, Errors error){

        if (error.hasErrors()){
            return ResponseEntity.status(400).body(error.getFieldError().getDefaultMessage());
        }
        advisorService.addAdvisor(Advisor);
        return ResponseEntity.status(201).body("Welcome to our school!!!!");
    }

    @PutMapping
    public ResponseEntity updateAdvisor(@RequestBody @Valid Advisor Advisor, Errors errors ){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        advisorService.updateAdvisor(Advisor);
        return ResponseEntity.status(200).body("Advisor has updated");
    }

    @PutMapping("/{advisorId}")
    public ResponseEntity getStudent(@PathVariable String advisorId){
        if (advisorService.getStudents(advisorId).size()<0){
            return ResponseEntity.status(400).body("There is no students");
        }
        return ResponseEntity.status(200).body(advisorService.getStudents(advisorId));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteAdvisor(@PathVariable String id){

        if(!advisorService.deleteAdvisor(id)){
            return ResponseEntity.status(400).body("there is no Advisor");
        }
        return ResponseEntity.status(200).body("Advisor has deleted");
    }



}
