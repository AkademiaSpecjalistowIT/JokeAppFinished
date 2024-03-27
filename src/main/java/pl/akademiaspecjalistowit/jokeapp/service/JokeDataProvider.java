package pl.akademiaspecjalistowit.jokeapp.service;

import pl.akademiaspecjalistowit.jokeapp.data.*;

import java.util.List;
import java.util.Random;

public class JokeDataProvider implements JokeProvider {

    private final List<JokeRepository> jokeRepositories;
    private static long counter = 0;


    public JokeDataProvider(List<JokeRepository> jokeRepositories) {
        this.jokeRepositories = jokeRepositories;
    }

    @Override
    public Joke getJoke() {
        Random rand = new Random();
        List<Joke> anyJokes = getJokeRepository().getAllJokes();
        return anyJokes.get(rand.nextInt(anyJokes.size()));
    }

    @Override
    public Joke getJokeByCategory(String category) {
        Random rand = new Random();
        List<Joke> jokesByCategory = getJokeRepository().getAllByCategory(category);
        return jokesByCategory.get(rand.nextInt(jokesByCategory.size()));
    }

    private JokeRepository getJokeRepository() {
        return jokeRepositories.get((int) counter++ % jokeRepositories.size());
    }
}