import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Comparator;

public class MyClassifier {

	private static void nn(List<Pima> trainingList, Pima pima, int k) {
		List<Pima> cloneTrainingList = new ArrayList<Pima>(trainingList);

		Collections.sort(cloneTrainingList, new Comparator<Pima>() {
			public int compare(Pima x, Pima y) {
				double d1 = x.distance(pima);
				double d2 = y.distance(pima);
				return d1 > d2 ? 1 : d1 == d2 ? 0 : -1;
			}
		});

		int yesCount = 0, noCount = 0;
		for (int i = 0; i < k; i++) {
			if (cloneTrainingList.get(i).type.equals("yes")) {
				yesCount++;
			} else {
				noCount++;
			}
		}

		if (yesCount >= noCount) {
			System.out.println("yes");
		} else {
			System.out.println("no");
		}

	}

	public static void nn(List<Pima> trainingList, List<Pima> testingList, int k) {
		for (Pima pima : testingList) {
			nn(trainingList, pima, k);
		}
	}

	private static double[] calMean(List<Pima> list, String type) {
		double meanArray[];
		if(list.get(0).size == 5){
			meanArray = new double[5];
			long size = list.stream().filter(o -> o.type.equals(type)).count();
			meanArray[0] = list.stream().filter(o -> o.type.equals(type)).mapToDouble(o -> o.glucouse).sum() / size;
			meanArray[1] = list.stream().filter(o -> o.type.equals(type)).mapToDouble(o -> o.insulin).sum() / size;
			meanArray[2] = list.stream().filter(o -> o.type.equals(type)).mapToDouble(o -> o.bmi).sum() / size;
			meanArray[3] = list.stream().filter(o -> o.type.equals(type)).mapToDouble(o -> o.pedigree).sum() / size;
			meanArray[4] = list.stream().filter(o -> o.type.equals(type)).mapToDouble(o -> o.age).sum() / size;
		}else{
			meanArray = new double[8];
			long size = list.stream().filter(o -> o.type.equals(type)).count();
			meanArray[0] = list.stream().filter(o -> o.type.equals(type)).mapToDouble(o -> o.preg).sum() / size;
			meanArray[1] = list.stream().filter(o -> o.type.equals(type)).mapToDouble(o -> o.glucouse).sum() / size;
			meanArray[2] = list.stream().filter(o -> o.type.equals(type)).mapToDouble(o -> o.blood).sum() / size;
			meanArray[3] = list.stream().filter(o -> o.type.equals(type)).mapToDouble(o -> o.tricep).sum() / size;
			meanArray[4] = list.stream().filter(o -> o.type.equals(type)).mapToDouble(o -> o.insulin).sum() / size;
			meanArray[5] = list.stream().filter(o -> o.type.equals(type)).mapToDouble(o -> o.bmi).sum() / size;
			meanArray[6] = list.stream().filter(o -> o.type.equals(type)).mapToDouble(o -> o.pedigree).sum() / size;
			meanArray[7] = list.stream().filter(o -> o.type.equals(type)).mapToDouble(o -> o.age).sum() / size;
		}
		return meanArray;
	}

	private static double[] calStd(List<Pima> list, double[] meanArray, String type) {
		double stdArray[];
		if(list.get(0).size == 5){
			stdArray = new double[8];
			long size = list.stream().filter(o -> o.type.equals(type)).count() - 1;
			stdArray[0] = Math.sqrt(list.stream().filter(o -> o.type.equals(type))
					.mapToDouble(o -> Math.pow(o.glucouse - meanArray[0], 2)).sum() / size);
			stdArray[1] = Math.sqrt(list.stream().filter(o -> o.type.equals(type))
					.mapToDouble(o -> Math.pow(o.insulin - meanArray[1], 2)).sum() / size);
			stdArray[2] = Math.sqrt(list.stream().filter(o -> o.type.equals(type))
					.mapToDouble(o -> Math.pow(o.bmi - meanArray[2], 2)).sum() / size);
			stdArray[3] = Math.sqrt(list.stream().filter(o -> o.type.equals(type))
					.mapToDouble(o -> Math.pow(o.pedigree - meanArray[3], 2)).sum() / size);
			stdArray[4] = Math.sqrt(list.stream().filter(o -> o.type.equals(type))
					.mapToDouble(o -> Math.pow(o.age - meanArray[4], 2)).sum() / size);
		}else{
			stdArray = new double[8];
			long size = list.stream().filter(o -> o.type.equals(type)).count() - 1;
			stdArray[0] = Math.sqrt(list.stream().filter(o -> o.type.equals(type))
					.mapToDouble(o -> Math.pow(o.preg - meanArray[0], 2)).sum() / size);
			stdArray[1] = Math.sqrt(list.stream().filter(o -> o.type.equals(type))
					.mapToDouble(o -> Math.pow(o.glucouse - meanArray[1], 2)).sum() / size);
			stdArray[2] = Math.sqrt(list.stream().filter(o -> o.type.equals(type))
					.mapToDouble(o -> Math.pow(o.blood - meanArray[2], 2)).sum() / size);
			stdArray[3] = Math.sqrt(list.stream().filter(o -> o.type.equals(type))
					.mapToDouble(o -> Math.pow(o.tricep - meanArray[3], 2)).sum() / size);
			stdArray[4] = Math.sqrt(list.stream().filter(o -> o.type.equals(type))
					.mapToDouble(o -> Math.pow(o.insulin - meanArray[4], 2)).sum() / size);
			stdArray[5] = Math.sqrt(
					list.stream().filter(o -> o.type.equals(type)).mapToDouble(o -> Math.pow(o.bmi - meanArray[5], 2)).sum()
							/ size);
			stdArray[6] = Math.sqrt(list.stream().filter(o -> o.type.equals(type))
					.mapToDouble(o -> Math.pow(o.pedigree - meanArray[6], 2)).sum() / size);
			stdArray[7] = Math.sqrt(
					list.stream().filter(o -> o.type.equals(type)).mapToDouble(o -> Math.pow(o.age - meanArray[7], 2)).sum()
							/ size);
		}
		return stdArray;
	}

