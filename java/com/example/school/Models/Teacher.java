package com.example.school.Models;

import lombok.Data;


import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;

@Data
public class Teacher {
@NotEmpty
    private String Name;
@NotEmpty
    private String TeacherId;

    private ArrayList<Classes > classes;

    public String getTeachername(String classid){
        for (int i = 0; i < classes .size(); i++) {
            if (classes.get(i).getClassId().equals(classid))
                return getName();
        }
        return null;
    }

    public Teacher(String TeacherId, String Name, ArrayList<Classes> classes) {
        this.TeacherId = TeacherId;
        this.Name = Name;
        this.classes = new ArrayList<>();
    }
}
