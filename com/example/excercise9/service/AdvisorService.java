package com.example.excercise9.service;

import com.example.excercise9.model.Advisor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdvisorService {

    private ArrayList<Advisor> advisors = new ArrayList<>();

    public ArrayList<Advisor> getAdvisors() {

        return advisors;
    }

    public boolean isAdd(Advisor advisor){
        for (int i = 0; i < advisors.size(); i++) {
            if(advisors.get(i).equals(advisor)){
                return false;
            }
        }
        advisors.add(advisor);
        return true;
    }

    public boolean isUpdate(Advisor advisor) {
        for (int i = 0; i < advisors.size(); i++) {
            if ((advisors.get(i).getId().equals(advisor.getId()))) {
                advisors.set(i,advisor);
                return true;
            }
        }
        return false;
    }

    public boolean isDelete(Advisor advisor) {
        return advisors.remove(advisor);
    }


}
