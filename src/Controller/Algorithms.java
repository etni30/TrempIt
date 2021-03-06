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
//import javafx.util.Pair;

public class Algorithms {

	private ModelInterface model = null;

	public Algorithms() throws Exception {
    	this.model = new Model();
    }
	
	 private Time calcDepartT(float distance, Time arriveT) throws Exception
	    {
	    	float walkTime = calcTime(distance, 7);
	    	Time departureT = Time.sub(arriveT, walkTime);
	    	return departureT;
	    }
	 private Time calcArriveT(Time groupDepTime, float distBtwnCities, float distBtwnDests) throws Exception {
		 float driveTime = calcTime(distBtwnCities, 70);	// assume a car drives at 70 kmph
		 float walkTime = calcTime(distBtwnDests, 7);		// assume a car drives at 7 kmph
		 Time arriveT = Time.add(groupDepTime, driveTime + walkTime);

		 return arriveT;
		}
	//______________________________________________________________________________________________________________
    // Method name: findTramps
    // Description: The function returns a list of paths between the two stations
    // prefer:
	// 	0 = minimum walk
	//	1 = early arriveTime
    public ArrayList<Path> findTramps(String srcStation, String srcCity, String dstStation,
    											String dstCity, Time desiredArriveT, Time departureT, int prefer) throws Exception
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
			
			throw new Exception("DATABASE ERROR");
		}
    	//--------------------------------------------------------------------------------------//
    	
    	//-------- Go over all the tramps and put them in a list of pairs <Group, float>--------//
    	    	
    	for( Group tramp: tramps)
    	{	

    		String groupSrcStation = tramp.getSourceStation();	//
    		String groupDstStation = tramp.getdstStation();		//	Get src's and dst's
    		String groupSrcCity = tramp.getSourceCity();		//
    		String groupDstCity = tramp.getdstCity();			//
    		
    		float distBtwnSrcs = 0;
    		float distBtwnDests = 0;
    		float distBtwnCities = 0;

    		System.out.println("srcStation: " + srcStation);
    		System.out.println("groupSrcStation: " + groupSrcStation);
    		System.out.println("dstStation: " + dstStation);
    		System.out.println("groupDstStation: " + groupDstStation);
    		
    		if( !srcStation.equals(groupSrcStation))
    			distBtwnSrcs = model.getDistance(srcStation, groupSrcStation);	 	 //	If not the same, get distance

    		if( !dstStation.equals(groupDstStation))
        		distBtwnDests = model.getDistance(dstStation, groupDstStation);		 //	If not the same, get distance 

    		distBtwnCities = model.getDistance(groupSrcCity, groupDstCity); 		 // get dist btwn cities

    		Time groupDepTime = tramp.getDepTime();									 // get group departure time

    		Time departT = calcDepartT(distBtwnSrcs, groupDepTime);					 // calc departure time

    		Time arriveT = calcArriveT(groupDepTime, distBtwnCities, distBtwnDests); // calc arriveT

    		Group g = new Group(tramp);											 	// Create a new group object
    		Float walkDistance = new Float(distBtwnSrcs + distBtwnDests);			 // Create the walkDistance Float object
    		
    		Path p = new Path(g, walkDistance, departT, arriveT);
    		
    		paths.add(p);
    	}

    	// At this point we have put all of the paths in a list
    	
    	paths = removeIrrelevantPaths(paths, departureT, desiredArriveT);
    	
    	sortPriority(paths, prefer); // sort by preference
    	
    	return paths;
    		
    }
    //_______________________________________________________________________________________________________________

    private ArrayList<Path> removeIrrelevantPaths(ArrayList<Path> paths, Time departureT, Time desiredArriveT)
    {
    	ArrayList<Path> relevantPaths = new ArrayList<Path>();
    	for( Path path: paths)
    	{
    		// if path DepT >= user depTime add it to relevant paths
    		if(path.getDepartureTime().compareTo(departureT) != -1)	
    		{
    			// if path ArriveTime <= user desired arrive time add it to relevant paths
    			if(path.getArriveTime().compareTo(desiredArriveT) != 1)
    				relevantPaths.add(path);
    		}
    		
    		
    	}
    	return relevantPaths;
    }
    
    private void sortPriority(ArrayList<Path> paths, int prefer)
    {
    	switch(prefer)
    	{

    	case 0:	// Sort by minimum walk distance
    	{
    		Collections.sort(paths, new Comparator<Path>()
    		{
    			@Override
    			public int compare(Path p1, Path p2)
    			{
    				int res =  Float.compare(p1.getWalkDistance(), p2.getWalkDistance());
    				return res;
    			}
    		});
    		
    		break;
    	}
    	
    	case 1:	// Sort by earliest arrive time
    	{
    		Collections.sort(paths, new Comparator<Path>()
    		{
    			@Override
    			public int compare(Path p1, Path p2)
    			{
    				return p1.getArriveTime().compareTo(p2.getArriveTime());
    			}
    		});
    		
    		break;
    	}
    	
    	case 2:	// Sort by closest departure time to desired departure time
    	{
    		Collections.sort(paths, new Comparator<Path>()
    		{
    			@Override
    			public int compare(Path p1, Path p2)
    			{
    				return p1.getDepartureTime().compareTo(p2.getDepartureTime());
    			}
    		});
    		
    		break;
    	}
    	}
    }
    public float calcTime(float distance, float speed)
    {
    	return distance/speed;
    }
    
    
}