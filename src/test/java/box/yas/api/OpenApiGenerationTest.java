package box.yas.api;

import io.quarkus.test.junit.QuarkusTest;
import io.vertx.core.json.JsonObject;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class OpenApiGenerationTest {

    private static final Path SCHEMA = Path.of("build", "openapi", "openapi.json");

    @Test
    void 빌드가_openapi_json을_만든다() throws Exception {
        assertTrue(Files.exists(SCHEMA),
                "OAS 파일이 없습니다: " + SCHEMA.toAbsolutePath()
                        + " — store-schema-directory 설정을 확인하세요");
    }

    @Test
    void 명세에_인사_엔드포인트가_들어있다() throws Exception {
        JsonObject spec = new JsonObject(Files.readString(SCHEMA));
        JsonObject paths = spec.getJsonObject("paths");

        assertNotNull(paths, "paths가 없습니다");
        assertNotNull(paths.getJsonObject("/api/greetings/{name}"),
                "인사 엔드포인트가 명세에 없습니다. 실제 paths: " + paths.fieldNames());
    }

    @Test
    void 명세에_Greeting_스키마가_들어있다() throws Exception {
        JsonObject spec = new JsonObject(Files.readString(SCHEMA));
        JsonObject components = spec.getJsonObject("components");

        assertNotNull(components, "components가 없습니다");
        JsonObject schemas = components.getJsonObject("schemas");

        assertNotNull(schemas.getJsonObject("Greeting"),
                "Greeting 스키마가 없습니다. 실제 스키마: " + schemas.fieldNames());
    }

    @Test
    void Greeting_스키마에_name과_message_프로퍼티가_들어있다() throws Exception {
        JsonObject spec = new JsonObject(Files.readString(SCHEMA));
        JsonObject components = spec.getJsonObject("components");

        assertNotNull(components, "components가 없습니다");
        JsonObject schemas = components.getJsonObject("schemas");

        JsonObject greeting = schemas.getJsonObject("Greeting");
        assertNotNull(greeting,
                "Greeting 스키마가 없습니다. 실제 스키마: " + schemas.fieldNames());

        JsonObject properties = greeting.getJsonObject("properties");
        assertNotNull(properties,
                "Greeting 스키마에 properties가 없습니다: " + greeting);
        assertTrue(properties.containsKey("name"),
                "Greeting 스키마에 name 프로퍼티가 없습니다. 실제 프로퍼티: " + properties.fieldNames());
        assertTrue(properties.containsKey("message"),
                "Greeting 스키마에 message 프로퍼티가 없습니다. 실제 프로퍼티: " + properties.fieldNames());
    }
}
