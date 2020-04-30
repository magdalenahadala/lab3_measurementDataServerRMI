package lab1;

//class Alarm represents alarm or alert
public class Alarm <T> extends Packet {
	int channelNr; //channel number
	int treshold; //value which, when exceeded, indicates alarm / alert
	int direction; //change direction (0 - any, -1 - down, 1 - up)
	
	//constructor
	public Alarm(String _device, String _description, long _date, int _channelNr, int _treshold, int _direction){
		super(_device, _description, _date);
		this.channelNr = _channelNr;
		this.treshold = _treshold;
		this.direction = _direction;
		}
	// method returning a string containing the object's attribute values
	public String toString(){
		String result_pack;
		String result;
		result_pack = super.toString();
		result = "\nAlarm:" + result_pack + ", \nChannel Number = " + channelNr + ", \nTreshold = " + treshold + ", \nDirection = " + direction;
		return result;
		}
}