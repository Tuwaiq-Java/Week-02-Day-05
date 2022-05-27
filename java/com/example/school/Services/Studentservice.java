package com.example.school.Services;

import com.example.school.Models.Classes;
import com.example.school.Models.Student;
import com.example.school.Models.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
@Service
@RequiredArgsConstructor
public class Studentservice {

    private final Classservice classservice;
    private ArrayList<Student> students=new ArrayList<>();



    public ArrayList<Student> getStudents(){
        return students;
    }

    public boolean isAdd(Student student) {
        return students.add(student);
    }

    public boolean isUpdate(Student student) {
        for (int i = 0; i < students.size() ; i++) {
            if (students.get(i).getStudentId().equals(student.getStudentId()))
                students.set(i,student);
            return true;
        }
        return false;
    }

    public boolean isDelete(Student student) {
        return students.remove(student);


    }

    public boolean isDelete(String major) {
        if(isExist(major)){
        for (Student student:students ) {
            if (major.equals(student.getMajor())){
                for (int i = 0; i < student.getClasses().size(); i++) {
                    student.getClasses().remove(i);
                }
            }
        }
        return true;
        }
        else{
            return false;}

           }

    private boolean isExist(String major) {
        for (Student student:students ) {
            if (major.equals(student.getMajor()))
                return true;
        }
        return false;
    }



    public Integer addCourses(String studenttId,String courseId){
        Classes currentclass= classservice.getClasses(courseId);
        Student currentStudent=getStudent(studenttId);
        if (currentStudent==null)
            return -1;
        if (currentclass==null)
            return 0;

        currentStudent.getClasses().add(currentclass);
             return 2;

    }

    public Student getStudent(String studentID){
       for (Student student:students ) {
           if (student.getStudentId().equals(studentID))
               return student;
       }
        return null;
   }

    public ArrayList<Student> StudentsName(String classid) {
        ArrayList<Student> students1 = new ArrayList<>();
        for (Student student : students) {
            Student studentname = student.StudentByid(classid);
            if (studentname != null)
                 students1.add(studentname);

        }
        return students1;
    }

    public ArrayList<Student> Advisorname(String name){
        ArrayList<Student> students1=new ArrayList<>();

        for (Student student:students ) {
            if (name.equals(student.getAdvisorName()))
                students1.add(student);
        }
        return students1;
           }








}
