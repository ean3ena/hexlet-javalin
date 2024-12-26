@import org.example.hexlet.dto.MainPage
@param MainPage page

@template.layout.page(content = @`
    <h1>Привет, Хекслет!</h1>
    @if(!page.isVisited())
        Это сообщение показывается только один раз. Если вы хотите увидеть его снова, сотрите куки
    @endif
    @if(page.getCurrentUser() != null)
        Добро пожаловать, ${page.getCurrentUser()}.
        Чтобы разлогиниться, удалите куку JSESSIONID из браузера
    @endif
`)