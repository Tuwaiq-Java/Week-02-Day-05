package com.example.schoolmanagement.service;
import com.example.schoolmanagement.model.Classes;
import com.example.schoolmanagement.model.Teacher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {
    List<Teacher> teacher = new ArrayList<>();

    public TeacherService() {
        ArrayList<Classes> classList1 = new ArrayList<>();
        ArrayList<Classes> classList2 = new ArrayList<>();
        ArrayList<Classes> classList3 = new ArrayList<>();
        ArrayList<Classes> classList4 = new ArrayList<>();
        this.teacher.addAll(
                List.of(
                        new Teacher("1","Michal",classList1),
                        new Teacher("2","Tom",classList2),
                        new Teacher("3","Day",classList3),
                        new Teacher("4","Steven",classList4)
                ));
    }
    public List<Teacher> getTeachers(){
        return teacher;
    }

    public void update(Teacher teacher, Teacher p){
        teacher.setName(p.getName());
    }

    public boolean deleteTeacher(String id){

        if (isTeacherByID(id)){
            Teacher teacher = getById(id);
            getTeachers().remove(teacher);
            return true;
        }
        return false;
    }

    public boolean isTeacherByID(String id){
        int checkForWork = -1;
        Teacher teacher = getById(id);
        if (teacher != null){
            checkForWork = Integer.parseInt(id);
        }
        return (checkForWork == -1) ? false :  true;
    }

    public boolean addTeacher(Teacher teacher){
        return this.teacher.add(teacher);
    }

    public  Teacher getById(String id){
        for (Teacher teacher: this.teacher) {
            if (teacher.getId().equals(id)){
                return teacher;
            }
        }
        return null;
    }
}
