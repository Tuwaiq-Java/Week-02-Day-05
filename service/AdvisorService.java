package com.example.schoolmanagementsoftware.services;

import com.example.schoolmanagementsoftware.moles.Advisor;
import com.example.schoolmanagementsoftware.moles.Classes;
import com.example.schoolmanagementsoftware.moles.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdvisorService {

    private ArrayList<Advisor> advisorList = new ArrayList<>();

    public ArrayList<Advisor> getAdvisors(){
        return advisorList;
    }

    public boolean addAdvisors(Advisor newAdvisors){
        return advisorList.add(newAdvisors);
    }

    public boolean updateAdvisors(String advisor) {

        ArrayList<Advisor> advisorList = new ArrayList<>();

        for (Advisor advisor1 : advisorList) {
            if(advisor1.getId().equals(advisor)){
                return true;
            }
        }
        return false;
    }

    public Boolean deleteAdvisor(String id){
        Integer curr_stu_idx=findAdvisorById(id);
        if(curr_stu_idx==null){
            return false;
        }
        advisorList.remove((int)curr_stu_idx);
        return true;

    }
    public Integer findAdvisorById(String id){
        for (int i = 0; i <advisorList.size() ; i++) {
            if(advisorList.get(i).getId().equals(id)){
                return i;
            }
        }
        return null;
    }

}
}
