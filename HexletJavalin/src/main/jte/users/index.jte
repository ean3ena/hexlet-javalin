@import org.example.hexlet.util.NamedRoutes
@import org.example.hexlet.dto.users.UsersPage
@param UsersPage page

@template.layout.page(content = @`
    <form action="${NamedRoutes.usersPath()}" method="get">
        <input type="search" required name="term" value="${page.getTerm()}">
        <input type="submit" value="Search">
    </form>
    <a href="${NamedRoutes.buildUserPath()}">New User</a>
    @for(var user : page.getUsers())
        <div>
            <h2><a href="${NamedRoutes.userPath(user.getId())}">${user.getName()}</a></h2>
        </div>
    @endfor
`)
