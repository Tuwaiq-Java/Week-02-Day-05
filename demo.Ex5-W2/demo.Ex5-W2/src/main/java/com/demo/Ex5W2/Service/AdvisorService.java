package com.demo.Ex5W2.Service;

import com.demo.Ex5W2.Model.Advisor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AdvisorService {
    private ArrayList<Advisor> advisors = new ArrayList();

    public ArrayList<Advisor> getAdvisors(){
        return advisors;
    }

    public boolean addAdvisors(@Valid Advisor adv) {
        return advisors.add(adv);
    }

    public boolean updateAdvisors(int index, Advisor adv) {
        if (index >= advisors.size() || index < 0) {
            return false;
        }
        advisors.set(index, adv);
        return true;
    }

    public boolean removeAdvisors(int index) {
        if(index > advisors.size() - 1){
            return false;
        }
        advisors.remove(index);
        return true;
    }

    public Advisor getAdvisors(String id){
        for (Advisor advisor:advisors) {
            if(advisor.getAdvisorID().equals(id)){
                return advisor;
            }
        }
        return null;
    }
}

