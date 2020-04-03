import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DataGenerator {
    public static String generateName() {
        Faker faker = new Faker();
        return faker.name().fullName();

    }
    public static String generateCVC() {
        Faker faker = new Faker();
        return faker.number().digits(3);
    }
    public static String shouldReturnRandomMonth() {
        List<String> givenList = Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
        Random random = new Random();
        return givenList.get(random.nextInt(givenList.size()));
    }
    public static String shouldReturnRandomYear() {
        List<String> givenList = Arrays.asList("21", "22", "23", "24");
        Random random = new Random();
        return givenList.get(random.nextInt(givenList.size()));
    }
    public static int generateYear() {
        LocalDate date = LocalDate.now();
        return date.getYear();
    }
    public static String shouldReturnRandomYearPlus() {
        List<Integer> givenList = Arrays.asList(1, 2, 3, 4, 5);
        Random random = new Random();
        int number = givenList.get(random.nextInt(givenList.size()));
        int year = DataGenerator.generateYear();
        int newYear = number + year;
        return String.valueOf(newYear % 100);
    }
    public static String shouldReturnRandomNumber() {
        int number = (int) (13 + Math.random() * 87);
        return String.valueOf(number);
    }
    public static String shouldReturnPreviousMonth() {
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int previousMonth = month - 1;
        return "0" + previousMonth;
    }
    public static String shouldReturnCurrentYear() {
        int year = DataGenerator.generateYear();
        return String.valueOf(year % 100);
    }
    public static String shouldReturnRandomNumeral() {
        List<String> givenList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        Random random = new Random();
        return givenList.get(random.nextInt(givenList.size()));
    }
    public static String shouldReturnRandomYearMinus() {
        List<Integer> givenList = Arrays.asList(1, 2, 3, 4, 5);
        Random random = new Random();
        int number = givenList.get(random.nextInt(givenList.size()));
        int year = DataGenerator.generateYear();
        int newYear = year - number;
        return String.valueOf(newYear % 100);
    }

}
