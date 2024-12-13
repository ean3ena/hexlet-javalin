@import org.example.hexlet.dto.users.UserPage
@param UserPage page

@template.layout.page(content = @`
    <h1>${page.getUser().getName()}</h1>
`)