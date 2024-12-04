package org.example.hexlet.dto.courses;

import org.example.hexlet.model.Course;

public class CoursePage {

    public CoursePage(Course course) {
        this.course = course;
    }

    private Course course;

    public Course getCourse() {
        return course;
    }
}
