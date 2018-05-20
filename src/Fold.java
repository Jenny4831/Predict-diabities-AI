

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fold {

	public static void main(String[] args) throws FileNotFoundException {
		List<Pima> dataList = new ArrayList<Pima>();
		List<Pima> yesList = new ArrayList<Pima>();
		List<Pima> noList = new ArrayList<Pima>();

		// read data into list
		Scanner sc = new Scanner(new File(args[0]));
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String tokens[] = line.split(",");
			Pima pima = new Pima(tokens);
			dataList.add(pima);

			if (pima.type.equals("yes")) {
				yesList.add(pima);
			}
			if (pima.type.equals("no")) {
				noList.add(pima);
			}
		}
		sc.close();

		int yesNum = yesList.size() / 10;
		int noNum = noList.size() / 10;

		List<List<Pima>> foldsDataList = new ArrayList<List<Pima>>(10);

		// split data
		for (int i = 0; i < 10; i++) {
			List<Pima> foldData = new ArrayList<Pima>();

			for (int j = 0; j < yesNum; j++) {
				Pima pima = yesList.remove(0);
				foldData.add(pima);
			}

			for (int j = 0; j < noNum; j++) {
				Pima pima = noList.remove(0);
				foldData.add(pima);
			}

			foldsDataList.add(foldData);
		}

		// for any left over data from yesList and noList
		// put it in 10 folders in order
		List<Pima> remainList = new ArrayList<Pima>();
		remainList.addAll(yesList);
		remainList.addAll(noList);

		for (int i = 0; !remainList.isEmpty(); i = (i + 1) % 10) {
			foldsDataList.get(i).add(remainList.remove(0));
		}

		// write folds into file
		PrintWriter pw = new PrintWriter(new File(args[1]));
		// test
		for (int i = 0; i < 10; i++) {
			List<Pima> foldData = foldsDataList.get(i);
			pw.printf("fold%d\n", i+1);
			for(Pima pima: foldData) {
				pw.println(pima);
			}
			pw.println();
		}

		pw.close();
	}

}
