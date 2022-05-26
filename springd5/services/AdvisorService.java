package com.example.springd5.services;

import com.example.springd5.model.Advisor;
import com.example.springd5.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdvisorService {
    private ArrayList<Advisor> advisors=new ArrayList<>();

    public ArrayList<Advisor> getAdvisor(){
        return advisors;
    }

    public boolean addAdvisor(Advisor advisor){
        return advisors.add(advisor);
    }

    public boolean updateAdvisor(Integer index,Advisor advisor){

        Advisor currentAdvisor =advisors.set(index,advisor);
        if(index>advisors.size()-1 || index<0){
            return false;
        }
        return true;
    }

    public boolean deleteAdvisor(Integer index){
        if(index>advisors.size()-1 || index<0){
            return false;
        }
        advisors.remove(index);
        return true;
    }
}
