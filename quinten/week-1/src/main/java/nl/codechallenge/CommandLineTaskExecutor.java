package nl.codechallenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!test")
@Component
public class CommandLineTaskExecutor implements CommandLineRunner {

    @Autowired
    InputToOutputConverterAndWriter converter;

    @Override
    public void run(String... args) throws Exception {
        if (args.length < 1) {
            System.out.println("Please provide the absolute path to the input csv file as argument");
            System.exit(1);
        }

        converter.createOutputs(args[0]);
    }

}
