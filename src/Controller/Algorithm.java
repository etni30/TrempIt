package Controller;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import API.ModelInterface;
import Model.Group;
import Model.Model;
import Model.Path;
import Model.Time;
import javafx.util.Pair;

public class Algorithm {

	private ModelInterface model = null;

	public Algorithm() {
    	this.model = new Model();
    }
	
	 public Time calcDepartT(float distance, Time arriveT) throws Exception
	    {
	    	float walkTime = calcTime(distance, 7);
	    	Time departureT = Time.sub(arriveT, walkTime);
	    	return departureT;
	    }
	 private Time calcArriveT(Time groupDepTime, float distBtwnCities, float distBtwnDests) throws Exception {
			
		 float driveTime = calcTime(distBtwnCities, 70);
		 float walkTime = calcTime(distBtwnDests, 7);
		 
		 Time arriveT = Time.add(groupDepTime, driveTime + walkTime);
			return null;
		}
	//______________________________________________________________________________________________________________
    // Method name: Find_Tremp
    // Description: The function returns a list of paths between the two stations
    
    public ArrayList<Path> Find_Tramp(String srcStation, String srcCity, String dstStation,
    											String dstCity, Time desiredArriveT) throws Exception
    {

    	//-----------------------------Variables initializations----------------------------------------//
    	
    	ArrayList <Path> paths = new ArrayList <Path>();
		Time departureTime;
		Time estimateArriveTime;
    	
    	LinkedList<Group> tramps;
    	//----------------------------------------------------------------------------------------------//
    	
    	//-------------If the source Station and the dst station are in the same city-------------------//
    	if(srcCity.equals(dstCity))	
    	{
    		Float distance = new Float(model.getDistance(srcStation, dstStation));
    		Time departT = calcDepartT(distance, desiredArriveT);
    		
    		Path p = new Path(null, distance, departT, desiredArriveT);
    		paths.add(p);
    		
    		return paths;
    	}
    	//--------------------------------------------------------------------------------------------//
    	
    	//------------------------ Get all tramps between the two cities-------------------------//
    	else try {
			tramps = model.getGroups(srcCity, dstCity);
		} catch (Exception e) {
			System.out.println(e);
			throw e;
		}
    	//--------------------------------------------------------------------------------------//
    	
    	//-------- Go over all the tramps and put them in a list of pairs <Group, float>--------//
    	    	
    	for( Group tramp: tramps)
    	{
    		String groupSrcStation = tramp.getSourceStation();	//
    		String groupDstStation = tramp.getdstStation();		//	Get src's and dst's
    		String groupSrcCity = tramp.getSourceCity();		//
    		String groupDstCity = tramp.getdstCity();			//
    		
    		float distBtwnSrcs = model.getDistance(srcStation, groupSrcStation);	 //	Get distances
    		float distBtwnDests = model.getDistance(dstStation, groupDstStation);	 // 
    		float distBtwnCities = model.getDistance(groupSrcCity, groupDstStation); // get dist btwn cities

    		
    		Time groupDepTime = tramp.getDepTime();									 // get departure time
    		Time departT = calcDepartT(distBtwnSrcs, groupDepTime);					 // calc departure time

    		Time arriveT = calcArriveT(groupDepTime, distBtwnCities, distBtwnDests); // calc arriveT
    		
    		Group g = new Group(tramp);												// Create a new group object
    		Float walkDistance = new Float(distBtwnSrcs + distBtwnDests);			// Create the walkDistance Float object
    		
    		Path p = new Path(g, walkDistance, departT, arriveT);
    		
    		paths.add(p);
    		
    	}
    	
    	// At this point we have put all of the paths in a list. Now we will sort it.
    	
    	Collections.sort(paths, new Comparator<Path>()
		{
			@Override
			public int compare(Path p1, Path p2)
			{
				return Float.compare(p1.getWalkDistance(), p2.getWalkDistance());
			}
		});
    	
    	return paths;
    		
    }
    //_______________________________________________________________________________________________________________

    public float calcTime(float distance, float speed)
    {
    	return distance/speed;
    }
    
    
}