	private static double calDensity(double val, double mean, double std) {
		double left = 1.0 / std * Math.sqrt(2 * Math.PI);
		double power = -Math.pow(val - mean, 2) / (2 * Math.pow(std, 2));
		double right = Math.pow(Math.E, power);
		return left * right;
	}

	public static void nb(List<Pima> trainingList, List<Pima> testingList) {
		int size = trainingList.size();
		double meanYesArray[] = calMean(trainingList, "yes");
		double stdYesArray[] = calStd(trainingList, meanYesArray, "yes");
		double meanNoArray[] = calMean(trainingList, "no");
		double stdNoArray[] = calStd(trainingList, meanNoArray, "no");

		double pYes = (double) trainingList.stream().filter(o -> o.type.equals("yes")).count() / size;
		double pNo = (double) trainingList.stream().filter(o -> o.type.equals("no")).count() / size;
		
		
		if(trainingList.get(0).size == 5){
			for (Pima pima : testingList) {
				double pPredictYes = pYes;
				double py[] = new double[5];
				py[0] = calDensity(pima.glucouse, meanYesArray[0], stdYesArray[0]);
				py[1] = calDensity(pima.insulin, meanYesArray[1], stdYesArray[1]);
				py[2] = calDensity(pima.bmi, meanYesArray[2], stdYesArray[2]);
				py[3] = calDensity(pima.pedigree, meanYesArray[3], stdYesArray[3]);
				py[4] = calDensity(pima.age, meanYesArray[4], stdYesArray[4]);
				
				for (double d : py) {
					pPredictYes *= d;
				}

				double pPredictNo = pNo;

				double pn[] = new double[5];
				pn[0] = calDensity(pima.glucouse, meanNoArray[0], stdNoArray[0]);
				pn[1] = calDensity(pima.insulin, meanNoArray[1], stdNoArray[1]);
				pn[2] = calDensity(pima.bmi, meanNoArray[2], stdNoArray[2]);
				pn[3] = calDensity(pima.pedigree, meanNoArray[3], stdNoArray[3]);
				pn[4] = calDensity(pima.age, meanNoArray[4], stdNoArray[4]);
				
				for (double d : pn) {
					pPredictNo *= d;
				}

				if (pPredictYes > pPredictNo) {
					System.out.println("yes");
				} else {
					System.out.println("no");
				}

			}
		}else{
			for (Pima pima : testingList) {
				double pPredictYes = pYes;
				
				double py[] = new double[8];
				py[0] = calDensity(pima.preg, meanYesArray[0], stdYesArray[0]);
				py[1] = calDensity(pima.glucouse, meanYesArray[1], stdYesArray[1]);
				py[2] = calDensity(pima.blood, meanYesArray[2], stdYesArray[2]);
				py[3] = calDensity(pima.tricep, meanYesArray[3], stdYesArray[3]);
				py[4] = calDensity(pima.insulin, meanYesArray[4], stdYesArray[4]);
				py[5] = calDensity(pima.bmi, meanYesArray[5], stdYesArray[5]);
				py[6] = calDensity(pima.pedigree, meanYesArray[6], stdYesArray[6]);
				py[7] = calDensity(pima.age, meanYesArray[7], stdYesArray[7]);
	
				for (double d : py) {
					pPredictYes *= d;
				}
	
				double pPredictNo = pNo;
	
				double pn[] = new double[8];
				pn[0] = calDensity(pima.preg, meanNoArray[0], stdNoArray[0]);
				pn[1] = calDensity(pima.glucouse, meanNoArray[1], stdNoArray[1]);
				pn[2] = calDensity(pima.blood, meanNoArray[2], stdNoArray[2]);
				pn[3] = calDensity(pima.tricep, meanNoArray[3], stdNoArray[3]);
				pn[4] = calDensity(pima.insulin, meanNoArray[4], stdNoArray[4]);
				pn[5] = calDensity(pima.bmi, meanNoArray[5], stdNoArray[5]);
				pn[6] = calDensity(pima.pedigree, meanNoArray[6], stdNoArray[6]);
				pn[7] = calDensity(pima.age, meanNoArray[7], stdNoArray[7]);
	
				for (double d : pn) {
					pPredictNo *= d;
				}
	
				if (pPredictYes > pPredictNo) {
					System.out.println("yes");
				} else {
					System.out.println("no");
				}
	
			}
		}

	}

	public static void main(String[] args) throws FileNotFoundException {
		
		List<Pima> trainingList = new ArrayList<Pima>();
		List<Pima> testingList = new ArrayList<Pima>();

		// read training data
		Scanner sc = new Scanner(new File(args[0]));
	
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			trainingList.add(new Pima(line.split(",")));
			

		}
		// read testing data
		sc = new Scanner(new File(args[1]));
		
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			testingList.add(new Pima(line.split(",")));			
		}
		sc.close();

		if (args[2].equals("NB")) {
			nb(trainingList, testingList);
		} else {
			int k = args[2].charAt(0) - '0';
			nn(trainingList, testingList, k);
		}
	}

}
