import kr.paragonnov.KkmaWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class KkmaWrapperTest {

    @DisplayName("Command-line Test (--help)")
    @Test
    public void commandTestHelp() {
        final String[] args = {
                "--help",
                "--disable-print-output",
                "안녕하세요, 반가워요. 잘 있어요. 다시 만나요."
        };

        final String result = KkmaWrapper.runKkma(args);
        System.out.println(result);

        Assertions.assertEquals(
                KkmaWrapper.helpMessage,
                result
        );
    }

    @DisplayName("Command-line Test")
    @Test
    public void commandTest() {
        final String[] args = {
                "--log",
                "--disable-print-output",
                "안녕하세요, 반가워요. 잘 있어요. 다시 만나요."
        };

        final String result = KkmaWrapper.runKkma(args);
        System.out.println(result);

        Assertions.assertEquals(
                "[[\"안녕/NNG\",\"하세/NNG\",\"요/JX\",\",/SP\",\"반갑/VA\",\"어요/EFN\",\"./SF\",\"잘/MAG\",\"있/VA\",\"어요/EFN\",\"./SF\",\"다시/MAG\",\"만나/VV\",\"아요/EFN\",\"./SF\"]]",
                result
        );
    }

    @DisplayName("Command-line Test (List)")
    @Test
    public void commandTestList() {
        final String[] args = {
                "--log",
                "--disable-print-output",
                "0안녕하세요, 반가워요. 잘 있어요. 다시 만나요.",
                "1안녕하세요, 반가워요. 잘 있어요. 다시 만나요.",
                "2안녕하세요, 반가워요. 잘 있어요. 다시 만나요.",
                "3안녕하세요, 반가워요. 잘 있어요. 다시 만나요.",
                "4안녕하세요, 반가워요. 잘 있어요. 다시 만나요."
        };

        final String result = KkmaWrapper.runKkma(args);
        System.out.println(result);

        Assertions.assertEquals(
                "[[\"0/NR\",\"안녕/NNG\",\"하세/NNG\",\"요/JX\",\",/SP\",\"반갑/VA\",\"어요/EFN\",\"./SF\",\"잘/MAG\",\"있/VA\",\"어요/EFN\",\"./SF\",\"다시/MAG\",\"만나/VV\",\"아요/EFN\",\"./SF\"],[\"1/NR\",\"안녕/NNG\",\"하세/NNG\",\"요/JX\",\",/SP\",\"반갑/VA\",\"어요/EFN\",\"./SF\",\"잘/MAG\",\"있/VA\",\"어요/EFN\",\"./SF\",\"다시/MAG\",\"만나/VV\",\"아요/EFN\",\"./SF\"],[\"2/NR\",\"안녕/NNG\",\"하세/NNG\",\"요/JX\",\",/SP\",\"반갑/VA\",\"어요/EFN\",\"./SF\",\"잘/MAG\",\"있/VA\",\"어요/EFN\",\"./SF\",\"다시/MAG\",\"만나/VV\",\"아요/EFN\",\"./SF\"],[\"3/NR\",\"안녕/NNG\",\"하세/NNG\",\"요/JX\",\",/SP\",\"반갑/VA\",\"어요/EFN\",\"./SF\",\"잘/MAG\",\"있/VA\",\"어요/EFN\",\"./SF\",\"다시/MAG\",\"만나/VV\",\"아요/EFN\",\"./SF\"],[\"4/NR\",\"안녕/NNG\",\"하세/NNG\",\"요/JX\",\",/SP\",\"반갑/VA\",\"어요/EFN\",\"./SF\",\"잘/MAG\",\"있/VA\",\"어요/EFN\",\"./SF\",\"다시/MAG\",\"만나/VV\",\"아요/EFN\",\"./SF\"]]",
                result
        );
    }

    @DisplayName("Command-line Test (Mass List)")
    @Test
    public void commandTestMassList() {
        final List<String> argList = new ArrayList<>(List.of("--log", "--disable-print-output"));
        for (int i = 0; i < 1000; i++) {
            argList.add("안녕하세요, 반가워요. 잘 있어요. 다시 만나요.".concat(Integer.toString(i)));
        }
        final String[] args = argList.toArray(String[]::new);

        final String result = KkmaWrapper.runKkma(args);
        System.out.println(result);
    }

    @DisplayName("Command-line Test (--b64)")
    @Test
    public void commandTestB64() {
        final String[] args = {
                "--log",
                "--b64",
                "--disable-print-output",
                Base64.getEncoder().encodeToString("안녕하세요, 반가워요. 잘 있어요. 다시 만나요.".getBytes())
        };

        final String result = KkmaWrapper.runKkma(args);
        System.out.println(new String(Base64.getDecoder().decode(result)));

        Assertions.assertEquals(
                "W1si7JWI64WVL05ORyIsIu2VmOyEuC9OTkciLCLsmpQvSlgiLCIsL1NQIiwi67CY6rCRL1ZBIiwi7Ja07JqUL0VGTiIsIi4vU0YiLCLsnpgvTUFHIiwi7J6IL1ZBIiwi7Ja07JqUL0VGTiIsIi4vU0YiLCLri6Tsi5wvTUFHIiwi66eM64KYL1ZWIiwi7JWE7JqUL0VGTiIsIi4vU0YiXV0=",
                result
        );
    }

    @DisplayName("Command-line Test (--b64 & List)")
    @Test
    public void commandTestB64List() {
        final String[] args = {
                "--log",
                "--b64",
                "--disable-print-output",
                Base64.getEncoder().encodeToString("0안녕하세요, 반가워요. 잘 있어요. 다시 만나요.".getBytes()),
                Base64.getEncoder().encodeToString("1안녕하세요, 반가워요. 잘 있어요. 다시 만나요.".getBytes()),
                Base64.getEncoder().encodeToString("2안녕하세요, 반가워요. 잘 있어요. 다시 만나요.".getBytes()),
                Base64.getEncoder().encodeToString("3안녕하세요, 반가워요. 잘 있어요. 다시 만나요.".getBytes())
        };

        final String result = KkmaWrapper.runKkma(args);
        System.out.println(new String(Base64.getDecoder().decode(result)));

        Assertions.assertEquals(
                "W1siMC9OUiIsIuyViOuFlS9OTkciLCLtlZjshLgvTk5HIiwi7JqUL0pYIiwiLC9TUCIsIuuwmOqwkS9WQSIsIuyWtOyalC9FRk4iLCIuL1NGIiwi7J6YL01BRyIsIuyeiC9WQSIsIuyWtOyalC9FRk4iLCIuL1NGIiwi64uk7IucL01BRyIsIuunjOuCmC9WViIsIuyVhOyalC9FRk4iLCIuL1NGIl0sWyIxL05SIiwi7JWI64WVL05ORyIsIu2VmOyEuC9OTkciLCLsmpQvSlgiLCIsL1NQIiwi67CY6rCRL1ZBIiwi7Ja07JqUL0VGTiIsIi4vU0YiLCLsnpgvTUFHIiwi7J6IL1ZBIiwi7Ja07JqUL0VGTiIsIi4vU0YiLCLri6Tsi5wvTUFHIiwi66eM64KYL1ZWIiwi7JWE7JqUL0VGTiIsIi4vU0YiXSxbIjIvTlIiLCLslYjrhZUvTk5HIiwi7ZWY7IS4L05ORyIsIuyalC9KWCIsIiwvU1AiLCLrsJjqsJEvVkEiLCLslrTsmpQvRUZOIiwiLi9TRiIsIuyemC9NQUciLCLsnogvVkEiLCLslrTsmpQvRUZOIiwiLi9TRiIsIuuLpOyLnC9NQUciLCLrp4zrgpgvVlYiLCLslYTsmpQvRUZOIiwiLi9TRiJdLFsiMy9OUiIsIuyViOuFlS9OTkciLCLtlZjshLgvTk5HIiwi7JqUL0pYIiwiLC9TUCIsIuuwmOqwkS9WQSIsIuyWtOyalC9FRk4iLCIuL1NGIiwi7J6YL01BRyIsIuyeiC9WQSIsIuyWtOyalC9FRk4iLCIuL1NGIiwi64uk7IucL01BRyIsIuunjOuCmC9WViIsIuyVhOyalC9FRk4iLCIuL1NGIl1d",
                result
        );
    }
}
