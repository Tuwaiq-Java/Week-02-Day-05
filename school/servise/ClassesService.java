package com.example.school.servise;

import com.example.school.model.Classes;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClassesService {
    private ArrayList<Classes> classes1 = new ArrayList<>();

    public  ArrayList<Classes> getClasses() {
        return classes1;
    }
    public boolean addClasses(Classes classes) {
        return classes1.add(classes);
    }
    public boolean updateClass(Classes classes,Integer index) {
        classes1.set(index, classes);
        return true;
    }
    public Boolean deleteClass(String classID) {
        classes1.remove(classID);
        return true;
    }
    //////////=========///////

}
