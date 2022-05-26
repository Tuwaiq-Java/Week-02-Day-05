package com.example.springd5.services;

import com.example.springd5.model.Classes;
import com.example.springd5.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClassesService {
    private ArrayList<Classes> classes=new ArrayList<>();

    public ArrayList<Classes> getClasses(){
        return classes;
    }

    public boolean addClasses(Classes classes1){
        return classes.add(classes1);
    }

    public boolean updateClasses(Integer index,Classes classes1){

        Classes currentClasses =classes.set(index,classes1);
        if(index>classes.size()-1 || index<0){
            return false;
        }
        return true;
    }

    public boolean deleteClasses(Integer index){
        if(index>classes.size()-1 || index<0){
            return false;
        }
        classes.remove(index);
        return true;
    }


    public Classes getClass(String classtid){
        for(Classes classes1:classes){
            if(classes1.getId().equals(classtid)){
                return classes1;
            }
        }
        return null;
    }
}
