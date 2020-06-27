package com.company;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Main {

    //Global Variables
    static String guessedLetters = "";
    static String incorrectLetters = "";
    static private ArrayList<String> films = new ArrayList<>();
    static String filmToGuess = "";
    static String letterGuessed;
    static int points = 10;

    public static void main(String[] args) throws FileNotFoundException {


        //Initialize classes
        File file = new File("movies.txt");
        Scanner sc = new Scanner(file);
        Scanner input = new Scanner(System.in);

        //Intro for players
        System.out.println("Welcome to the game of Guess the Movie!");
        System.out.println("The rules are simple, the computer randomly picks a movie title, and shows you how many letters its made up of.");
        System.out.println("Your goal is to try to figure out the movie by guessing one letter at a time.");
        System.out.println("If a letter is indeed in the title the computer will reveal its correct position in the word, if not, you lose a point.");
        System.out.println("If you lose 10 points, game over!!");
        System.out.println("Let's start!");


        //Display film
        //System.out.println(getRandomMovie());
        filmToGuess = getRandomMovie();
        //      System.out.println(filmToGuess);
        //     getHiddenMovieTitle();
        String encryptedFilmName = filmToGuess;
        encryptedFilmName = getHiddenMovieTitle();
        System.out.println("Film To Guess: " + encryptedFilmName);

        //     System.out.println("Guess the letter: ");


        //Loop - checking letters
        for (; points > 0; ) {

            if (getHiddenMovieTitle().contains("_")) {

                letterGuessed = input.next();
//            System.out.println(letterGuessed);

                //If correct letter guessed
                if (filmToGuess.toLowerCase().contains(letterGuessed)) {
                    guessedLetters += " " + letterGuessed;
                    displayOverviewMessages();
                    getHiddenMovieTitle();
                    System.out.println(getHiddenMovieTitle());
                    displayInfoMessage();

                }//If incorrect letter guessed
                else {
                    incorrectLetters += " " + letterGuessed;
                    displayOverviewMessages();
                    getHiddenMovieTitle();
                    System.out.println(getHiddenMovieTitle());
                    points--;

                    System.out.println("You have lost 1 point. Current score: " + points + " points.");
                    displayInfoMessage();
                }
            } else {
                points = 0;
                displayInfoMessage();
            }

        }

    }


    // picking a random film from an array list containing films form the text file
    public static String getRandomMovie() throws FileNotFoundException {
        File file = new File("movies.txt");
        Scanner sc = new Scanner(file);

        //Adding films to an array
        while (sc.hasNextLine()) {
            films.add(sc.nextLine());
        }
        int movieIndex = (int) (Math.random() * films.size());
        return films.get(movieIndex);
    }

    // method replacing all the letters in the movie title with underscores,
    public static String getHiddenMovieTitle() {
        if (guessedLetters.equals("")) {
            return "Film To Guess: " + filmToGuess.replaceAll("[a-zA-Z]", "_");
        } else {
            return "Film To Guess: " + filmToGuess.replaceAll("[a-zA-Z&&[^" + guessedLetters + "]]", "_");
        }
    }

    //Showing overview of correct and incorrect letters guessed
    public static void displayOverviewMessages() {
        System.out.println("Correct letters guessed: " + guessedLetters + " ");
        System.out.println("Incorrect letters guessed: " + incorrectLetters + " ");
    }

    //Message informing about the state of game
    public static void displayInfoMessage() {
        if (points > 0) {
            System.out.println("Guess another letter. ");
        } else {
            System.out.println("Game Over");
            System.out.println("Correct answer was: " + filmToGuess);
        }
        System.out.println(" ");
    }

}

