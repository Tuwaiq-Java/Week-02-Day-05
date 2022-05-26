package com.example.schoolmanagement.conteoller;

import com.example.schoolmanagement.model.Api;
import com.example.schoolmanagement.model.Student;
import com.example.schoolmanagement.model.Teacher;
import com.example.schoolmanagement.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RequestMapping("api/v1/student")
@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<ArrayList<Student>> getStudent(){
        return ResponseEntity.status(200).body(studentService.getStudents());
    }
    @PostMapping
    public ResponseEntity<Api> addStudent(@RequestBody @Valid Student student, Errors errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }

        boolean isStudentAdd = studentService.addStudent(student);
        if (!isStudentAdd) {
            return ResponseEntity.status(500).body(new Api("Error adding a Student", 500));

        }
        return ResponseEntity.status(200).body(new Api("New Student added", 200));
    }
    @PutMapping("{index}")
    public ResponseEntity<Api> editStudent(@PathVariable int index, @RequestBody@Valid Student student, Errors errors){

        if (errors.hasErrors()){

            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }

        studentService.updateStudent(index, student);

        return ResponseEntity.status(200).body(new Api(" Student update", 200));
    }
    @DeleteMapping("{index}")
    public ResponseEntity<Api> deleteTeacher(@PathVariable int index){

      studentService.deleteStudent(index);

        return ResponseEntity.status(200).body(new Api(" Student delete", 200));
    }

    @PutMapping("/classliststudent")
    public ResponseEntity<Api> classList(@RequestParam String studentid, @RequestParam String classid) {
        Integer classList = studentService.classList(studentid, classid);

        switch (classList) {
            case -1:
                return ResponseEntity.status(400).body(new Api("class is not valid", 400));
            case 0:
                return ResponseEntity.status(400).body(new Api("student id is wrong", 400));

            case 1:
                return ResponseEntity.status(200).body(new Api("class list !" , 200));
            default:
                return ResponseEntity.status(500).body(new Api("Server error", 500));
        }

    }
    @PutMapping("/changemjor")
    public ResponseEntity<Api> changemajor(@RequestParam String  studentid, @RequestParam String major) {
        Integer changemajor = studentService.changeMajor(studentid,major);

        switch (changemajor) {
            case -1:
                return ResponseEntity.status(400).body(new Api("student id is not valid", 400));
            case 0:
                return ResponseEntity.status(200).body(new Api("student id is major change"+changemajor, 200));

            default:
                return ResponseEntity.status(500).body(new Api("Server error", 500));
        }

    }
    @PutMapping("/{courseid}")
    public ResponseEntity<Api> studentList(@PathVariable String courseid) {
        ArrayList<Student> s1=studentService.studentList(courseid);

        return ResponseEntity.status(200).body(new Api("The student of list type"+s1,200));


    }
}
