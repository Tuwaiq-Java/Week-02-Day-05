package com.example.schoolmanagement.service;
import com.example.schoolmanagement.model.Classes;
import com.example.schoolmanagement.model.Student;
import com.example.schoolmanagement.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassesService {
    List<Classes> classes = new ArrayList<>();
    private final TeacherService teacherService;
    private final StudentService studentService;

    public ClassesService(TeacherService teacherService, StudentService studentService) {
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.classes.addAll(
                List.of(
                        new Classes("1","Math"),
                        new Classes("2","CS"),
                        new Classes("3","History"),
                        new Classes("4","Chemistry")
                ));
    }
    public List<Classes> getClassess(){
        return classes;
    }

    public void update(Classes classes, Classes p){
        classes.setName(p.getName());
    }

    public boolean deleteClasses(String id){

        if (isClassesByID(id)){
            Classes classes = getById(id);
            getClassess().remove(classes);
            return true;
        }
        return false;
    }

    public boolean isClassesByID(String id){
        int checkForWork = -1;
        Classes classes = getById(id);
        if (classes != null){
            checkForWork = Integer.parseInt(id);
        }
        return (checkForWork == -1) ? false :  true;
    }

    public boolean addClasses(Classes classes){
        return this.classes.add(classes);
    }

    public  Classes getById(String id){
        for (Classes classes: this.classes) {
            if (classes.getId().equals(id)){
                return classes;
            }
        }
        return null;
    }

    public Object getTecherById(String id) {
        Classes classes1 = getById(id);
        for (Teacher t : teacherService.teacher) {
            for (Classes c : t.getClassList()) {
                if (c.getId().equals(id)) {
                    return t.getName();
                }
            }
        }
        return "No teacher was assign yet!";
    }

    public Object getStudentByClassID(String id) {
        ArrayList<Student> students = new ArrayList<>();
        for (Student s: studentService.student) {
            for (Classes c:s.getClassList()) {
                if (c.getId().equals(id)){
                    students.add(s);
                }
            }
        }
        return students;
    }
}
