package com.example.schoolmanagementsoftware.services;

import com.example.schoolmanagementsoftware.moles.Classes;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClassesService {
    private ArrayList<Classes> classesList = new ArrayList<>();

    public ArrayList<Classes> getClasses1(){
        return classesList;
    }

    public boolean addClasses1(Classes newClasses) {
        return classesList.add(newClasses);
    }

    public boolean updateClasses1(String classes) {
        ArrayList<Classes> idClasses1 = new ArrayList<>();

        for (Classes classes1 : idClasses1) {
            if(classes1.getId().equals(classes)){
                return true;
            }
        }
        return false;
    }

    public Boolean deleteClasses1(String id){
        Integer indexClass = idClass(id);
        if(indexClass == null){
            return false;
        }
        classesList.remove((int)indexClass);
        return true;

    }
    public Integer idClass(String id){
        for (int i = 0; i < classesList.size() ; i++) {
            if(classesList.get(i).getId().equals(id)){
                return i;
            }
        }
        return null;
    }


}

