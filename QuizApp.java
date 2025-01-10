import java.util.*;//A simple Quiz App that asks the user multiple-choice questions and calculates the score based on the answers provided. The user has 10 seconds to answer each question. The questions and answers are hardcoded in the program. The program also displays a summary of the user's performance at the end of the quiz.
import java.util.concurrent.*;

public class QuizApp {
    static class Question {
        String questionText;
        String[] options;
        int correctOption;

        public Question(String questionText, String[] options, int correctOption) {
            this.questionText = questionText;
            this.options = options;
            this.correctOption = correctOption;
        }
    }

    public static void main(String[] args) {
        List<Question> questions = new ArrayList<>();//i have used collection framework to store the questions and answers
        questions.add(new Question("What is the capital of India?", new String[]{"1. Delhi", "2. Guwahati", "3. Paris", "4. Rome"}, 1));
        questions.add(new Question("Which planet is known as the Red Planet?", new String[]{"1. Earth", "2. Mars", "3. Jupiter", "4. Venus"}, 2));
        questions.add(new Question("Who wrote 'Hamlet'?", new String[]{"1. Charles Dickens", "2. William Shakespeare", "3. Jane Austen", "4. Mark Twain"}, 2));
        questions.add(new Question("What is the largest mammal?", new String[]{"1. Elephant", "2. Blue Whale", "3. Giraffe", "4. Hippopotamus"}, 2));

        Scanner scanner = new Scanner(System.in);
        int score = 0;
        Map<Integer, Boolean> results = new HashMap<>();//its a map that stores the results of the quiz

        System.out.println("Welcome to the Quiz! You have 10 seconds to answer each question.");
        System.out.println("-----------------------------------------------------------\n");

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + q.questionText);
            for (String option : q.options) {
                System.out.println(option);
            }
            
            System.out.println("You have 10 seconds to answer.");
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future<Integer> future = executor.submit(() -> {
                System.out.print("Your answer: ");
                return scanner.nextInt();
            });

            int userAnswer = -1;
            try {//exception handaling is displayed here
                userAnswer = future.get(10, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                System.out.println("Time's up! Moving to the next question.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            } finally {
                executor.shutdownNow();
            }

            if (userAnswer == q.correctOption) {
                score++;
                results.put(i + 1, true);
            } else {
                results.put(i + 1, false);
            }

            System.out.println();
        }

        System.out.println("Quiz Finished!\n");
        System.out.println("Your final score is: " + score + "/" + questions.size());

        System.out.println("Summary:");
        for (int i = 0; i < questions.size(); i++) {
            String result = results.get(i + 1) ? "Correct" : "Incorrect";
            System.out.println("Question " + (i + 1) + ": " + result);
        }scanner.close();//closing the scanner is essential to avoid memory leaks
    }
}
