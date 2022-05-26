package com.example.schoolmanagement.service;

import com.example.schoolmanagement.models.Classes;
import com.example.schoolmanagement.models.Student;
import com.example.schoolmanagement.models.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class ClassService {
//    private final StudentService studentService;
//    private final TeacherService teacherService;


    private ArrayList<Classes> classes = new ArrayList<>();

    public ArrayList<Classes> getClasses() {
        return classes;
    }

    public void addClass(Classes myClass){
        classes.add(myClass);
    }

    public Boolean updateClass(Classes myClass, Integer index){
        if(index > classes.size()-1){
            return false;
        }
        classes.set(index, myClass);
        return true;
    }
    public Boolean deleteClass(Integer index){
        if(index > classes.size()-1){
            return false;
        }
        classes.remove(index);
        return true;
    }




    public Classes findClass(String classId){
        for(Classes c: classes){
            if (c.getId().equals(classId)){
                return c;
            }
        }
        return null;
    }


}

