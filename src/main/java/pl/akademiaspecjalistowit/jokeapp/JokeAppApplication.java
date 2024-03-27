package pl.akademiaspecjalistowit.jokeapp;

import java.net.http.HttpClient;
import java.util.List;
import pl.akademiaspecjalistowit.jokeapp.data.FileJokeRepository;
import pl.akademiaspecjalistowit.jokeapp.data.InMemoryJokeRepository;
import pl.akademiaspecjalistowit.jokeapp.data.JokeRepository;
import pl.akademiaspecjalistowit.jokeapp.service.JokeApiProvider;
import pl.akademiaspecjalistowit.jokeapp.service.JokeDataProvider;
import pl.akademiaspecjalistowit.jokeapp.service.JokeProvider;
import pl.akademiaspecjalistowit.jokeapp.service.JokeService;
import pl.akademiaspecjalistowit.jokeapp.service.JokeServiceImpl;
import pl.akademiaspecjalistowit.jokeapp.view.JokeView;

public class JokeAppApplication {

    public static void main(String[] args) {
        JokeService jokeService = initiateApplicationContext();
        JokeView jokeView = new JokeView(jokeService);
        jokeView.run();
    }

    private static JokeService initiateApplicationContext() {
        List<JokeRepository> jokeRepositories = List.of(
            new InMemoryJokeRepository(),
            new FileJokeRepository("src/main/resources/jokes.txt"));

        List<JokeProvider> jokeProviders =
            List.of(new JokeApiProvider(HttpClient.newHttpClient()), new JokeDataProvider(jokeRepositories));

        JokeService jokeService = new JokeServiceImpl(jokeProviders);
        return jokeService;
    }
}
