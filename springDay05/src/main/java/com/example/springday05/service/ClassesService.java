package com.example.springday05.service;

import com.example.springday05.model.Advisor;
import com.example.springday05.model.Classes;
import com.example.springday05.model.Student;
import com.example.springday05.model.Teacher;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ClassesService {

    public ArrayList<Classes> classesSER = new ArrayList<>();

    public ArrayList<Classes> getClasses() {

        return classesSER;
    }

    public String addClasses(Classes classes) {
        classesSER.add(classes);
        return "Class add !";
    }

    public String editClasses(int index, Classes classes) {
        classesSER.set(index,classes);
        return "Class edit completed !";
    }

    public String deleteClasses(int index) {
        classesSER.remove(index);
        return "Class delete completed";
    }

    public Classes checkClass(String classID){
        for (Classes classes: classesSER){
            if (classes.getId().equals(classID)){
                return classes;
            }
        }
        return null;
    }


}
