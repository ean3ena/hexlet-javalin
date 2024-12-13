package org.example.hexlet;

import java.util.Random;
import java.util.Locale;
import net.datafaker.Faker;
import org.example.hexlet.model.Course;
import org.example.hexlet.model.User;

import java.util.List;
import java.util.ArrayList;

public class Data {
    private static final int ITEMS_COUNT = 30;

    private static int idCounter = ITEMS_COUNT;

    public static List<Course> getCourses() {
        Random random = new Random(123);
        Faker faker = new Faker(new Locale("en"), random);

        List<Course> courses = new ArrayList<>();

        for (int i = 0; i < ITEMS_COUNT; i++) {
            Course course = new Course(
                    Long.valueOf(i),
                    faker.educator().course(),
                    faker.text().text()
            );
            courses.add(course);
        }

        return courses;
    }

    public static List<User> getUsers() {
        Random random = new Random(123);
        Faker faker = new Faker(new Locale("en"), random);

        List<User> users = new ArrayList<>();

        for (int i = 0; i < ITEMS_COUNT; i++) {
            User user = new User(
                    Long.valueOf(i),
                    faker.name().name()
            );
            users.add(user);
        }

        return users;
    }

    public static String getNextId() {
        int id = ++idCounter;
        return Integer.toString(id);
    }
}
