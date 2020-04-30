package lab1;

//class TimeHistory which represents the time course
public class TimeHistory <T> extends Sequence <T>{
	public double sensitivity; //represents voltage sensitivity
	
	//constructor
	public TimeHistory(String _device, String _description, long _date, int _channelNr, String _unit, double _resolution,T[] _table, double _sensitivity){
		super(_device, _description, _date, _channelNr, _unit, _resolution, _table);
		this.sensitivity = _sensitivity;
		}
	
	// method returning a string containing the object's attribute values
	public String toString(){
		String result_seq;
		String result;
		result_seq=super.toString();
		result ="TimeHistory:"+ result_seq + " \nSensitivity = " + sensitivity;
		return result;
		}
}