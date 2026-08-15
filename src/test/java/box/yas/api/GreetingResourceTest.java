package box.yas.api;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GreetingResourceTest {

    @Test
    void 이름을_주면_인사말을_돌려준다() {
        given()
            .when().get("/api/greetings/세계")
            .then()
                .statusCode(200)
                .body("name", is("세계"))
                .body("message", is("안녕하세요, 세계님"));
    }
}
