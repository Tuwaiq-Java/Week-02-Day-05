package com.example.springday05.controller;

import com.example.springday05.model.Advisor;
import com.example.springday05.service.AdvisorService;
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
        return ResponseEntity.status(200).body(advisorService.getAdvisor());
    }

    @PostMapping
    public ResponseEntity<String> addAdvisor(@RequestBody @Valid Advisor advisor, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        return ResponseEntity.status(200).body(advisorService.addAdvisor(advisor));
    }

    @PutMapping("/{index}")
    public ResponseEntity<String> editAdvisor(@PathVariable int index ,@RequestBody @Valid Advisor advisor,
                                              Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        return ResponseEntity.status(200).body(advisorService.editAdvisor(index,advisor));
    }

    @DeleteMapping("/{index}")
    public ResponseEntity<String> deleteAdvisor(@PathVariable int index){

        return ResponseEntity.status(200).body(advisorService.deleteAdvisor(index));
    }
}
