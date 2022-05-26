package com.example.schoolmanagementsoftware.services;

import com.example.schoolmanagementsoftware.moles.Advisor;
import com.example.schoolmanagementsoftware.moles.Classes;
import com.example.schoolmanagementsoftware.moles.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class StudentService {

    private ArrayList<Student> studentList = new ArrayList<>();

    private final classesService classesService;
    private final advisorService advisorService;


    public ArrayList<Student> getStudents(){
        return studentList;
    }

    public boolean addStudents(Student newStudent){
        return studentList.add(newStudent);
    }

    public boolean updateStudents(String student) {
        ArrayList<Student> idStudents = new ArrayList<>();

        for (Student student1: idStudents) {
            if(student1.getId().equals(student)){
                return true;
            }
        }
        return false;
    }
    public Boolean deleteStudent(String id){
        Integer indexStudent=findStudentById(id);
        System.out.println(indexStudent);
        if(indexStudent == null){
            return false;
        }
        studentList.remove((int)indexStudent);
        return true;

    }
    public Integer findStudentById(String id){
        for (int i = 0; i <studentList.size() ; i++) {
            if(studentList.get(i).getId().equals(id)){
                return i;
            }
        }
        return null;
    }
    public Boolean addStudentClass(String stu_id,String idClasses){
        Student cStudents=studentList.get(findStudentById(stu_id));
        Classes cClass = classesService(classesService.(idClasses));
        if(cStudents ==null |idClasses ==null){
            return false;
        }

        return cStudents.getClassArrayList().add(cClass);
    }
    public Boolean changeMajor(String cStudents,String new_major){
        Student cStudents = studentList.get(findStudentById(cStudents));
        if(cStudents == null){
            return false;
        }
        cStudents.setMajor(new_major);
        cStudents.setClassArrayList(new ArrayList<>());
        return true;
    }
    public ArrayList<Student> getClassStudents(String idClasses){
        Classes target_class=classesService.getCourse().get(classesService.idClass(idClasses));
        if(target_class==null){
            return null;
        }

        ArrayList<Student> students =new ArrayList<>();
        for (Student cStudents : studentList) {
            for (Classes cClasses : cStudents.getClassArrayList()
            ) {
                if(target_class.getId().equals(cClasses.getId())){
                    students.add(cStudents);
                }

            }
        }
        return students;
    }

    public ArrayList<Student> getAdvisorStudents(String adv_id){
        Advisor cAdvisor = advisorService.getAdvisors().get(advisorService.findAdvisorById(adv_id));
        if(cAdvisor == null){
            return null;
        }
        ArrayList<Student>filtered_students=new ArrayList<>();
        for (Student curr_stu:studentList) {
            if(curr_stu.getAdvisorName().equals(cAdvisor.getName())){
                filtered_students.add(curr_stu);
            }
        }
        return filtered_students;
    }

}

