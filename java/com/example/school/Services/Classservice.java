package com.example.school.Services;

import com.example.school.Models.Classes;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class Classservice{
    private ArrayList<Classes > classes=new ArrayList<>();



    public ArrayList<Classes> getClasses(){
        return classes;
    }
    public boolean isAdd(Classes class1) {
         return classes.add(class1);
    }

    public boolean isUpdate(Classes class1) {
        for (int i = 0; i < classes.size() ; i++) {
            if (classes.get(i).getClassId().equals(class1.getClassId()))
                classes.set(i,class1);
               return true;
        }
       return false;
    }


    public boolean isDelete(Classes class1) {
        return classes.remove(class1);


    }



    public Classes getClasses(String ClassId) {
        for (Classes classe:classes ) {
            if (classe.getClassId().equals(ClassId))
                return classe;
        }
        return null;

    }



}
