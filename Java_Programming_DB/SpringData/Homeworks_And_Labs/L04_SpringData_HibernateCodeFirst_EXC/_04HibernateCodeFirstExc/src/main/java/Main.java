import service.*;

import java.util.Scanner;

import static constants.Constants.*;


public class Main {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print(INPUT_PROMPT);
        int taskNumber = Integer.parseInt(scanner.nextLine());

        if (isTaskNumberNonExistent(taskNumber)) return;

        System.out.println(executeTask(taskNumber));
    }


    private static String executeTask(int taskNumber) {

        switch (taskNumber) {
            case 1 -> {
                GringottsService.getInstance().executeTaskOne();
                return executedTaskNumber(taskNumber);
            }
            case 2 -> {
                SalesService.getInstance().executeTaskTwo();
                return executedTaskNumber(taskNumber);
            }
            case 3 -> {
                UniversityService.getInstance().executeTaskThree();
                return executedTaskNumber(taskNumber);
            }
            case 4 -> {
                HospitalService.getInstance().executeTaskFour();
                return executedTaskNumber(taskNumber);
            }
            case 5 -> {
                BillingService.getInstance().executeTaskFive();
                return executedTaskNumber(taskNumber);
            }
            case 6 -> {
                FootballBettingService.getInstance().executeTaskSix();
                return executedTaskNumber(taskNumber);
            }
            default -> {
                return "Something went wrong with execution!";
            }
        }
    }


    private static boolean isTaskNumberNonExistent(int taskNumber) {

        if (taskNumber < 1 || taskNumber > 6) {
            System.out.printf(String.format(TASK_DOES_NOT_EXIST, taskNumber));
            return true;
        }

        return false;
    }

    private static String executedTaskNumber(int taskNumber) {
        return String.format(TASK_WAS_EXECUTED, taskNumber);
    }


}
