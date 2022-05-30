package com.example.schoolmangmentsys.Services;

import com.example.schoolmangmentsys.model.Student;
import com.example.schoolmangmentsys.model.StudyClasses;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class StudyClassestService {


    ArrayList<StudyClasses> studyClasses = new ArrayList<>();
    private final StudentService studentService;
    public ArrayList<StudyClasses> getStuClass() {
        return studyClasses;
    }

    public Boolean addStuClass(StudyClasses studyClass) {
       return studyClasses.add(studyClass);


    }

    public ArrayList<StudyClasses> delStuClass(Integer index) {
       studyClasses.remove(index);
       return studyClasses;
    }

    public ArrayList<StudyClasses> updStuClass(ArrayList<String> classesNames, Integer index) {
         classesNames.set(index , "");
        return studyClasses;
    }




    public Integer stuOfClassList(String classId , String stuId){
        StudyClasses studyClasses = getStuClasses(classId);
        Student student = studentService.getStudents(stuId);



        if(studyClasses == null || student == null){
            return -1;
        }
        //student.getClassList().;
    }





    // helper
    public StudyClasses getStuClasses(String studyClassId){
        for (StudyClasses studyClasses1 :studyClasses) {
            if(studyClasses1.getId().equals(studyClassId)){
                return studyClasses1;
            }
        }
        return null;
    }
    }



