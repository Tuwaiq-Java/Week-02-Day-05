package com.example.schoolmangmentsys.Services;

import com.example.schoolmangmentsys.model.Student;
import com.example.schoolmangmentsys.model.StudyClasses;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class StudentService {



    ArrayList<Student> students = new ArrayList();
    private final StudyClassestService studyClassestService ;
    public ArrayList<Student> getStu() {
        return students;
    }

    public Boolean addStu(Student student) {
return students.add(student);
    }

    public ArrayList<Student> delStu(Integer index) {
        students.remove(index);
        return students;
    }

    public ArrayList<Student> updStu(ArrayList<String> studentsNames , Integer index) {
      studentsNames.set(index ,"");
      return students;
    }

    public Integer addStuToClassList(String stuId , String classId) {
        Student student = getStudents(stuId);
        StudyClasses studyClasses = studyClassestService.getStuClasses(classId);

        if(student == null || studyClasses ==null){
            return -1;
        }



       student.getClassList().add(stuId);
        return 0;


    }












    // helper func
    public Student getStudents(String stuId){
        for (Student student :students) {
            if(student.getId().equals(stuId)){
                return student;
            }
        }
        return null;
    }


    public Integer changeStuMajor(String stuId , String major) {
        Student student = getStudents(stuId);
        Student studentM = getStudents(major);


        if (student == null || studentM ==null){
            return -1;

    }
        if(!student.getClassList().isEmpty()){
            return 0;
        }

        student.setMajor(student.getMajor());
        return 1;



        }





}
