package box.yas.api;

/**
 * 인사말 응답.
 *
 * @param name    인사 대상 이름
 * @param message 완성된 인사말
 */
public record Greeting(String name, String message) {
}
