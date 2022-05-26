package com.example.schoolmanagement.service;
import com.example.schoolmanagement.model.Advisor;
import com.example.schoolmanagement.model.Classes;
import com.example.schoolmanagement.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdvisorService {
    List<Advisor> advisor = new ArrayList<>();
    private final StudentService studentService;


    public AdvisorService(StudentService studentService) {
        this.studentService = studentService;
        ArrayList<Student> students1 = new ArrayList<>();
        ArrayList<Student> students2 = new ArrayList<>();
        ArrayList<Student> students3 = new ArrayList<>();
        ArrayList<Student> students4 = new ArrayList<>();
        this.advisor.addAll(
                List.of(
                        new Advisor("1","Somal",49,students1),
                        new Advisor("2","JiJI",66,students2),
                        new Advisor("3","aJks",39,students3),
                        new Advisor("4","VOlod",42,students4)
                ));
    }
    public List<Advisor> getAdvisors(){
        return advisor;
    }

    public void update(Advisor advisor, Advisor p){
        advisor.setName(p.getName());
    }

    public boolean deleteAdvisor(String id){

        if (isAdvisorByID(id)){
            Advisor advisor = getById(id);
            getAdvisors().remove(advisor);
            return true;
        }
        return false;
    }

    public boolean isAdvisorByID(String id){
        int checkForWork = -1;
        Advisor advisor = getById(id);
        if (advisor != null){
            checkForWork = Integer.parseInt(id);
        }
        return (checkForWork == -1) ? false :  true;
    }

    public boolean addAdvisor(Advisor advisor){
        for (Student s: advisor.getStudentsList()) {
            for (Student allS: studentService.student) {
                if (s.getId().equals(allS.getId())) {
                    allS.setAdvisorName(advisor.getName());
                }
            }
        }
        return this.advisor.add(advisor);
    }

    public  Advisor getById(String id){
        for (Advisor advisor: this.advisor) {
            if (advisor.getId().equals(id)){
                return advisor;
            }
        }
        return null;
    }

    public Object getStudentByAdvisorId(String id) {
        ArrayList<Student> students = new ArrayList<>();
        String advisorName = getById(id).getName();
        for (Student s: studentService.student) {
                if (s.getAdvisorName().equals(advisorName)){
                    students.add(s);
            }
        }
        return students;
    }
}
