import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * Java 11+ java.net.http.HttpClient를 사용한 간단한 HTTP 클라이언트 예제입니다.
 */
public class SimpleHttpClient {

    public static void main(String[] args) {
        // 1. GET 요청 예제 (동기 방식)
        System.out.println("--- GET 요청 시작 ---");
        try {
            String getResponse = sendGetRequest("https://jsonplaceholder.typicode.com/posts/1");
            System.out.println("응답 결과:\n" + getResponse);
        } catch (Exception e) {
            System.err.println("GET 요청 중 오류 발생: " + e.getMessage());
        }

        // 2. POST 요청 예제 (JSON 데이터 전송)
        System.out.println("\n--- POST 요청 시작 ---");
        String jsonPayload = "{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}";
        try {
            String postResponse = sendPostRequest("https://jsonplaceholder.typicode.com/posts", jsonPayload);
            System.out.println("응답 결과:\n" + postResponse);
        } catch (Exception e) {
            System.err.println("POST 요청 중 오류 발생: " + e.getMessage());
        }
    }

    /**
     * 동기 방식으로 GET 요청을 보냅니다.
     */
    public static String sendGetRequest(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    /**
     * 동기 방식으로 POST 요청을 보냅니다.
     */
    public static String sendPostRequest(String url, String json) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
