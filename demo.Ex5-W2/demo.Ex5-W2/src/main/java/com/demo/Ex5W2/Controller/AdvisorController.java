package com.demo.Ex5W2.Controller;

import com.demo.Ex5W2.Model.Advisor;
import com.demo.Ex5W2.Service.AdvisorService;

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

    //Get
    @GetMapping
    public ResponseEntity<ArrayList<Advisor>> getAdvisors(){
        return ResponseEntity.status(200).body(advisorService.getAdvisors());
    }

    //Add
    @PostMapping
    public ResponseEntity<String> addAdvisors(@RequestBody @Valid Advisor adv, Errors errors) {

        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isAdvisorAdded = advisorService.addAdvisors(adv);
        if(!isAdvisorAdded){
            return ResponseEntity.status(500).body("Error adding a advisor");
        }
        return ResponseEntity.status(200).body("New advisor added");
    }

    //Update
    @PutMapping("/{index}")
    public ResponseEntity<String> updateAdvisors(@PathVariable Integer index, @RequestBody Advisor advisor){
        boolean isUpdated = advisorService.updateAdvisors(index,advisor);
        if (!isUpdated){
            return ResponseEntity.status(400).body("Invalid index ");
        }
        return ResponseEntity.status(200).body("advisor updated ");
    }

    //Delete
    @DeleteMapping("/{index}")
    public ResponseEntity<String> removeAdvisors(@PathVariable Integer index){
       boolean isDeleted = advisorService.removeAdvisors(index);
        if (!isDeleted){
            return ResponseEntity.status(400).body("error ");
        }
        return ResponseEntity.status(200).body("Advisor deleted ");
    }
}
