import java.util.ArrayList;
import java.util.Scanner;

public class MovieChatbot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] responses = {
            "Invalid input. I didn't understand you",
            "Please try again.",
            "Invalid input",
            "Sorry, we are currently experiencing a large number of requests, please try again later.",
            "Please read the instructions and try again",
            "Sorry I didn't understand you."
        };

        // Movie list
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Wild Robot", "PG", 10.00, 90, "2D"));
        movies.add(new Movie("Venom: The Last Dance", "PG-13", 12.50, 120, "3D"));
        movies.add(new Movie("Interstellar", "PG-13", 15.00, 169, "2D"));
        movies.add(new Movie("Ratatouille", "G", 8.00, 111, "2D"));
        movies.add(new Movie("1917", "R", 14.00, 119, "3D"));

        boolean running = true;
        boolean waitingForHello = false;

        while (running) {
            if (!waitingForHello) {
                System.out.println("Hello, welcome to the theatre!");
                System.out.println("Would you like to see a movie? (yes/no)");
            }

            String response = scanner.nextLine().toLowerCase();

            if (response.equals("yes")) {
                waitingForHello = false; // Reset state
                System.out.println("Great! How many people will be watching?");
                int numPeople = getValidIntegerInput(scanner, "Please enter the number of people:");

                if (numPeople == 0) {
                    System.out.println("Are you actually a real customer? Or are you Mr. Thai trying to trick me?");
                } else if (numPeople < 0) {
                    System.out.println("Negative people? Are you bringing ghosts to the theatre? Spooky!");
                } else {
                    // Process age for valid input
                    int age;
                    if (numPeople > 1) {
                        System.out.println("What is the age of the youngest person in your group?");
                        age = getValidIntegerInput(scanner, "Please enter a valid age:");
                    } else {
                        System.out.println("What is your age?");
                        age = getValidIntegerInput(scanner, "Please enter a valid age:");
                    }

                    ArrayList<Movie> suitableMovies = filterMoviesByAge(movies, age);
                    if (!suitableMovies.isEmpty()) {
                        System.out.println("Here are the movies you can watch:");
                        for (int i = 0; i < suitableMovies.size(); i++) {
                            System.out.println((i + 1) + ". " + suitableMovies.get(i).getName());
                        }

                        System.out.println("Please choose a movie by entering its number:");
                        int choice = getValidIntegerInput(scanner, "Please enter a valid number:") - 1;

                        if (choice >= 0 && choice < suitableMovies.size()) {
                            Movie selectedMovie = suitableMovies.get(choice);
                            System.out.println("You selected: " + selectedMovie.getName());
                            System.out.println("Details:");
                            System.out.println("Age Rating: " + selectedMovie.getAgeRating());
                            System.out.println("Price: $" + selectedMovie.getPrice());
                            System.out.println("Length: " + selectedMovie.getLength() + " minutes");
                            System.out.println("Resolution: " + selectedMovie.getResolution());

                            System.out.println("Would you like to confirm this movie? (yes/no)");
                            String confirm = scanner.nextLine().toLowerCase();

                            if (confirm.equals("yes")) {
                                double total = selectedMovie.getPrice() * numPeople;
                                System.out.println("The total due is: $" + total);
                                System.out.println("Enjoy the movie!");
                                running = false; // End the loop after confirming
                            } else if (confirm.equals("no")) {
                                System.out.println("Would you like to start over? (yes/no)");
                                String restart = scanner.nextLine().toLowerCase();

                                if (restart.equals("no")) {
                                    System.out.println("Goodbye! Have a great day!");
                                    running = false;
                                }
                            }
                        }
                    } else {
                        System.out.println("Sorry, there are no movies suitable for the given age.");
                    }
                }
            } else if (response.equals("no")) {
                waitingForHello = true; // Set flag to wait for "hello"
                System.out.println("OK, just reply 'hello' if you'd like to see one later.");
            } else if (waitingForHello && response.equals("hello")) {
                waitingForHello = false; // Reset state
            } else if (response.equals("goodbye")) {
                System.out.println("Goodbye! Have a great day!");
                running = false;
            } else {
                int x = (int) (Math.random() * 6);
                System.out.println(responses[x]);
            }
        }

        scanner.close();
    }

    // Method to filter movies by age rating
    public static ArrayList<Movie> filterMoviesByAge(ArrayList<Movie> movies, int age) {
        ArrayList<Movie> suitableMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (isAgeAppropriate(movie.getAgeRating(), age)) {
                suitableMovies.add(movie);
            }
        }
        return suitableMovies;
    }

    // Helper method to check if a movie is age-appropriate
    public static boolean isAgeAppropriate(String ageRating, int age) {
        switch (ageRating) {
            case "G":
                return true;
            case "PG":
                return age >= 8;
            case "PG-13":
                return age >= 13;
            case "R":
                return age >= 17;
            default:
                return false;
        }
    }

    // Helper method to get a valid integer input
    public static int getValidIntegerInput(Scanner scanner, String errorMessage) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
    }
}

// Movie class
class Movie {
    private String name;
    private String ageRating;
    private double price;
    private int length;
    private String resolution;

    public Movie(String name, String ageRating, double price, int length, String resolution) {
        this.name = name;
        this.ageRating = ageRating;
        this.price = price;
        this.length = length;
        this.resolution = resolution;
    }

    public String getName() {
        return name;
    }

    public String getAgeRating() {
        return ageRating;
    }

    public double getPrice() {
        return price;
    }

    public int getLength() {
        return length;
    }

    public String getResolution() {
        return resolution;
    }
}
