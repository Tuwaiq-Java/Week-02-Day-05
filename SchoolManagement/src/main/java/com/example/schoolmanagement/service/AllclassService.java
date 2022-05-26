package com.example.schoolmanagement.service;

import com.example.schoolmanagement.model.Allclass;
import com.example.schoolmanagement.model.Student;
import com.example.schoolmanagement.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AllclassService {

    private ArrayList<Allclass> allclasses=new ArrayList<>();

    public ArrayList<Allclass> getAllclasses(){
        return allclasses;
    }
    public boolean addAllclass(Allclass allclass){

        return allclasses.add(allclass);
    }
    public Allclass updateAllclass(int index,Allclass allclass)
    {
        return allclasses.set(index, allclass);
    }
    public Allclass deleteAllclass(int index){
        return allclasses.remove(index);
    }
    public Allclass getClassid(String classid ){
        for (Allclass allclass:allclasses) {
            if(allclass.getId().equals(classid)){
                return allclass;
            }
        }
        return null;

    }
}
