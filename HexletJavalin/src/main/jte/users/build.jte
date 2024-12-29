@import org.example.hexlet.util.NamedRoutes
@import org.example.hexlet.dto.users.BuildUserPage
@param BuildUserPage page

@template.layout.page(content = @`
    @if(page.getErrors() != null)
        <ul>
            @for(var validator : page.getErrors().values())
                @for(var error : validator)
                    <li>${error.getMessage()}</li>
                @endfor
            @endfor
        </ul>
    @endif

    <form action="${NamedRoutes.usersPath()}" method="post">
        <div class="form-group">
            <label for="inputName">Имя</label>
            <input type="text" class="form-control" id="inputName" name="name" placeholder="Введите имя" value="${page.getName()}" />
        </div>
        <div class="form-group">
            <label for="inputEmail">Email</label>
            <input type="email" class="form-control" id="inputEmail" required name="email" placeholder="Введите Email" value="${page.getEmail()}" />
        </div>
        <div class="form-group">
            <label for="inputPassword">Пароль</label>
            <input type="password" class="form-control" id="inputPassword" required name="password" placeholder="Введите пароль" />
        </div>
        <div class="form-group">
            <label for="inputPasswordConfirmation">Подтверждение пароля</label>
            <input type="password" class="form-control" id="inputPasswordConfirmation" required name="passwordConfirmation" placeholder="Повторите пароль" />
        </div>
        <button type="submit" class="btn btn-primary">Зарегистрировать</button>
    </form>
`)
