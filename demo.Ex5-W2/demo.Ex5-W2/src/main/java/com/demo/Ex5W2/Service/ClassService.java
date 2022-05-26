package com.demo.Ex5W2.Service;

import com.demo.Ex5W2.Model.Class;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ClassService {
    private ArrayList<Class> classList = new ArrayList();

    public ArrayList<Class> getClasses(){
        return classList;
    }

    public boolean addClasses(Class c) {
        return classList.add(c);
    }

    public boolean updateClasses(int index, Class c) {
        if (index >= classList.size() || index < 0) {
            return false;
        }
        classList.set(index, c);
        return true;
    }

    public boolean removeClasses(int index) {
        if(index > classList.size() - 1){
            return false;
        }
        classList.remove(index);
        return true;
    }

    public Class getClass(String id){
        for (Class c:classList) {
            if(c.getClassID().equals(id)){
                return c;
            }
        }
        return null;
    }
}

