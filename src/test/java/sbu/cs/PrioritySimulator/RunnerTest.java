package sbu.cs.PrioritySimulator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RunnerTest {

    private static Runner runner;

    @BeforeAll
    static void setup() {
        runner = new Runner();
    }

    @RepeatedTest(value = 100, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @DisplayName("priorityThreads")

    void startTest() throws InterruptedException {
        int blackCount = 13;
        int blueCount = 8;
        int whiteCount = 5;

        runner.run(blackCount, blueCount, whiteCount);
        List<Message> messages = runner.getMessages();
        int count = 0;

        while (count < blackCount) {
            if (! messages.get(count ++).getCallerThreadColor().equals(BlackThread.class.getName())) {
                printError(count - 1, "black", messages.get(count - 1).getCallerThreadColor());
                fail();
            }
        }
        while (count < blackCount + blueCount) {
            if (! messages.get(count ++).getCallerThreadColor().equals(BlueThread.class.getName())) {
                printError(count - 1, "blue", messages.get(count - 1).getCallerThreadColor());
                fail();
            }
        }
        while (count < blackCount + blueCount + whiteCount) {
            if (! messages.get(count ++).getCallerThreadColor().equals(WhiteThread.class.getName())) {
                printError(count - 1, "white", messages.get(count - 1).getCallerThreadColor());
                fail();
            }
        }
    }

    private void printError(int count, String expected, String color) {
        System.out.printf("e" +
                "Expected index %s to be %s but it is %s", count, expected, color);
    }
}