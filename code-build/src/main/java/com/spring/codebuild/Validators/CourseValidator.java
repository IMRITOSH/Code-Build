package com.spring.codebuild.Validators;

import com.spring.codebuild.models.Course;

public class CourseValidator {
    public String checkId(int id, Course dataBaseCourse){

        if(id == 0){
            return "Id should not be empty";
        } else if(dataBaseCourse==null){
            return "This id does not exist";
        }

        return "";
    }
}
