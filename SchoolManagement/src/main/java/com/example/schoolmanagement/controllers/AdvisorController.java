package com.example.schoolmanagement.controllers;

import com.example.schoolmanagement.models.Advisor;
import com.example.schoolmanagement.models.Api;
import com.example.schoolmanagement.models.Student;
import com.example.schoolmanagement.service.AdvisorService;
import com.example.schoolmanagement.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController @RequestMapping("api/v1/advisor") @RequiredArgsConstructor
public class AdvisorController {
    private final AdvisorService advisorService;

    @GetMapping("/")
    public ResponseEntity getAdvisors(){
        return ResponseEntity.status(200).body(advisorService.getAdvisor());
    }
    @PostMapping("/")
    public ResponseEntity addAdvisor(@RequestBody @Valid Advisor adv, Errors error){
        if(error.hasErrors()){
            return ResponseEntity.status(400).body(new Api(error.getFieldError().getDefaultMessage(),400));
        }
        advisorService.addAdvisor(adv);
        return ResponseEntity.status(201).body(new Api("Successfully added.",201));
    }
    @PutMapping("/update/{index}")
    public ResponseEntity updateAdvisor(@RequestBody @Valid Advisor adv, Errors error, @PathVariable Integer index){
        if(error.hasErrors()){
            return ResponseEntity.status(400).body(new Api(error.getFieldError().getDefaultMessage(),400));
        }
        if(advisorService.updateAdvisor(adv, index)){
            return ResponseEntity.status(201).body(new Api("Successfully updated.",201));
        }
        return ResponseEntity.status(400).body(new Api("Invalid Index.",400));
    }

    @DeleteMapping ("/{index}")
    public ResponseEntity deleteAdvisor(@PathVariable Integer index){
        if(advisorService.deleteAdvisor(index)){
            return ResponseEntity.status(201).body(new Api("Successfully deleted.",201));
        }
        return ResponseEntity.status(400).body(new Api("Invalid Index.",400));
    }

//    @PostMapping("/add/class")
//    public ResponseEntity addClass(@RequestParam String classId,@RequestParam String advId){
//        Integer addStatus = advisorService.addClass(classId, advId);
//        if(addStatus == 0){
//            return ResponseEntity.status(201).body(new Api("Successfully added class.",201));
//        }else if(addStatus == -1){
//            return ResponseEntity.status(400).body(new Api("Invalid class id.",201));
//        }
//        return ResponseEntity.status(400).body(new Api("Invalid student id.",400));
//    }


}
