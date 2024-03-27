package pl.akademiaspecjalistowit.jokeapp.service;

import java.util.List;
import pl.akademiaspecjalistowit.jokeapp.data.Joke;

public class JokeServiceImpl implements JokeService {

    private final List<JokeProvider> jokeProviders;
    private static long counter = 0;

    public JokeServiceImpl(List<JokeProvider> jokeProviders) {
        if (jokeProviders == null || jokeProviders.isEmpty()) {
            throw new RuntimeException("Required at least one JokeProvider for the application to run");
        }
        this.jokeProviders = jokeProviders;
    }

    @Override
    public Joke getJoke() {
        return getJokeProvider().getJoke();
    }

    @Override
    public Joke getJoke(String category) {
        return getJokeProvider().getJokeByCategory(category);
    }

    private JokeProvider getJokeProvider() {
        return jokeProviders.get((int) counter++ % jokeProviders.size());
    }
}
