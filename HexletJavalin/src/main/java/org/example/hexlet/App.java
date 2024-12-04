package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;
import io.javalin.rendering.template.JavalinJte;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;

import java.util.List;

import static io.javalin.rendering.template.TemplateUtil.model;

public class App {

    private static final List<Course> COURSES = Data.getCourses();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> ctx.render("index.jte"));

        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Integer.class).get();
            var course = COURSES.stream()
                    .filter(el -> el.getId().equals(id))
                    .findFirst().orElseThrow(() -> new NotFoundResponse("Course not found"));
            var page = new CoursePage(course);
            ctx.render("courses/show.jte", model("page", page));
        });

        app.get("/courses", ctx -> {
            var header = "Курсы по программированию";
            var page = new CoursesPage(COURSES, header);
            ctx.render("courses/index.jte", model("page", page));
        });

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7090);
    }
}
