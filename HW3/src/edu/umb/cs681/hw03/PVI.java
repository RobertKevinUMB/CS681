package edu.umb.cs681.hw03;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PVI {

    public static void main(String[] args) {

        Path path = Paths.get("Data" ,"PVI.csv");
        try( Stream<String> lines = Files.lines(path) ){
            List<List<String>> matrix = lines.map( line -> {
                return Stream.of( line.split(",") )
                    .map(value->value.substring(0, value.length()))
                    .collect( Collectors.toList() ); })
                    .collect( Collectors.toList() );


            List Total = matrix.stream().filter((i) -> i.get(4).contains("Massachusetts")).collect(Collectors.toList());

            List suffolk_death = matrix.stream().filter((i) -> i.get(5).contains("Suffolk")).collect(Collectors.toList()).get(0);
            System.out.println("\n Number of deaths occurred in the Suffolk county of the Massachusetts state are: " + suffolk_death.get(7));

            String Massachusetts_death = matrix.stream().filter((i) -> i.get(4).contains("Massachusetts"))
                    .map((i) -> i.get(7)).reduce("0", (subtotal, element) -> String.valueOf(Integer.parseInt(subtotal) + Integer.parseInt(element)));

            System.out.println("\n Total number of deaths in State of Massachusetts are: " + Massachusetts_death);

            System.out.println("\n Avg number of deaths in State of Massachusetts are: " + Integer.parseInt(Massachusetts_death)/Total.size());



        }

        catch(IOException ex) {
            ex.printStackTrace();
        }

    }
}
