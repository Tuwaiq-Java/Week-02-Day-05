package com.example.SchoolSystem.controller;

import com.example.SchoolSystem.model.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public class Advisor {
    private final advisorService;

    @GetMapping
    public ResponseEntity getAdvisors() {
        return ResponseEntity.status(200).body(advisorService.getAdvisors());
    }

    @PostMapping
    public ResponseEntity addAdvisor(@RequestBody @Valid Advisor advisor, Errors error) {
        if (error.hasFieldErrors()) {
            String err_msg = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new Api(err_msg, 400));
        }
        Boolean isAdvisorAdded = advisorService.addAdvisor(advisor);
        if (!isAdvisorAdded) {
            return ResponseEntity.of().status(400).body(new Api("Advisor not added", 400));
        }
        return ResponseEntity.status(201).body(new Api("Advisor has successfully been added", 201));
    }

    @PostMapping("{adv_id}")
    public ResponseEntity updateAdvisor(@RequestBody @Valid Advisor advisor, @PathVariable String adv_id, Errors error) {
        if (error.hasFieldErrors()) {
            String err_msg = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new Api(err_msg, 400));
        }
        Boolean isAdvisorUpdated = advisorService.updateAdvisor(advisor, adv_id);
        if (!isAdvisorUpdated) {
            return ResponseEntity.status(400).body(new Api("Advisor not updated", 400));
        }
        return ResponseEntity.status(201).body(new Api("Advisor has successfully been updated", 201));

    }

    @DeleteMapping()
    public ResponseEntity deleteAdvisor(@RequestPart String adv_id) {
        Boolean isAdvisorDelete = advisorService.deleteAdvisor(adv_id);
        if (!isAdvisorDelete) {
            return ResponseEntity.status(400).body(new Api("Advisor not deleted", 400));
        }
        return ResponseEntity.status(201).body(new Api("Advisor has successfully been deleted", 201));
    }
}
