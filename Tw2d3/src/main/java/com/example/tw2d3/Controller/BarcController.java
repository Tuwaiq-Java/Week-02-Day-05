package com.example.tw2d3.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/bark")
public class BarcController {
private final BarkService ridesService;

@GetMapping
public ArrayList getRides(){
    return ridesService.getRides();
}
    @PostMapping
    public ResponseEntity addRides(@Valid @RequestBody Bark rides, Errors errors) {
        if (errors.hasErrors()) {
            String defult = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(defult);}
        ridesService.add(rides);
        return ResponseEntity.status(200).body(rides);
    }

    @PutMapping({"/{index}"})
    public ResponseEntity updateRides(@PathVariable Integer index, @Valid @RequestBody Bark rides,Errors errors ) {
        if (errors.hasErrors()) {
            String defult = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(defult);}

       boolean isPut=ridesService.put(index,rides);
        if(isPut){
            return ResponseEntity.status(200).body(new ResponseMessage("rides updated", 200));
        } else {
            return ResponseEntity.status(400).body(new ResponseMessage("Invalid index", 400));
        }
    }

    @DeleteMapping({"/{index}"})
    public ResponseEntity deleteRides(@PathVariable int index) {

           ridesService.delete(index);
            return ResponseEntity.status(200).body(new ResponseMessage("rides is delete", 200));
    }

    @PutMapping("sale/{id}")
    public ResponseEntity<ResponseMessage> sale(@PathVariable String id, @RequestBody int amount) {
        int result = ridesService.sale(id,amount);
        switch (result){
            case -1: return ResponseEntity.status(400).body(new ResponseMessage("amount is less than price  ", 400));
            case 0: return ResponseEntity.status(200).body(new ResponseMessage("your ticket id is "+id, 200));
            default:return ResponseEntity.status(400).body(new ResponseMessage("id not found", 400));
            }
        }

//,@RequestBody Integer ticket
    @PutMapping("refile/{id}")
    public ResponseEntity refile(@PathVariable String id) {
        boolean isRefile=ridesService.refile(id);
        if(isRefile)
            return ResponseEntity.status(200).body(new ResponseMessage("ticket is refiled", 200));
            return ResponseEntity.status(400).body(new ResponseMessage("ticket is not zero", 400));
        }
    }

