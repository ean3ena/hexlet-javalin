@import gg.jte.Content
@import org.example.hexlet.NamedRoutes
@param Content content

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hexlet Javalin Example</title>
</head>
<body>
<header>
    <h1>Hexlet</h1>
    <nav>
        <ul>
            <li><a href="/">Home</a></li>
            <li><a href="${NamedRoutes.usersPath()}">Users</a></li>
            <li><a href="${NamedRoutes.coursesPath()}">Courses</a></li>
        </ul>
    </nav>
</header>
<main>
    <div class="content">
        ${content}
    </div>
</main>
<footer>
    <p>
        <a href="https://github.com/ean3ena">My GitHub</a>
    </p>
</footer>
</body>
</html>
