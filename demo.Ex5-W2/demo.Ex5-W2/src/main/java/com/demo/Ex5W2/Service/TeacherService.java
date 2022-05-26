package com.demo.Ex5W2.Service;

import com.demo.Ex5W2.Model.Class;
import com.demo.Ex5W2.Model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private ArrayList<Teacher> teachers = new ArrayList();

    public ArrayList<Teacher> getTeachers(){
        return teachers;
    }

    public boolean addTeachers(Teacher teacher) {
        return teachers.add(teacher);
    }

    public boolean updateTeachers(int index, Teacher teacher) {
        if (index >= teachers.size() || index < 0) {
            return false;
        }
        teachers.set(index, teacher);
        return true;
    }

    public boolean removeTeachers(int index) {
        if(index > teachers.size() - 1){
            return false;
        }
        teachers.remove(index);
        return true;
    }

    public Teacher getTeachers(String id){
        for (Teacher t:teachers) {
            if(t.getTeacherID().equals(id)){
                return t;
            }
        }
        return null;
    }
}

