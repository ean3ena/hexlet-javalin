@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.courses.CoursesPage
@param CoursesPage page

@template.layout.page(content = @`
    <form action="${NamedRoutes.coursesPath()}" method="get">
        <input type="search" required name="term" value="${page.getTerm()}">
        <input type="submit" value="Search">
    </form>
    <a href="${NamedRoutes.buildCoursePath()}">New Course</a>
    @for(var course : page.getCourses())
       <div>
           <h2><a href="${NamedRoutes.coursePath(course.getId())}">${course.getName()}</a></h2>
       </div>
    @endfor
`)