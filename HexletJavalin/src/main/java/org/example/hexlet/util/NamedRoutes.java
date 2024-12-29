package org.example.hexlet.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    public static String usersPath() {
        return "/users";
    }

    public static String buildUserPath() {
        return "/users/build";
    }

    public static String userPath(Long id) {
        return userPath(String.valueOf(id));
    }

    public static String userPath(String id) {
        return "/users/" + id;
    }

    public static String coursesPath() {
        return "/courses";
    }

    public static String buildCoursePath() {
        return "/courses/build";
    }

    public static String coursePath(Long id) {
        return coursePath(String.valueOf(id));
    }

    public static String coursePath(String id) {
        return "/courses/" + id;
    }

    public static String sessionsPath() { return "/sessions"; }

    public static String buildSessionsPath() { return "/sessions/build"; }

    public static String carsPath() { return "/cars"; }

    public static String buildCarPath() { return "/cars/build"; }

    public static String carPath(Long id) { return carPath(String.valueOf(id)); }

    public static String carPath(String id) { return "/cars/" + id; }
}
