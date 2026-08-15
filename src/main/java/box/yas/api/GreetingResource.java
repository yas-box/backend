package box.yas.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * 파이프라인 검증용 더미 엔드포인트.
 *
 * <p>이 API의 목적은 기능이 아니라 OAS를 만들어내는 것이다. 릴리스 파이프라인이
 * 코드에서 명세를 뽑아 문서 사이트까지 흘려보내는지 확인하는 데 쓴다.
 */
@Path("/api/greetings")
@Produces(MediaType.APPLICATION_JSON)
public class GreetingResource {

    /**
     * 주어진 이름으로 인사말을 만든다.
     *
     * @param name 인사 대상 이름
     */
    @GET
    @Path("/{name}")
    public Greeting greet(@PathParam("name") String name) {
        return new Greeting(name, "안녕하세요, " + name + "님");
    }

    /**
     * 기본 인사말을 돌려준다.
     */
    @GET
    public Greeting greetAnonymous() {
        return new Greeting("손님", "안녕하세요, 손님");
    }
}
