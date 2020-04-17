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
	
	// The function gets Time t1 and float t2. t2 represents duration in hours
	// and returns the t1-t2
	public static Time sub(Time t1, float t2) throws Exception
	{
		
		int hour = t1.getHour() - Math.round(t2);
		
		int minute = (int) (t1.getMinute() - (t2%1)*60);
		if(minute<0)
		{
			hour--;
			minute += 60;
		}
		
		if(hour<0)
			hour += 24;
		
		Time t = new Time(String.valueOf(hour) + ":" + String.valueOf(minute));
		return t;
		
	}
	
	public static Time add(Time t1, float t2) throws Exception
	{
		
		int hour = t1.getHour() + Math.round(t2);
		
		int minute = (int) (t1.getMinute() + (t2%1)*60);
		if(minute>60)
		{
			hour++;
			minute += 60;
		}
		if(hour>24)
			hour -= 24;
		
		Time t = new Time(String.valueOf(hour) + ":" + String.valueOf(minute).charAt(0) +  String.valueOf(minute).charAt(1));
		return t;
	}
	
	
	// return 0 if t1=t2, 1 if t1>t2 and -1 of t1<t2
	public int compareTo(Time t2) {
		
		if(this.hour < t2.hour) {
			return -1;
		}
		
		if(this.hour > t2.hour) {
			return 1;
		}
		
		else if(this.minute > t2.minute) {
			return 1;
		}
		
		else if(this.minute < t2.minute)
			return -1;
		
		return 0;
	}

	}

