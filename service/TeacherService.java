package com.example.schoolmanagementsoftware.services;

import com.example.schoolmanagementsoftware.moles.Classes;
import com.example.schoolmanagementsoftware.moles.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private ArrayList<Teacher> teacherList = new ArrayList<>();

    private final ClassesService classesService;



    public ArrayList<Teacher> getTeachers() {
        return teacherList;
    }

    public boolean addTeachers(Teacher newTeacher) {
        return teacherList.add(newTeacher);
    }

    public boolean updateTeachers(String teacher) {
        ArrayList<Teacher> idTeacher = new ArrayList<>();

        for (Teacher teacher1: idTeacher) {
            if(teacher1.getId().equals(teacher)){
                return true;
            }
        }
        return false;
    }

    public Boolean deleteTeacher(String id){
        Integer indexTeacher = idClass(id);
        if(indexTeacher ==null){
            return false;
        }
        teacherList.remove((int)indexTeacher);
        return true;

    }
    public Integer idClass(String id){
        for (int i = 0; i < teacherList.size() ; i++) {
            if(teacherList.get(i).getId().equals(id)){
                return i;
            }

        }
        return null;
    }

    public Boolean addTeacherClass(String tech_id,String idClass1){
        Teacher cTeacher= teacherList.get(idClass(tech_id));
        Classes cClasses = tech_id.getClass().getClasses(classesService.idClass(idClass1));
        if(cTeacher == null | cClasses == null){
            return false;
        }


        return cTeacher.getClassArrayList().add(cClasses);

    }
    public String getClassTeacher(String idClass){
        Classes curr_class=classesService.getClasses1().get(classesService.idClass(idClass));
        if(curr_class==null){
            return null;
        }

        for (Teacher curr_tech : teacherList) {

            for(int j=0;j < curr_tech.getClassArrayList().size(); j++){
                if(curr_tech.getClassArrayList().get(j).getId().equals(idClass)){
                    return curr_tech.getName();
                }
            }
        }
        return null;
    }
}
