package com.example.schoolmanagement.controllers;

import com.example.schoolmanagement.model.Api;
import com.example.schoolmanagement.model.Classes;
import com.example.schoolmanagement.service.ClassesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClassesController {
    private ClassesService classesService;
    public ClassesController(ClassesService classesService){
        this.classesService = classesService;
    }
    /**
     * Get all the domain data.
     */
    @GetMapping()
    public ResponseEntity<List> getClasses(){
        return  ResponseEntity.status(HttpStatus.OK).body(classesService.getClassess());
    }
    /**
     * Get a specific data by passing an id.
     * @param id id of the data
     */
    @GetMapping("teacher/{id}")
    ResponseEntity<Object> getTecherClassesByID(@PathVariable String id){
        if (!classesService.isClassesByID(id)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("no class was found", HttpStatus.BAD_REQUEST));
        }
        return  ResponseEntity.status(HttpStatus.OK).body(classesService.getTecherById(id));
    }@GetMapping("student/{id}")
    ResponseEntity<Object> getStudentClassesByID(@PathVariable String id){
        if (!classesService.isClassesByID(id)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("no class was found", HttpStatus.BAD_REQUEST));
        }
        return  ResponseEntity.status(HttpStatus.OK).body(classesService.getStudentByClassID(id));
    }

    //getStudentByClassID
    /**
     * Add new data.
     * @param p date to be added
     * @param errors errors if any found from the date validation
     */
    @PostMapping()
    ResponseEntity<Api> add(@RequestBody @Valid Classes p, Errors errors){
        try {
            check(errors);
            return (classesService.addClasses(p)) ? ResponseEntity.status(HttpStatus.CREATED).body(new Api("Adding was successful!", HttpStatus.CREATED)) : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Api("Adding was NOT successful!!", HttpStatus.INTERNAL_SERVER_ERROR));
        } catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(e.getMessage(), HttpStatus.BAD_REQUEST));
        }
    }
    /**
     * Update/Create data by passing an id.
     * @param id id of the data
     */
    @PutMapping("{id}")
    public ResponseEntity<Api> putClasses(@RequestBody @Valid Classes p, Errors errors, @PathVariable String id){
        try {
            check(errors);
            Classes classes = classesService.getById(id);
            if (classes != null) {
                classesService.update(classes, p);
                return ResponseEntity.status(HttpStatus.OK).body(new Api("Updated successfully!", HttpStatus.OK));
            } else
                return add(p, errors);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(e.getMessage(), HttpStatus.BAD_REQUEST));
        }
    }
    /**
     * Delete a data by passing an id.
     * @param id id of the data
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Api> deleteClasses(@PathVariable String id){
        boolean status;
        status = classesService.deleteClasses(id);
        return (!status) ? ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("No data found!", HttpStatus.BAD_REQUEST))
                :          ResponseEntity.status(HttpStatus.OK).body(new Api("Successfully deleted!",HttpStatus.OK));
    }


    /**
     * Checks if they are any errors from the given errors class, if any error found throw the appropriate message.
     * @param errors
     */
    public void check(Errors errors) throws IllegalArgumentException{
        if (errors.hasErrors()){
            String error = errors.getFieldError().getDefaultMessage();
            throw new IllegalArgumentException(error);
        }
    }

}
