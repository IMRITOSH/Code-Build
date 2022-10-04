package com.spring.codebuild.controllers;


import com.spring.codebuild.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("")
public class CoursesController {
    private CourseService courseService;

    @Autowired
    public CoursesController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/course")
    public ResponseEntity getCourse(@RequestParam("id") int id) {
        ResponseEntity course = courseService.showCourse(id);

        return course;
    }

    @GetMapping("/courses")
    public ResponseEntity getCourses(@RequestParam("tag") String tag) {
        return ResponseEntity.ok(courseService.showCourses(tag));
    }

    @GetMapping("/course/tags")
    public ResponseEntity getCourseTags() {
        return ResponseEntity.ok(courseService.getTags());
    }

    @GetMapping("/course/popular")
    public ResponseEntity getPopular(@RequestParam("id") int id) {
        return ResponseEntity.ok(courseService.getPopularCourses(id));
    }

    @PutMapping("/course/add_view")
    public ResponseEntity addView(@RequestBody int id) {
        String result = courseService.addViews(id);

        return ResponseEntity.ok(result);
    }
}
