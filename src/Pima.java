
public class Pima {
	// @attribute 'Pregnancy times' numeric
	// @attribute ' Glucouse test' numeric
	// @attribute ' Blood pressure' numeric
	// @attribute 'Tricep skinfold thickness' numeric
	// @attribute ' Insulin' numeric
	// @attribute ' BMI' numeric
	// @attribute ' Pedigree function' numeric
	// @attribute ' Age' numeric
	double preg;
	double glucouse;
	double blood;
	double tricep;
	double insulin;
	double bmi;
	double pedigree;
	double age;
	String type;
	int size;
	public Pima(String tokens[]) {
		if(tokens.length == 6 || tokens.length == 5){
			this.glucouse = Double.parseDouble(tokens[0]);
			this.insulin = Double.parseDouble(tokens[1]);
			this.bmi = Double.parseDouble(tokens[2]);
			this.pedigree = Double.parseDouble(tokens[3]);
			this.age = Double.parseDouble(tokens[4]);
			if(tokens.length == 6){
				this.type = tokens[5];
			}
			this.size = 5;
		}else{
			this.preg = Double.parseDouble(tokens[0]);
			this.glucouse = Double.parseDouble(tokens[1]);
			this.blood = Double.parseDouble(tokens[2]);
			this.tricep = Double.parseDouble(tokens[3]);
			this.insulin = Double.parseDouble(tokens[4]);
			this.bmi = Double.parseDouble(tokens[5]);
			this.pedigree = Double.parseDouble(tokens[6]);
			this.age = Double.parseDouble(tokens[7]);
			if (tokens.length == 9) {
				this.type = tokens[8];
			}
			this.size = 8;
		}
	}
	
//	public CFSPima(String tokens[]){
//		this.glucouse = Double.parseDouble(tokens[0]);
//		this.insulin = Double.parseDouble(tokens[1]);
//		this.bmi = Double.parseDouble(tokens[2]);
//		this.pedigree = Double.parseDouble(tokens[3]);
//		this.age = Double.parseDouble(tokens[4]);
//	}

	public String toString() {
		return String.format("%f,%f,%f,%f,%f,%f,%f,%f,%s", this.preg, this.glucouse, this.blood, this.tricep,
				this.insulin, this.bmi, this.pedigree, this.age, this.type);
	}

	public double distance(Pima that) {
		double sum = 0;
		if(that.size == 5){
			sum += Math.pow(this.glucouse - that.glucouse, 2);
			sum += Math.pow(this.insulin - that.insulin, 2);
			sum += Math.pow(this.bmi - that.bmi, 2);
			sum += Math.pow(this.pedigree - that.pedigree, 2);
			sum += Math.pow(this.age - that.age, 2);
		}else{
			sum += Math.pow(this.preg - that.preg, 2);
			sum += Math.pow(this.glucouse - that.glucouse, 2);
			sum += Math.pow(this.blood - that.blood, 2);
			sum += Math.pow(this.tricep - that.tricep, 2);
			sum += Math.pow(this.insulin - that.insulin, 2);
			sum += Math.pow(this.bmi - that.bmi, 2);
			sum += Math.pow(this.pedigree - that.pedigree, 2);
			sum += Math.pow(this.age - that.age, 2);
		}
		return Math.sqrt(sum);
	}

}
