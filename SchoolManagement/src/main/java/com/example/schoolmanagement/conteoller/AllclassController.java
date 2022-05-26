package com.example.schoolmanagement.conteoller;

import com.example.schoolmanagement.model.Advisor;
import com.example.schoolmanagement.model.Allclass;
import com.example.schoolmanagement.model.Api;
import com.example.schoolmanagement.model.Student;
import com.example.schoolmanagement.service.AdvisorService;
import com.example.schoolmanagement.service.AllclassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RequestMapping("api/v1/class")
@RestController
@RequiredArgsConstructor
public class AllclassController {

    private final AllclassService allclassService;
    @GetMapping
    public ResponseEntity<ArrayList<Allclass>> getclass(){

        return ResponseEntity.status(200).body(allclassService.getAllclasses());

    }
    @PostMapping
    public ResponseEntity<Api> addAdivsor(@RequestBody @Valid Allclass allclass, Errors errors){

        if (errors.hasErrors()){

            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }

        boolean isClassadd=allclassService.addAllclass(allclass);
        if (!isClassadd) {
            return ResponseEntity.status(500).body(new Api("Error adding a class", 500));

        }
        return ResponseEntity.status(200).body(new Api("New class added", 200));
    }


    @PutMapping("{index}")
    public ResponseEntity<Api> editlClass(@PathVariable int index,@RequestBody@Valid Allclass allclass, Errors errors){

        if (errors.hasErrors()){

            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }

       allclassService.updateAllclass(index,allclass);

        return ResponseEntity.status(200).body(new Api(" Teacher update", 200));
    }
    @DeleteMapping("{index}")
    public ResponseEntity<Api> deleteClass(@PathVariable int index){

        allclassService.deleteAllclass(index);

        return ResponseEntity.status(200).body(new Api(" Teacher delete", 200));
    }

    }




