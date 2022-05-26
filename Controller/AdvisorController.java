package com.example.schoolmanagementsoftware.controllers;


import com.example.schoolmanagementsoftware.moles.Advisor;
import com.example.schoolmanagementsoftware.moles.Api;
import com.example.schoolmanagementsoftware.moles.Teacher;
import com.example.schoolmanagementsoftware.services.AdvisorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("student")
@RequiredArgsConstructor
public class AdvisorController {

    private final AdvisorService advisorService;

    @GetMapping
    public ResponseEntity<ArrayList<Advisor>> getAdvisors(){
        return ResponseEntity.status(200).body(advisorService.getAdvisors());
    }

    @PostMapping
    public ResponseEntity<Api> addAdvisors(@RequestBody @Valid Advisor advisor, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new Api(message, 400));
        }
        boolean isAddTeachers = advisorService.addAdvisors(advisor);

        if(!isAddTeachers){
            return ResponseEntity.status(500).body(new Api("Server error", 500));
        }

        return ResponseEntity.status(201).body(new Api("Student added", 201));
    }

    @PutMapping("/update")
    public ResponseEntity<Api> updateAdvisors(@RequestBody @Valid Advisor advisor, Errors errors) {


        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new Api(message, 400));
        }
        boolean isUpdateStudents = advisorService.updateAdvisors(String.valueOf(advisor));

        if (!isUpdateStudents) {
            return ResponseEntity.status(500).body(new Api("Server error", 500));
        }

        return ResponseEntity.status(201).body(new Api("Student update", 201));
    }

    @DeleteMapping()
    public ResponseEntity deleteAdvisor(@RequestParam String adv_id) {
        Boolean isAdvisorDelete = advisorService.deleteAdvisor(adv_id);
        if (!isAdvisorDelete) {
            return ResponseEntity.status(400).body(new Api("Advisor not deleted", 400));
        }
        return ResponseEntity.status(201).body(new Api("Advisor has successfully been deleted", 201));
    }

}
}
