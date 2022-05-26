package com.example.schoolmanagement.service;

import com.example.schoolmanagement.model.Advisor;
import com.example.schoolmanagement.model.Allclass;
import com.example.schoolmanagement.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class StudentService {

    private ArrayList<Student> students =new ArrayList();

    private final AllclassService allclassService;
    private final AdvisorService advisorService;

    public ArrayList<Student> getStudents(){

        return students;
    }
    public boolean addStudent(Student student) {

        return students.add(student);
    }
    public Student updateStudent(int index,Student student)
    {
        return students.set(index, student);
    }
    public Student deleteStudent(int index){
        return students.remove(index);
    }

    public Student getStudentid(String studentid ){
        for (Student student:students) {
            if(student.getId().equals(studentid)){
                return student;
            }
        }
        return null;

}
   public Integer classList(String studentid,String classid){
       Allclass currentClass=allclassService.getClassid(classid);
       Student currentStudent=getStudentid(studentid);
       if(currentClass==null){
           return -1;
       }
       if(currentStudent==null){
           return 0;
       }
        currentStudent.getSclasslist().add(currentClass);


       return 1;
   }

    public Integer changeMajor(String studentid,String major){
        Student currentStudent=getStudentid(studentid);

        if(currentStudent==null){
            return -1;
        }

        currentStudent.setMajor(major);
        currentStudent.getSclasslist().clear();



        return 0;
    }
    public ArrayList<Student>studentList(String courseid){
        Allclass classs=allclassService.getClassid(courseid);
        ArrayList<Student> studentList=new ArrayList<>();
        for (Student student: students) {
            for(Allclass newclass:student.getSclasslist())
            if(classs.getId().equals(newclass.getId())){
                studentList.add(student);
            }
        }
        return studentList;
    }

    public ArrayList<Student>studentListForAdvsor(String advisorid){
        Advisor advisor=advisorService.getAvsorid(advisorid);
        ArrayList<Student> studentList=new ArrayList<>();
        for (Student student: students) {
                if(student.getAdvisorname().equals(advisor.getName())){
                    studentList.add(student);
                }
        }
        return studentList;
    }



}
