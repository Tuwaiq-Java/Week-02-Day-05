package com.example.schoolmanagement.service;

import com.example.schoolmanagement.model.Advisor;
import com.example.schoolmanagement.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AdvisorService {


    private ArrayList<Advisor> advisors =new ArrayList();


    public ArrayList<Advisor> getAdvisors(){

        return advisors;
    }
    public boolean addAdivsor(Advisor advisor) {

        return advisors.add(advisor);
    }
    public Advisor updateAdvisor(int index,Advisor advisor)
    {
        return advisors.set(index,advisor);
    }
    public Advisor deleteAdvisor(int index){
        return advisors.remove(index);
    }



    public Advisor getAvsorid(String advisorid ){
        for (Advisor advisor:advisors) {
            if(advisor.getId().equals(advisorid)){
                return advisor;
            }
        }
        return null;

    }



}
