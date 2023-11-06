package kr.paragonnov;


import com.google.gson.Gson;
import org.snu.ids.kkma.ma.*;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class KkmaWrapper {

    public static final String helpMessage = ">>> Usage <<<\n  > java -jar kkma-wrapper.jar <Options> <Input Text 1> <Input Text 2> <Input Text 3> ...\n\n>>> Options <<<\n  --help\t\t\t help.\n  --log\t\t\t\t enable kkma log output.\n  --b64\t\t\t\t enable base64 input/output.\n  --disable-print-output\t disable output printing.\n";

    public static void main(String[] args) {
        KkmaWrapper.runKkma(args);
    }

    public static String runKkma(String[] args) {
        final List<String> argList = new ArrayList<>(List.of(args));

        // Option arguments
        final boolean isHelp = argList.remove("--help");
        final boolean enableLog = argList.remove("--log");
        final boolean enableB64 = argList.remove("--b64");
        final boolean enablePrint = !argList.remove("--disable-print-output");

        // Help
        if (isHelp) {
            System.out.println(helpMessage);
            return helpMessage;
        }

        // Empty result
        String emptyResult = "[[]]";
        // Empty result with B64
        if (enableB64) {
            emptyResult = Base64.getEncoder().encodeToString(emptyResult.getBytes());
        }

        // Return empty result
        if (argList.isEmpty()) {
            // Print result
            if (enablePrint) {
                System.out.print(emptyResult);
            }
            return emptyResult;
        }

        // Target string
        List<String> stringList = argList.subList(0, argList.size());
        // Target string with B64
        if (enableB64) {
            stringList = stringList.stream().map(s -> new String(Base64.getDecoder().decode(s.getBytes()))).toList();
        }

        final PrintStream systemOut = System.out;
        // Disable log printing
        if (!enableLog) {
            System.setOut(new PrintStream(PrintStream.nullOutputStream()));
        }

        final MorphemeAnalyzer morphemeAnalyzer = new MorphemeAnalyzer();
        // Create logger
        if (enableLog) {
            morphemeAnalyzer.createLogger(null);
        }

        final List<List<String>> outputList = new ArrayList<>();
        for (String string : stringList) {
            try {
                // Analyze morph
                List<MExpression> result = morphemeAnalyzer.analyze(string);
                result = morphemeAnalyzer.postProcess(result);
                result = morphemeAnalyzer.leaveJustBest(result);

                final List<String> morphList = result
                        .stream()
                        .flatMap(Collection::stream)
                        .flatMap(Collection::stream)
                        .map(Morpheme::getSmplStr)
                        .toList();

                outputList.add(morphList);
            } catch (Exception exception) {
                // Print result
                if (enablePrint) {
                    outputList.add(List.of());
                }
            }
        }

        // Morph list
        String resultString = new Gson().toJson(outputList);
        // Morph list with B64
        if (enableB64) {
            resultString = Base64.getEncoder().encodeToString(resultString.getBytes());
        }

        // Disable log printing
        if (!enableLog) {
            System.setOut(new PrintStream(systemOut, false, StandardCharsets.UTF_8));
        }

        // Print result
        if (enablePrint) {
            System.out.print(resultString);
        }

        // Close logger
        if (enableLog) {
            morphemeAnalyzer.closeLogger();
        }

        return resultString;
    }
}
