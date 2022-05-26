package com.example.schoolmanagement.controller;

import com.example.schoolmanagement.model.Advisor;
import com.example.schoolmanagement.model.Class;
import com.example.schoolmanagement.model.ResponseAPI;
import com.example.schoolmanagement.model.Student;
import com.example.schoolmanagement.service.AdvisorService;
import com.example.schoolmanagement.service.StudentService;
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
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<ArrayList<Advisor>> getAdvisors(){
        return ResponseEntity.status(200).body(advisorService.getAdvisors());
    }

    @PostMapping
    public ResponseEntity<ResponseAPI> addAdvisor(@RequestBody @Valid Advisor advisor, Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ResponseAPI(errors.getFieldError().getDefaultMessage(),400));
        }
        Boolean response = advisorService.addAdvisor(advisor);
        if (response) {
            return ResponseEntity.status(201).body(new ResponseAPI("Advisor added!",201));
        }
        return ResponseEntity.status(400).body(new ResponseAPI("Advisor already registered!",400));
    }

    @PutMapping
    public ResponseEntity<ResponseAPI> updateAdvisor(@RequestBody @Valid Advisor advisor, Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ResponseAPI(errors.getFieldError().getDefaultMessage(),400));
        }
        Boolean response = advisorService.editAdvisor(advisor);
        if (response) {
            return ResponseEntity.status(200).body(new ResponseAPI("Advisor edited!",200));
        }
        return ResponseEntity.status(400).body(new ResponseAPI("advisorID doesn't exists!",400));
    }

    @DeleteMapping("/{advisorID}")
    public ResponseEntity<ResponseAPI> deleteAdvisor(@PathVariable String advisorID) {
        Boolean response = advisorService.deleteAdvisor(advisorID);
        if (!response) {
            return ResponseEntity.status(400).body(new ResponseAPI("advisorID doesn't exists!",400));
        }
        return ResponseEntity.status(200).body(new ResponseAPI("Advisor deleted!",200));
    }

    @GetMapping("/getStudentList/{advisorID}")
    public ResponseEntity<Object> getStudentListOfAdvisorID(@PathVariable String advisorID) {
        Advisor advisor = advisorService.returnIsAdvisorExists(advisorID);
        if (advisor == null) {
            return ResponseEntity.status(400).body(new ResponseAPI("advisorID doesn't exists!",400));
        }

        ArrayList<Student> studentWithThisAdvisor = new ArrayList<>();
        studentWithThisAdvisor = studentService.getStudentsWithThisAdvisor(advisorID);
        if (studentWithThisAdvisor.size() == 0) {
            return ResponseEntity.status(400).body(new ResponseAPI(String.format("no student has this advisor: %s!",advisorID),400));
        }
        return ResponseEntity.status(200).body(studentWithThisAdvisor);

    }
}
