
import static org.assertj.core.api.Assertions.assertThat;

import org.example.hexlet.App;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    @BeforeAll
    public static void beforeAll() {
        app = App.getApp();
        app.start(0);
        int port = app.port();
        baseUrl = "http://localhost:" + port;
    }

    @AfterAll
    public static void afterAll() {
        app.stop();
    }

    @Test
    void testRootPage() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/").asString();
        assertThat(response.getStatus()).isEqualTo(200);
    }



    @Test
    void testPostNotFound() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/posts/999").asString();
        assertThat(response.getStatus()).isEqualTo(404);
    }
}
