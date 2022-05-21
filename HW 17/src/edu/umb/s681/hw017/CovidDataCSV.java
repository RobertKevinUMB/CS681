package edu.umb.s681.hw017;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors; 
import java.util.stream.Stream;
public class CovidDataCSV implements Runnable{
    public static void main(String[] args) {
		Thread t1 = new Thread(new CovidDataCSV());
		t1.start();
		Thread t2 = new Thread(new CovidDataCSV());
		t2.start();
		
	}

	@Override
	public void run() {
		Path file_path = Paths.get("./src/Data/CovidPVI_Data.txt"); 
		try( Stream<String> l = Files.lines(file_path) ){
			List<List<String>> matrix = l.map( line -> { 
				return Stream.of( line.split(",")).map(value->value.substring(0, value.length())) .collect( Collectors.toList() ); 
			}).collect(Collectors.toList());
			List<?> deaths_in_suffolk = matrix.stream().parallel().filter((i) -> i.get(5).contains("Suffolk")).collect(Collectors.toList()).get(0);
			String deaths_in_Massachusetts = matrix.stream().parallel().filter((i) -> i.get(4).contains("Massachusetts"))
					.map((i) -> i.get(7)).reduce("0", (subtotal, element) -> String.valueOf(Integer.parseInt(subtotal) + Integer.parseInt(element)));
			System.out.println("\nTotal number of deaths due to Covid in Suffolk county are:: " + deaths_in_suffolk.get(7)+" " + Thread.currentThread().getName());
			System.out.println("\nTotal number of deaths due to Covid in Massachusetts are: " + deaths_in_Massachusetts+" " + Thread.currentThread().getName());
					}
        catch(IOException ex) {
        	ex.printStackTrace(); 
        }
		
	} 
}
