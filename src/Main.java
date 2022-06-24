import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter full file path: ");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            List<Employee> list = new ArrayList<>();
            String line = br.readLine();
            while (line != null){
                String[] fields = line.split(",");
                list.add(new Employee(fields[0],fields[1], Double.parseDouble(fields[2])));
                line = br.readLine();
            }
            double salary = sc.nextDouble();

            double avg = list.stream()
                    .filter(employee -> employee.getName().charAt(0) == 'M')
                    .map(employee -> employee.getSalary())
                    .reduce(0.0, (x,y) -> x+ y);
            System.out.println("Sum of salary of people whose name starts with 'M' " + avg);

            Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());

            List<String> emails = list.stream().filter(product -> product.getSalary() > salary)
                    .map(product -> product.getEmail()).sorted(comp).collect(Collectors.toList());

            emails.forEach(System.out::println);
        }
        catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        sc.close();


    }
}