package lab1;

import java.io.Serializable;

//abstract class Packet represents frame(packet) of data
public abstract class Packet implements Serializable{
	//it has attributes of protected type availability 
	public String device; //device - name of the device being the data source, description - verbal description of the data
	public String description;
	public long date; //date understood as the number of seconds since the beginning of time
	
	//constructor
	public Packet(String _device, String _description, long _date)
	{
		this.device = _device;
		this.description = _description;
		this.date = _date;
	}
	// method returning a string containing the object's attribute values
	public String toString(){
		String result_pack;
		result_pack="\nDevice = " + device + ", \nDescription = " + description + ", \nDate = " + date;
		return result_pack;
		}
}