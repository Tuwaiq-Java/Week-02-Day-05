package com.example.schoolmanagement.service;

import com.example.schoolmanagement.model.Class;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClassService {
    private ArrayList<Class> classes = new ArrayList<>();
    public ArrayList<Class> getClasses() {
        return classes;
    }

    public Boolean addClass(Class clasS) {
        if (!isClassExisst(clasS.getID()))
            return false;
        return classes.add(clasS);
    }

    public Boolean isClassExisst(String classID) {
        for (int i = 0; i < classes.size(); i++) {
            if (classes.get(i).getID().equals(classID))
                return false;
        }
        return true;
    }

    public Boolean editClass(Class clasS) {
        for (int i = 0; i < classes.size(); i++) {
            if (classes.get(i).getID().equals(clasS.getID())){
                classes.set(i,clasS);
                return true;
            }
        }
        return false;
    }

    public Boolean deleteClass(String classID) {
        for (int i = 0; i < classes.size(); i++) {
            if (classes.get(i).getID().equals(classID)) {
                classes.remove(i);
                return true;
            }
        }
        return false;
    }

    public Class isClassExist(String classID) {
        for (int i = 0; i < classes.size(); i++) {
            if (classes.get(i).getID().equals(classID)) {
                return classes.get(i);
            }
        }
        return null;
    }
}
