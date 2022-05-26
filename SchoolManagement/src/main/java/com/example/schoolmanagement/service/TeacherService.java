package com.example.schoolmanagement.service;

import com.example.schoolmanagement.model.Allclass;
import com.example.schoolmanagement.model.Student;
import com.example.schoolmanagement.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private ArrayList<Teacher> teachers=new ArrayList<>();
    private final AllclassService allclassService;
    public ArrayList<Teacher> getTeacher(){
    return teachers;
}
    public boolean addTeacher(Teacher teacher){

        return teachers.add(teacher);
}
    public Teacher updateTeacher(int index,Teacher teacher)
    {
        return teachers.set(index, teacher);
    }
    public Teacher deleteTeacher(int index){
        return teachers.remove(index);
    }
    public Teacher getTeacherid(String teacherid ){
        for (Teacher teacher1:teachers) {
            if(teacher1.getId().equals(teacherid)){
                return teacher1;
            }
        }
        return null;

    }
    public Integer classList(String Teacherid,String classid){
        Allclass currentClass=allclassService.getClassid(classid);
        Teacher currentTeacher =getTeacherid(Teacherid);
        if(currentClass==null){
            return -1;
        }
        if(currentTeacher ==null){
            return 0;
        }
        currentTeacher.getTclasslist().add(currentClass);


        return 1;
    }

    public String teacherList(String courseid) {
        Allclass classs = allclassService.getClassid(courseid);
        // ArrayList<Teacher> teacherArrayList=new ArrayList<>();
        for (Teacher teacher : teachers) {
            for (Allclass newclass : teacher.getTclasslist())
                if (classs.getId().equals(newclass.getId())) {
                    return teacher.getName();
                }
        }

   return null; }
}

