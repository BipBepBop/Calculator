import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<String> ROMANS = List.of("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X");

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println(calc(scanner.nextLine()));
    }

    public static String calc(String input) {
        boolean isRoman = false;
        int first;
        int second;
        int result = 0;
        String[] elements = input.split(" ");
        int size = elements.length;
        if (size > 3) {
            throw new RuntimeException("Ошибка т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        if (size < 3) {
            throw new RuntimeException("Ошибка т.к. строка не является математической операцией");
        }
        String firstStr = elements[0];
        String secondStr = elements[2];
        try {
            first = Integer.parseInt(firstStr);
            second = Integer.parseInt(secondStr);
        } catch (NumberFormatException e) {
            first = ROMANS.indexOf(firstStr) + 1;
            second = ROMANS.indexOf(secondStr) + 1;
            isRoman = true;
        }
        if (first > 10 || first < 1 || second > 10 || second < 1) {
            throw new RuntimeException("Калькулятор должен принимать на вход числа от 1 до 10 включительно");
        }
        result = switch (elements[1]) {
            case "+" -> first + second;
            case "-" -> first - second;
            case "*" -> first * second;
            case "/" -> first / second;
            default -> throw new RuntimeException("Не математическая операция");
        };
        return !isRoman
                ? String.valueOf(result)
                : convert(result);
    }

    public static String convert(int result) {
        int units = result % 10;
        int tens = (result % 100) / 10;
        int hundreds = (result % 1000) / 100;
        if (result <= 0) {
            throw new RuntimeException("Ошибка, так как меньше или равно нулю");
        }
        return convertHundreds(hundreds) + convertTens(tens) + convertUnits(units);
    }

    public static String convertUnits(int units) {
        String s_units = "";
        return switch (units) {
            case 1 -> "I";
            case 2 -> "II";
            case 3 -> "III";
            case 4 -> "IV";
            case 5 -> "V";
            case 6 -> "VI";
            case 7 -> "VII";
            case 8 -> "VIII";
            case 9 -> "IX";
            default -> s_units;
        };
    }

    public static String convertTens(int tens) {
        String s_tens = "";
        return switch (tens) {
            case 1 -> "X";
            case 2 -> "XX";
            case 3 -> "XXX";
            case 4 -> "XL";
            case 5 -> "L";
            case 6 -> "LX";
            case 7 -> "LXX";
            case 8 -> "LXXX";
            case 9 -> "XC";
            default -> s_tens;
        };
    }

    public static String convertHundreds(int hundreds) {
        String s_hundreds = "";
        if (hundreds == 1) {
            return "C";
        }
        return s_hundreds;
    }
}