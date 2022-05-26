package com.example.tw2d3.Controller2;


import com.example.tw2d3.Controller.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeesController {

    ArrayList<Employees> employees = new ArrayList<>();

    @GetMapping
    public ArrayList getEmployee() {
        return employees;
    }

    @PostMapping
    public ResponseEntity addEmployee(@Valid @RequestBody Employees E1, Errors errors) {
        if (errors.hasErrors()) {
            String defult = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(defult);
        }
        employees.add(E1);

        return ResponseEntity.status(200).body(E1);
    }

    @PutMapping({"/{index}"})
    public ResponseEntity updateRides(@PathVariable Integer index, @Valid @RequestBody Employees E2, Errors errors) {
        if (errors.hasErrors()) {
            String defult = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(defult);
        }

        if (index < this.employees.size() && index >= 0) {
            this.employees.set(index, E2);
            return ResponseEntity.status(200).body(new ResponseMessage("Employee is updated", 200));
        } else {
            return ResponseEntity.status(400).body(new ResponseMessage("Invalid index", 400));
        }
    }

    @DeleteMapping({"/{index}"})
    public ResponseEntity deleteRides(@PathVariable int index) {

        employees.remove(index);
        return ResponseEntity.status(200).body(new ResponseMessage("employee is delete", 200));
    }


    @PutMapping("/OnLeave/{id}")
    public ResponseEntity OnLeave(@PathVariable String id) {
        for (int i = 0; i < this.employees.size(); i++) {
            Employees E = (Employees) this.employees.get(i);
            if (E.getID().equals(id)) {
                if (E.getAnnualLeave() == 0) {
                    return ResponseEntity.status(400).body(new ResponseMessage("annual leave is zero", 400));
                }
                if (E.isOnLeave()) {
                    return ResponseEntity.status(400).body(new ResponseMessage("employee is on leave", 400));
                } else {
                  //  boolean check = E.isOnLeave();
                    E.setOnLeave(true);
                    E.setAnnualLeave(E.getAnnualLeave()-1);
                    employees.add(i, E);
                }
            }
            }
        return ResponseEntity.status(200).body(new ResponseMessage("GOOD", 200));
    }
}
