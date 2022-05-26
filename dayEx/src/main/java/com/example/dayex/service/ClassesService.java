package com.example.dayex.service;

import com.example.dayex.model.Advisor;
import com.example.dayex.model.Classes;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClassesService {
    public boolean updateClass(Classes classes){
        for (int i = 0; i < classesArr.size(); i++) {
            if (classesArr.get(i).getId().equals(classes.getId()))
                classesArr.set(i,classes);
            return true;
        }
        return false;
    }
    public boolean deleteClasses(String id){
        Classes classes = getClassesById(id);
        if(classes == null){
            return false;
        }
        classesArr.remove(classes);
        return true;
    }
    ArrayList<Classes> classesArr = new ArrayList<>();

    public ArrayList<Classes> getClasses(){
        return classesArr;
    }
    public boolean addClasses(Classes classes){
       return classesArr.add(classes);
    }

    public Classes getClassesById(String id){
        for (Classes classes: classesArr) {
            if(classes.getId().equals(id))
                return classes;
        }

        return null;
    }
}
