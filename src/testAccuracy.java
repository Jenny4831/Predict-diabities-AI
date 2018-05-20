import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class testAccuracy {
	public static void main(String[] args) throws FileNotFoundException{
		List<String> output = new ArrayList<String>();
		List<String> expected = new ArrayList<String>();
		Scanner sc = new Scanner(new File(args[0]));
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			output.add(line);
		}
		sc = new Scanner(new File(args[1]));
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			expected.add(line);
		}
		sc.close();
		
		int correct = 0;
		for(int i = 0; i < output.size(); i++){
			if(output.get(i).contains(expected.get(i))){
				//System.out.println(output.get(i) + );
				correct++;
			}
		}
		double accuracy = (double)correct/(double)expected.size();
		System.out.print(accuracy * 100 + "%");
		
	}
}
