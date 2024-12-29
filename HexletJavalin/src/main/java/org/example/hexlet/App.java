package org.example.hexlet;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import org.example.hexlet.controller.CarsController;
import org.example.hexlet.controller.CoursesController;
import org.example.hexlet.controller.RootController;
import org.example.hexlet.controller.SessionsController;
import org.example.hexlet.controller.UsersController;
import org.example.hexlet.repository.BaseRepository;
import org.example.hexlet.util.NamedRoutes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class App {

    public static Javalin getApp() throws Exception {

        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:h2:mem:hexlet_project;DB_CLOSE_DELAY=-1");

        var dataSource = new HikariDataSource(hikariConfig);
        var url = App.class.getClassLoader().getResourceAsStream("schema.sql");
        var sql = new BufferedReader(new InputStreamReader(url))
                .lines().collect(Collectors.joining("\n"));

        try (var connection = dataSource.getConnection();
                var statement = connection.createStatement()) {
            statement.execute(sql);
        }
        BaseRepository.dataSource = dataSource;

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get(NamedRoutes.rootPath(), RootController::index);

        app.before(ctx -> {
            //
        });

        app.get(NamedRoutes.buildCoursePath(), CoursesController::build);
        app.get(NamedRoutes.coursePath("{id}"), CoursesController::show);
        app.get(NamedRoutes.coursesPath(), CoursesController::index);
        app.post(NamedRoutes.coursesPath(), CoursesController::create);
        app.get(NamedRoutes.coursePath("{id}") + "/edit", CoursesController::edit);
        app.patch(NamedRoutes.coursePath("{id}"), CoursesController::update);
        //app.delete(NamedRoutes.coursePath("{id}"), CoursesController::destroy);

        app.get(NamedRoutes.buildUserPath(), UsersController::build);
        app.get(NamedRoutes.userPath("{id}"), UsersController::show);
        app.get(NamedRoutes.usersPath(), UsersController::index);
        app.post(NamedRoutes.usersPath(), UsersController::create);
        app.get(NamedRoutes.userPath("{id}") + "/edit", UsersController::edit);
        app.patch(NamedRoutes.userPath("{id}"), UsersController::update);
        //app.delete(NamedRoutes.userPath("{id}"), UsersController::destroy);

        app.get(NamedRoutes.buildCarPath(), CarsController::build);
        app.get(NamedRoutes.carPath("{id}"), CarsController::show);
        app.get(NamedRoutes.carsPath(), CarsController::index);
        app.post(NamedRoutes.carsPath(), CarsController::create);

        app.after(ctx -> {
            //
        });

        app.get(NamedRoutes.buildSessionsPath(), SessionsController::build);
        app.post(NamedRoutes.sessionsPath(), SessionsController::create);
        app.delete(NamedRoutes.sessionsPath(), SessionsController::destroy);


        return app;
    }

    public static void main(String[] args) throws Exception {
        Javalin app = getApp();
        app.start(7090);
    }
}
