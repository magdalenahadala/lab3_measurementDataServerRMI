package lab1;

//testing of created classes
public class Main{
	public static void main(String[] args){
		Integer[] tab1 = {1, 2, 3, 4, 5, 6, 7};
		Double[] tab2 = {1.1, 2.1, 3.2, 4.3, 5.4};
		TimeHistory<Integer> TH = new TimeHistory<Integer>("device1", "description1", 2020, 1, "unit1", 15.6, tab1 , 10.20);
		Spectrum<Double> S = new Spectrum<Double>("device2", "description2", 2020, 1, "unit2", 15.6, tab2 , 1);
		Alarm A = new Alarm ("device3", "description3", 2020, 1, 20, -1 );
		System.out.println(TH.toString());
		System.out.println(S.toString());
		System.out.println(A.toString());
		}
}
