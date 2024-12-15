package org.example.hexlet.dto.courses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.hexlet.model.Course;

@AllArgsConstructor
@Getter
public class CoursesPage {
    private List<Course> courses;
    private String header;
    private String term;
}
