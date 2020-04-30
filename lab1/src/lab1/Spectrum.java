package lab1;

//class Spectrum which represents the amplitude spectrum
public class Spectrum <T> extends Sequence <T>{
	public int scailing; //denotes the type of scale (linear or logarithmic)
	
	public Spectrum(String _device, String _description, long _date, int _channelNr, String _unit, double _resolution, T[] _table, int i){
		//super constructor of the class
		super(_device, _description, _date, _channelNr, _unit, _resolution, _table);
		this.scailing = i;
		}
	
	// method returning a string containing the object's attribute values
	public String toString(){
		String result_seq;
		String result;
		result_seq = super.toString();
		result = "\nSpectrum:" + result_seq + " \nScailing = " + scailing;
		return result;
		}
}