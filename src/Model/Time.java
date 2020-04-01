package Model;

public class Time {
	
	private int hour;
	private int minute;	
	
	// time is saved in DB as String, we break the String and create Time object
	public Time(String s) throws Exception{
        String[] time = s.split(":"); 
        int hour = Integer.parseInt(time[0]);
        int minute = Integer.parseInt(time[1]);
        if(hour < 24 && hour >= 0)
		{
			if(minute < 60 && minute >= 0)
			{
				this.hour = hour;
				this.minute = minute;
				return;
			}
		}
		throw new Exception("Invalid time values");
	}

	// get and set functions
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	
	// turn object to string
	public String toString() {
		String h = Integer.toString(hour);
		String m = Integer.toString(minute);
		if(h.length() == 1)
			h = "0" + h;
		if(m.length() == 1)
			m = "0" + m;
		return h + ":" + m;
	}
	// return 0 if equal,  1 if t1 is bigger, 2 if t2 is bigger 
	public static int cmpTime(Time t1, Time t2) {
		if(t1.hour > t2.hour) {
			return 1;
		}
		else if(t2.hour > t1.hour) {
			return 2;
		}
		else {
			if(t1.minute > t2.minute) {
				return 1;
			}
			else if(t2.minute > t1.minute) {
				return 2;
			}
			return 0;
		}
	}

	
	

}
