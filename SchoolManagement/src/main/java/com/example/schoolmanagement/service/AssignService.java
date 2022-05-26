package com.example.schoolmanagement.service;
import com.example.schoolmanagement.model.Assign;
import com.example.schoolmanagement.model.Classes;
import com.example.schoolmanagement.model.Student;
import com.example.schoolmanagement.model.Teacher;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Lazy
public class AssignService {
    List<Assign> assign = new ArrayList<>();
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final ClassesService classesService;
    public AssignService(StudentService studentService, TeacherService teacherService, ClassesService classesService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.classesService = classesService;
        this.assign.addAll(
                List.of(
                        new Assign("1","1","1")
                ));
    }
    public List<Assign> getAssigns(){
        return assign;
    }

    public boolean deleteAssign(String id){

        if (isAssignByID(id)){
            Assign assign = getById(id);
            getAssigns().remove(assign);
            return true;
        }
        return false;
    }

    public boolean isAssignByID(String id){
        int checkForWork = -1;
        Assign assign = getById(id);
        if (assign != null){
            checkForWork = Integer.parseInt(id);
        }
        return (checkForWork == -1) ? false :  true;
    }

    public boolean addAssign(Assign assign){
        return this.assign.add(assign);
    }

    public  Assign getById(String id){
        for (Assign assign: this.assign) {
            if (assign.getId().equals(id)){
                return assign;
            }
        }
        return null;
    }

    public boolean assignStudent(Assign p) {
        Student student = studentService.getById(p.getAssinedid());
        Classes classes = classesService.getById(p.getClassesid());
        if (student == null || classes == null ){
            return false;
        }
        student.getClassList().add(classes);
        return true;
    }

    public boolean assignTeacher(Assign p) {
        Teacher teacher = teacherService.getById(p.getAssinedid());
        Classes classes = classesService.getById(p.getClassesid());
        if (teacher == null || classes == null ){
            return false;
        }
        teacher.getClassList().add(classes);
        return true;
    }
}
