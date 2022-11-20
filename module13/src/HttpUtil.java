import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

public class HttpUtil {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();


    public static int sendPost(URI uri, User user) throws IOException, InterruptedException {
        final String requestBody = GSON.toJson(user);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        GSON.fromJson(response.body(), User.class);

        return response.statusCode();
    }


    public static int sendPut(URI uri, int userId, User user) throws IOException, InterruptedException {
        final String requestBody = GSON.toJson(user);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/" + userId))
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        GSON.fromJson(response.body(), User.class);
        return response.statusCode();
    }


    public static int sendDelete(URI uri, int userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/" + userId))
                .DELETE()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        GSON.fromJson(response.body(), User.class);
        return response.statusCode();
    }


    public static List<User> sendGetUsers(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(), new TypeToken<List<User>>() {
        }.getType());
    }


    public static User sendGetByUserID(URI uri, int userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/" + userId))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(), User.class);
    }


    public static User sendGetByUsername(URI uri, String username) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "?username=" + username))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        List<User> getUserByUserName = GSON.fromJson(response.body(), new TypeToken<List<User>>() {
        }.getType());
        return getUserByUserName.get(0);
    }



    public static int sendGetHighestPostId(URI uri, int taskTwoUserId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/" + taskTwoUserId + "/posts"))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        List<Post> posts = GSON.fromJson(response.body(), new TypeToken<List<Post>>() {
        }.getType());

        return posts.stream()
                .map(Post::getId)
                .max(Integer::compare)
                .orElse(1);
    }



    public static List<Comment> sendGetUserComments(URI uri, int highestPostId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/" + highestPostId + "/comments"))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(), new TypeToken<List<Comment>>() {
        }.getType());
    }



    public static void writeCommentsToJson(List<Comment> comments, int taskTwoUserId, int highestPostId) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("target/user-" + taskTwoUserId + "-post-" + highestPostId + "-comments.json"))) {
            writer.write(GSON.toJson(comments));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }



    public static List<ToDo> sendGetUserTodos(URI uri, int taskThreeUserId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/" + taskThreeUserId + "/todos"))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        List<ToDo> todo = GSON.fromJson(response.body(), new TypeToken<List<ToDo>>() {
        }.getType());

        return todo.stream()
                .filter(el -> !el.isCompleted())
                .collect(Collectors.toList());

    }
}