package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.concurrent.Callable;


@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    @Parameters(index = "0", description = "path to first file")
    private File filepath1;

    @Parameters(index = "1", description = "path to second file")
    private File filepath2;

    @Option(names = {"-f", "--format"}, description = "[default: stylish]")
    private String format = "stylish";


    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {

        System.out.println(filepath1.toString());
        System.out.println(filepath2.toString());
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        String output = Differ.generate(filepath1.toString(), filepath2.toString());

        System.out.println(output);
        return 0;
    }
}
