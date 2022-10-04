package com.spring.codebuild.Services;

import com.spring.codebuild.DAO.CourseDAO;
import com.spring.codebuild.Validators.CourseValidator;
import com.spring.codebuild.models.Course;
import com.spring.codebuild.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService extends CourseValidator {
    @Autowired
    private CourseDAO courseDAO;

    public CourseService(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public ResponseEntity showCourse(int id) {
        Course course = courseDAO.getCourse(id);

        String idError = checkId(id, course);
        if (!idError.equals("")) {
            return ResponseEntity.ok(idError);
        }

        return ResponseEntity.ok(course);
    }

    public List<Course> showCourses(String tag) {
        List<Course> matchingCourses;

        if (tag.length() != 0) {
            List<Course> checkingCourses = courseDAO.getCourses();

            TagsFinder tagsFinder = new TagsFinder();
            matchingCourses = tagsFinder.findCourseTags(checkingCourses, tag);

            return matchingCourses;
        }

        matchingCourses = courseDAO.getCourses();
        return matchingCourses;
    }

    public List<Course> getPopularCourses(int id) {
        List<Course> popularCourses = courseDAO.getPopularCourses();
        int sizePopularCourses = popularCourses.size();

        if (id != 0) {
            for (int i = 0; i < sizePopularCourses; i++) {
                if (popularCourses.get(i).getId() == id) {
                    popularCourses.remove(i);
                    return popularCourses;
                }
            }
        }

        popularCourses.remove(sizePopularCourses - 1);

        return popularCourses;
    }

    public List<Tag> getTags() {
        return courseDAO.getTags();
    }

    public String addViews(int id) {
        Course course = courseDAO.getCourse(id);

        String idError = checkId(id, course);
        if (!idError.equals("")) {
            return idError;
        }

        courseDAO.addView(course.getViews(), id);

        return "Успешно";
    }
}
