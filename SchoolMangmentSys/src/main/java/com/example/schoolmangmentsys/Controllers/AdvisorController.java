package com.example.schoolmangmentsys.Controllers;

import com.example.schoolmangmentsys.Services.AdvisorService;
import com.example.schoolmangmentsys.model.Advisor;
import com.example.schoolmangmentsys.model.Api;
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

 private final AdvisorService advisorService ;


    @GetMapping
    public ResponseEntity<ArrayList<Advisor>> getAdvi(){
        return ResponseEntity.status(200).body(advisorService.getAdvi());
    }


    @PostMapping
    public ResponseEntity<Api> addAdvi(@RequestBody @Valid Advisor advisor , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage() , 400));
        }
        Boolean isStuAdded = advisorService.addAdvi(advisor);
        if(!isStuAdded ){

            return ResponseEntity.status(500).body(new Api("error adding an advisor",500));
        }

        return ResponseEntity.status(201).body(new Api("new advisor is added",201));
    }



    @DeleteMapping("/{index}")
    public ResponseEntity<ArrayList<Advisor>> delAdvi(@PathVariable Integer index){
        return ResponseEntity.status(200).body(advisorService.delAdvi(index));

    }


    @PutMapping("index")
    public ResponseEntity<ArrayList<Advisor>> updAdvi(@RequestBody @Valid  ArrayList<String> studentsNames, Integer index )
    {
        return ResponseEntity.status(200).body(advisorService.updAdvi(studentsNames , index));
    }


}
