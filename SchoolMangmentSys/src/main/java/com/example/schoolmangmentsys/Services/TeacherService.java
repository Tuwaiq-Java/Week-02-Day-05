package com.example.schoolmangmentsys.Services;

import com.example.schoolmangmentsys.model.Advisor;
import com.example.schoolmangmentsys.model.Student;
import com.example.schoolmangmentsys.model.StudyClasses;
import com.example.schoolmangmentsys.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class TeacherService {

    ArrayList<Teacher> teachers = new ArrayList<>();
    private final StudyClassestService studyClassestService;
    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public Boolean addTeachers(Teacher teacher) {
        return teachers.add(teacher);
    }

    public ArrayList<Teacher>delTeachers(Integer index) {
        teachers.remove(index);
        return teachers;
    }

    public ArrayList<Teacher> updTeachers(ArrayList<String> teacheraNames, Integer index) {
        teacheraNames.set(index , "");
        return teachers;
    }

    public Integer addTeacherToClassList(String teacherId, String classId) {
        Teacher teacher = getTeacher(teacherId);
        StudyClasses studyClasses = studyClassestService.getStuClasses(classId);

        if(teacher == null || studyClasses ==null){
            return -1;
        }


        teacher.getClassList().add(teacherId);
        return 0;

    }



    public Integer teacherOfClass(String classId , String teacherId){
        Teacher teacher = getTeacher(teacherId);
        StudyClasses teacherClass = studyClassestService.getStuClasses(classId);

        if (teacher == null || teacherClass ==null){
            return -1;
        }

        teacherClass.getName().contains(teacherId);
            return 0;



    }



    public Teacher getTeacher(String teacherId){
        for (Teacher teacher :teachers) {
            if(teacher.getId().equals(teacherId)){
                return teacher;
            }
        }
        return null;
    }














}
