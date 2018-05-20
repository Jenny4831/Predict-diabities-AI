

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CFSFold {

	public static void main(String[] args) throws FileNotFoundException {
		List<CFSPima> dataList = new ArrayList<CFSPima>();
		List<CFSPima> yesList = new ArrayList<CFSPima>();
		List<CFSPima> noList = new ArrayList<CFSPima>();

		// read data into list
		Scanner sc = new Scanner(new File(args[0]));
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String tokens[] = line.split(",");
			CFSPima cfsPima = new CFSPima(tokens);
			dataList.add(cfsPima);

			if (cfsPima.type.equals("yes")) {
				yesList.add(cfsPima);
			}
			if (cfsPima.type.equals("no")) {
				noList.add(cfsPima);
			}
		}
		sc.close();

		int yesNum = yesList.size() / 10;
		int noNum = noList.size() / 10;

		List<List<CFSPima>> foldsDataList = new ArrayList<List<CFSPima>>(10);

		// split data
		for (int i = 0; i < 10; i++) {
			List<CFSPima> foldData = new ArrayList<CFSPima>();

			for (int j = 0; j < yesNum; j++) {
				CFSPima cfsPima = yesList.remove(0);
				foldData.add(cfsPima);
			}

			for (int j = 0; j < noNum; j++) {
				CFSPima cfsPima = noList.remove(0);
				foldData.add(cfsPima);
			}

			foldsDataList.add(foldData);
		}

		// for any left over data from yesList and noList
		// put it in 10 folders in order
		List<CFSPima> remainList = new ArrayList<CFSPima>();
		remainList.addAll(yesList);
		remainList.addAll(noList);

		for (int i = 0; !remainList.isEmpty(); i = (i + 1) % 10) {
			foldsDataList.get(i).add(remainList.remove(0));
		}

		// write folds into file
		PrintWriter pw = new PrintWriter(new File(args[1]));
		// test
		for (int i = 0; i < 10; i++) {
			List<CFSPima> foldData = foldsDataList.get(i);
			pw.printf("fold%d\n", i+1);
			for(CFSPima cfsPima: foldData) {
				pw.println(cfsPima);
			}
			pw.println();
		}

		pw.close();
	}

}
