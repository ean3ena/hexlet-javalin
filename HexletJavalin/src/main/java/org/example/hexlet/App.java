package org.example.hexlet;

import io.javalin.Javalin;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import io.javalin.http.NotFoundResponse;
import io.javalin.rendering.template.JavalinJte;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static io.javalin.rendering.template.TemplateUtil.model;

public class App {

    private static final List<Course> COURSES = Data.getCourses();
    //private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> ctx.render("index.jte"));

        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var course = COURSES.stream()
                    .filter(el -> Objects.equals(el.getId(), id))
                    .findFirst().orElseThrow(() -> new NotFoundResponse("Course not found"));
            var page = new CoursePage(course);
            ctx.render("courses/show.jte", model("page", page));
        });

        app.get("/courses", ctx -> {
            var term = ctx.queryParam("term");
            List<Course> courses;
            if (term != null) {
                courses = COURSES.stream()
                    .filter(el -> StringUtils.containsIgnoreCase(el.getName(), term))
                    .toList();
            } else {
                courses = COURSES;
            }
            var header = "Курсы по программированию";
            var page = new CoursesPage(courses, header, term);
            ctx.render("courses/index.jte", model("page", page));
        });

        app.get("/users/build", ctx -> {
            ctx.render("users/build.jte");
        });

        app.get("/users/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var user = UserRepository.getEntities().stream()
                    .filter(el -> Objects.equals(el.getId(), id))
                    .findFirst().orElseThrow(() -> new NotFoundResponse("User not found"));
            var page = new UserPage(user);
            ctx.render("users/show.jte", model("page", page));
        });

        app.get("/users", ctx -> {
            var term = ctx.queryParam("term");
            List<User> users;
            if (term != null) {
                users = UserRepository.getEntities().stream()
                    .filter(el -> StringUtils.containsIgnoreCase(el.getName(), term))
                    .toList();
            } else {
                users = UserRepository.getEntities();
            }
            var page = new UsersPage(users, term);
            ctx.render("users/index.jte", model("page", page));
        });

        app.post("/users", ctx -> {
            var name = ctx.formParam("name").trim();
            var email = ctx.formParam("email").trim().toLowerCase();
            var password = ctx.formParam("password");
            var passwordConfirmation = ctx.formParam("passwordConfirmation");

            var user = new User(name, email, password);
            UserRepository.save(user);
            ctx.redirect("/users");
        });


        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7090);
    }
}
