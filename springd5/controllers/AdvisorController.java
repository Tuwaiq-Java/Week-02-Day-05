package com.example.springd5.controllers;

import com.example.springd5.model.Advisor;
import com.example.springd5.model.Api;
import com.example.springd5.model.Student;
import com.example.springd5.services.AdvisorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("advisor")
@AllArgsConstructor
public class AdvisorController {

    private final AdvisorService advisorService;

    @GetMapping
    public ResponseEntity<ArrayList<Advisor>> getAdvisor() {
        return ResponseEntity.status(200).body(advisorService.getAdvisor());
    }

    @PostMapping
    public ResponseEntity<Api> addAdvisor(@RequestBody @Valid Advisor advisor, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean add = advisorService.addAdvisor(advisor);
        if (!add) {
            return ResponseEntity.status(400).body(new Api("Error adding student", 400));
        }
        return ResponseEntity.status(200).body(new Api("Student added", 200));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity<Api> update(@RequestBody @Valid Advisor advisor, @PathVariable Integer index, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean isUpdate = advisorService.updateAdvisor(index, advisor);
        if (!isUpdate) {
            return ResponseEntity.status(400).body(new Api(" index not found ", 400));
        }
        return ResponseEntity.status(200).body(new Api(" Advisor updated ", 200));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity<Api> delete(@PathVariable Integer index) {
        boolean isDelete = advisorService.deleteAdvisor(index);
        if (!isDelete) {
            return ResponseEntity.status(400).body(new Api(" index not found ", 400));
        }
        return ResponseEntity.status(200).body(new Api(" Student deleted ", 200));
    }
}
