import com.google.gson.Gson;
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
        final String expectedResult = KkmaWrapper.helpMessage;
        final String[] args = {
                "--help",
                "--disable-print-output",
                "안녕하세요, 반가워요. 잘 있어요. 다시 만나요."
        };

        final String actualResult = KkmaWrapper.runKkma(args);
        System.out.println(actualResult);

        Assertions.assertEquals(
                expectedResult,
                actualResult
        );
    }

    @DisplayName("Command-line test (--log)")
    @Test
    public void commandTestLog() {
        final String expectedResult = "[[\"안녕/NNG\",\"하/XSV\",\"세요/EFN\",\"./SF\"]]";
        final String[] args = {
                "--log",
                "안녕하세요."
        };

        final String actualResult = KkmaWrapper.runKkma(args);
        System.out.println(actualResult);

        Assertions.assertEquals(
                expectedResult,
                actualResult
        );
    }

    @DisplayName("Command-line Test")
    @Test
    public void commandTest() {
        final String expectedResult = "[[\"안녕/NNG\",\"하세/NNG\",\"요/JX\",\",/SP\",\"반갑/VA\",\"어요/EFN\",\"./SF\",\"잘/MAG\",\"있/VA\",\"어요/EFN\",\"./SF\",\"다시/MAG\",\"만나/VV\",\"아요/EFN\",\"./SF\"]]";
        final String[] args = {
                "--log",
                "--disable-print-output",
                "안녕하세요, 반가워요. 잘 있어요. 다시 만나요."
        };

        final String actualResult = KkmaWrapper.runKkma(args);
        System.out.println(actualResult);

        Assertions.assertEquals(
                expectedResult,
                actualResult
        );
    }

    @DisplayName("Command-line Test (List)")
    @Test
    public void commandTestList() {
        final List<List<String>> expectedMorphList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            expectedMorphList.add(List.of(Integer.toString(i).concat("/NR"), "안녕/NNG", "하세/NNG", "요/JX", ",/SP", "반갑/VA", "어요/EFN", "./SF", "잘/MAG", "있/VA", "어요/EFN", "./SF", "다시/MAG", "만나/VV", "아요/EFN", "./SF"));
        }
        final String expectedResult = new Gson().toJson(expectedMorphList);

        final List<String> argList = new ArrayList<>(List.of("--log", "--disable-print-output"));
        for (int i = 0; i < 4; i++) {
            argList.add(Integer.toString(i).concat("안녕하세요, 반가워요. 잘 있어요. 다시 만나요."));
        }
        final String[] args = argList.toArray(String[]::new);

        final String actualResult = KkmaWrapper.runKkma(args);
        System.out.println(actualResult);

        Assertions.assertEquals(
                expectedResult,
                actualResult
        );
    }

    @DisplayName("Command-line Test (Mass List)")
    @Test
    public void commandTestMassList() {
        final List<List<String>> expectedMorphList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            expectedMorphList.add(List.of("안녕/NNG", "하세/NNG", "요/JX", ",/SP", "반갑/VA", "어요/EFN", "./SF", "잘/MAG", "있/VA", "어요/EFN", "./SF", "다시/MAG", "만나/VV", "아요/EFN", "./SF", Integer.toString(i).concat("/NR")));
        }
        final String expectedResult = new Gson().toJson(expectedMorphList);

        final List<String> argList = new ArrayList<>(List.of("--log", "--disable-print-output"));
        for (int i = 0; i < 1000; i++) {
            argList.add("안녕하세요, 반가워요. 잘 있어요. 다시 만나요.".concat(Integer.toString(i)));
        }
        final String[] args = argList.toArray(String[]::new);

        final String actualResult = KkmaWrapper.runKkma(args);
        System.out.println(actualResult);

        Assertions.assertEquals(
                expectedResult,
                actualResult
        );
    }

    @DisplayName("Command-line Test (--b64)")
    @Test
    public void commandTestB64() {
        final String expectedResult = "[[\"안녕/NNG\",\"하세/NNG\",\"요/JX\",\",/SP\",\"반갑/VA\",\"어요/EFN\",\"./SF\",\"잘/MAG\",\"있/VA\",\"어요/EFN\",\"./SF\",\"다시/MAG\",\"만나/VV\",\"아요/EFN\",\"./SF\"]]";

        final String[] args = {
                "--log",
                "--b64",
                "--disable-print-output",
                Base64.getEncoder().encodeToString("안녕하세요, 반가워요. 잘 있어요. 다시 만나요.".getBytes())
        };

        final String actualResult = KkmaWrapper.runKkma(args);
        final String decodedActualResult = new String(Base64.getDecoder().decode(actualResult));
        System.out.println(decodedActualResult);

        Assertions.assertEquals(
                expectedResult,
                decodedActualResult
        );
    }

    @DisplayName("Command-line Test (--b64 & --pretty)")
    @Test
    public void commandTestB64Pretty() {
        final String expectedResult = "[[\"안녕/NNG\",\"하세/NNG\",\"요/JX\",\",/SP\",\"반갑/VA\",\"어요/EFN\",\"./SF\",\"잘/MAG\",\"있/VA\",\"어요/EFN\",\"./SF\",\"다시/MAG\",\"만나/VV\",\"아요/EFN\",\"./SF\"]]";

        final String[] args = {
                "--log",
                "--b64",
                "--pretty",
                "--disable-print-output",
                Base64.getEncoder().encodeToString("안녕하세요, 반가워요. 잘 있어요. 다시 만나요.".getBytes())
        };

        final String actualResult = KkmaWrapper.runKkma(args);
        final String decodedActualResult = new String(Base64.getDecoder().decode(actualResult));
        System.out.println(decodedActualResult);

        Assertions.assertEquals(
                expectedResult,
                decodedActualResult
        );
    }

    @DisplayName("Command-line Test (--b64 & List)")
    @Test
    public void commandTestB64List() {
        final List<List<String>> expectedMorphList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            expectedMorphList.add(List.of(Integer.toString(i).concat("/NR"), "안녕/NNG", "하세/NNG", "요/JX", ",/SP", "반갑/VA", "어요/EFN", "./SF", "잘/MAG", "있/VA", "어요/EFN", "./SF", "다시/MAG", "만나/VV", "아요/EFN", "./SF"));
        }
        final String expectedResult = new Gson().toJson(expectedMorphList);

        final List<String> textList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            textList.add(Base64.getEncoder().encodeToString(Integer.toString(i).concat("안녕하세요, 반가워요. 잘 있어요. 다시 만나요.").getBytes()));
        }

        final List<String> argList = new ArrayList<>(List.of(
                "--log",
                "--b64",
                "--disable-print-output"
        ));
        argList.addAll(textList);

        final String[] args = argList.toArray(String[]::new);

        final String actualResult = KkmaWrapper.runKkma(args);
        final String decodedActualResult = new String(Base64.getDecoder().decode(actualResult));
        System.out.println(decodedActualResult);

        Assertions.assertEquals(
                expectedResult,
                decodedActualResult
        );
    }

    @DisplayName("Command-line Test (--pretty & List)")
    @Test
    public void commandTestPrettyList() {
        final List<List<String>> expectedMorphList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            expectedMorphList.add(List.of("안녕/NNG", "하세/NNG", "요/JX", ",/SP", "반갑/VA", "어요/EFN", "./SF", "잘/MAG", "있/VA", "어요/EFN", "./SF", "다시/MAG", "만나/VV", "아요/EFN", "./SF", Integer.toString(i).concat("/NR")));
        }
        final String expectedResult = new Gson().toJson(expectedMorphList);

        final List<String> argList = new ArrayList<>(List.of(
                "--log",
                "--pretty"
        ));
        for (int i = 0; i < 1000; i++) {
            argList.add("안녕하세요, 반가워요. 잘 있어요. 다시 만나요.".concat(Integer.toString(i)));
        }
        final String[] args = argList.toArray(String[]::new);

        final String actualResult = KkmaWrapper.runKkma(args);
        System.out.println(actualResult);

        Assertions.assertEquals(
                expectedResult,
                actualResult
        );
    }
}
