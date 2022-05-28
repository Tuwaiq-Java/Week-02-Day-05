package com.example.school.controller;

import com.example.school.model.Advisor;
import com.example.school.model.Api;
import com.example.school.model.Classes;
import com.example.school.servise.AdvisorService;
import com.example.school.servise.StudentService;
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
    public ResponseEntity<ArrayList<Advisor>> getAdvisor() {
        return ResponseEntity.status(200).body(advisorService.getAdvisor());
    }

    @PostMapping("/add")
    public ResponseEntity<Api> addAdvisor(@RequestBody @Valid Advisor advisor, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean isAdvisor =advisorService.addAdvisor(advisor);
        if (!isAdvisor) {
            return ResponseEntity.status(400).body(new Api("Not Found Advisor !", 400));
        }
        return ResponseEntity.status(201).body(new Api("Advisor added!", 201));
    }

    @PutMapping("/update")
    public ResponseEntity<Api> updateAdvisor(@RequestBody @Valid Advisor advisor,@RequestParam Integer index, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean isAdvisor =advisorService.updateAdvisor(index, advisor);
        if (!isAdvisor) {
            return ResponseEntity.status(400).body(new Api(" Not found Advisor", 400));
        }
        return ResponseEntity.status(200).body(new Api("update Advisor", 200));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Api> deleteAdvisor(@RequestParam String advisorID) {
        boolean isAdvisor =advisorService.deleteAdvisor(advisorID);
        if (!isAdvisor) {
            return ResponseEntity.status(400).body(new Api("not found Advisor id ",400));
        }
        return ResponseEntity.status(200).body(new Api("Advisor deleted!",200));
    }
    ////////////////=============================////////////////////
}
