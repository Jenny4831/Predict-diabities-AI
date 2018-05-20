
public class CFSPima {
	// @attribute 'Pregnancy times' numeric
	// @attribute ' Glucouse test' numeric
	// @attribute ' Blood pressure' numeric
	// @attribute 'Tricep skinfold thickness' numeric
	// @attribute ' Insulin' numeric
	// @attribute ' BMI' numeric
	// @attribute ' Pedigree function' numeric
	// @attribute ' Age' numeric
	
	double glucouse;
	double insulin;
	double bmi;
	double pedigree;
	double age;
	String type;
	
	public CFSPima(String tokens[]) {
			this.glucouse = Double.parseDouble(tokens[0]);
			this.insulin = Double.parseDouble(tokens[1]);
			this.bmi = Double.parseDouble(tokens[2]);
			this.pedigree = Double.parseDouble(tokens[3]);
			this.age = Double.parseDouble(tokens[4]);
			this.type = tokens[5];
	}
	
	public String toString() {
		return String.format("%f,%f,%f,%f,%f,%s", this.glucouse, 
				this.insulin, this.bmi, this.pedigree, this.age, this.type);
	}

}
