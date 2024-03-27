package pl.akademiaspecjalistowit.jokeapp.view;

import java.util.InputMismatchException;
import java.util.Scanner;
import pl.akademiaspecjalistowit.jokeapp.service.JokeService;

public class JokeView {

    private final Scanner scanner;
    private final JokeService jokeService;

    public JokeView(JokeService jokeService) {
        this.scanner = new Scanner(System.in);
        this.jokeService = jokeService;
    }

    public void run() {
        int choiceFromTheUserMenu = -1;
        int choiceFromTheCategoryList = -1;

        do {
            showUserMenu();
            try {
                choiceFromTheUserMenu = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException ex) {
                scanner.nextLine();
            }
            switch (choiceFromTheUserMenu) {
                case 1:
                    wantARandomJokeFromAnyCategory(jokeService);
                    break;
                case 2:
//                    showCategoryList(jokeService);
                    try {
                        choiceFromTheCategoryList = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException ex) {
                        scanner.nextLine();
                    }
//                    wantARandomJokeFromSpecificCategory(jokeService.showAvailableCategories()
//                        .get(choiceFromTheCategoryList - 1), jokeService);
                    break;
                case 0:
                    exitingTheApplication();
                    break;
                default:
                    System.out.println("Incorrect value entered!");
                    System.out.println();
            }
        } while (choiceFromTheUserMenu != 0);
        scanner.close();
    }

    private void showUserMenu() {
        System.out.println("Select further options:\n" +
            "If you want to draw a joke from any category - enter 1:\n" +
            "If you want to randomly select a joke from a specific category - enter 2:\n" +
            "Logout - enter 0:");
    }

    private void wantARandomJokeFromAnyCategory(JokeService jokeService) {
        System.out.println("I'm displaying a joke for a random category...");
        System.out.println(jokeService.getJoke());
        System.out.println();
    }

//    private void showCategoryList(JokeService jokeService) {
//        List<String> categories = jokeService.showAvailableCategories();
//        for (int i = 0; i < categories.size(); i++) {
//            System.out.println((i + 1) + ". " + categories.get(i));
//        }
//        System.out.println("Enter the category number: ");
//    }

    private void wantARandomJokeFromSpecificCategory(String category, JokeService jokeService) {
        System.out.println("I'm displaying a random joke from the category " + category + "...");
        System.out.println(jokeService.getJoke(category));
        System.out.println();
    }

    private void exitingTheApplication() {
        System.out.println("Thank you! I hope you had fun!");
    }
}
