package com.example.schoolmanagement.conteoller;

import com.example.schoolmanagement.model.Advisor;
import com.example.schoolmanagement.model.Api;
import com.example.schoolmanagement.service.AdvisorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RequestMapping("api/v1/advisor")
@RestController
@RequiredArgsConstructor
public class AdvisorController {

    private final AdvisorService advisorService;
    @GetMapping
    public ResponseEntity<ArrayList<Advisor>> getAdvisor(){

        return ResponseEntity.status(200).body(advisorService.getAdvisors());

    }
    @PostMapping
    public ResponseEntity<Api> addAdivsor(@RequestBody @Valid Advisor advisor, Errors errors){

        if (errors.hasErrors()){

            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }

        boolean isAdvisoradd=advisorService.addAdivsor(advisor);
        if (!isAdvisoradd) {
            return ResponseEntity.status(500).body(new Api("Error adding a Advisor", 500));

        }
        return ResponseEntity.status(200).body(new Api("New Advisor added", 200));
    }


    @PutMapping("{index}")
    public ResponseEntity<Api> editAdvisor(@PathVariable int index,@RequestBody@Valid Advisor advisor, Errors errors){

        if (errors.hasErrors()){

            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }

       advisorService.updateAdvisor(index, advisor);

        return ResponseEntity.status(200).body(new Api(" Teacher update", 200));
    }
    @DeleteMapping("{index}")
    public ResponseEntity<Api> deleteAdvisor(@PathVariable int index){

      advisorService.deleteAdvisor(index);

        return ResponseEntity.status(200).body(new Api(" Teacher delete", 200));
    }
   /* @PutMapping("{sdsoivrlist}")
    public ResponseEntity<Api> sdsoivrlist(@PathVariable String courseid) {
        ArrayList<String> d1=advisorService.advisortList(courseid);

        return ResponseEntity.status(200).body(new Api("The student of list type"+d1,200));


    }*/



}
