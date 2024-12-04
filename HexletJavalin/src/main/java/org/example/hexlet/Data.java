package org.example.hexlet;

import java.util.Random;
import java.util.Locale;
import net.datafaker.Faker;
import org.example.hexlet.model.Course;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.Collections;

public class Data {
    private static final int ITEMS_COUNT = 30;

    private static int idCounter = ITEMS_COUNT;

    public static List<Course> getCourses() {
        Random random = new Random(123);
        Faker faker = new Faker(new Locale("en"), random);

        List<Course> courses = new ArrayList<>();

        for (int i = 0; i < ITEMS_COUNT; i++) {
            Course course = new Course(
                    i,
                    faker.educator().course(),
                    faker.text().text()
            );
            courses.add(course);
        }

        return courses;
    }

    public static String getNextId() {
        int id = ++idCounter;
        return Integer.toString(id);
    }
}
