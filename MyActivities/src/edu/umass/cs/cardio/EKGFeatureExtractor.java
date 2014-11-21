package edu.umass.cs.cardio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import edu.umass.cs.accelerometer.ActivityFeatureExtractor;
import edu.umass.cs.accelerometer.ReorientAxis;
/**
 * 
 * @author group2
 * Attempt to modify ActivityFeatureExtractor from step detection project
 * for EKG feature extraction
 *
 */
public class EKGFeatureExtractor {

	//LinkedLists to keep EKG data for a window
	private LinkedList<Double> cardioVector = new LinkedList<Double>();
	private LinkedList<Long> timeVector = new LinkedList<Long>();
	
	//TODO: This window value is from steps, we will most likely need to change this value
	private long WINDOW_IN_MILLISEC = 5000;
	private double lastCardioValue = 0;
	
	/**
	 * Constructor for the extractor
	 * @param WINDOW
	 */
	public EKGFeatureExtractor(long WINDOW) {
		WINDOW_IN_MILLISEC = WINDOW;
	}
	
	/* Extract features. These two extract features will mimic the ones from ActivityFeatureExtractor
	 * This approach may be simplified since we are dealing with less robust feature set, and only
	 * one stream of data. Will see...
	 */
	public Double[] extractFeatures(long timestamp, double cardioValue){
		addTime(timestamp);
		addValues(cardioValue);
		lastCardioValue = cardioValue;
		//Return null if features not extracted
		if((timestamp - timeVector.get(0))< WINDOW_IN_MILLISEC)
			return null;
		
		if(cardioVector.isEmpty()||cardioVector.size()<2)
			return null;
		
		return extractFeatures();
	}
	
	//extract features over a window
	private Double[] extractFeatures(){
		//we only have one feature we care about: RR-distance, so no need for the giant array
		Double[] features = new Double[1];
		for(int i=0; i<features.length; i++)
			features[i] = 0.0;
		
		//TODO: figure out how to calculate RR-distance
		Long[] times = timeVector.toArray(new Long[0]);
		times[times.length-1] = times[0] + WINDOW_IN_MILLISEC;
		
		//features of cardioVector
		Double[] values = cardioVector.toArray(new Double[0]);
		
		return null;
	}
	
	//compute RR-interval
	/*
	 * TODO: modify our detectSteps algorithm from Context_Service to detect R and then the time between each one
	 * Our detect steps algorithm only increments a counter when it passes the test, except we need to figure out the time
	 * of each peak...
	 */
	private double computeRRInterval(Double values[]){
		/**
		 * The following code is just detectSteps, for "inspiration"
		 */
		/*
		int steps = 0;
		if (currentFrame < windowSize) {
			//stores our x,y,z values in our array, adds our new value to our 
			//running aggregate, and increments our frame counter
			positionalData[currentFrame] = Math.sqrt(Math.pow(filt_acc_x, 2) +
					Math.pow(filt_acc_y, 2) + Math.pow(filt_acc_z, 2));
			currentFrame++;
		}
		else {
			//Once we’ve collected enough data to fill our window, we can now 
			//compute values. We will first compute our threshold by dividing 
			//our thresholdNumerator by windowSize. Then we can step through the 
			//list, checking to see if our data points cross the threshold 
			//(and also have a negative slope from point to point). There should 
			//also be a periodicity filter (steps can’t be too close together).
			
			//compute max and min
			double min = positionalData[0];
			double max = positionalData[0];
			for(int i = 1; i < positionalData.length; i++) {
				if(positionalData[i] <= min)
					min = positionalData[i];
				else if(positionalData[i] > max)
					max = positionalData[i];
			}
			
			//compute threshold and count steps!
			threshold = max - ((max-min)/2);
			double restrictMaxMin = max - min;
			if ((threshold > 6) && (threshold < 12) && (restrictMaxMin > 1)) {
				for(int i = 1; i< positionalData.length; i++){
						// don't count a step if we are inside the frame period
						if (periodFrameCounter < minFramePeriod) 
							periodFrameCounter++;
						// if data crosses threshold on negative slope
						else if (positionalData[i-1] > threshold && positionalData[i] <= threshold)	{
							steps++;
							periodFrameCounter = 0;			
						}
				}
			}
			//reset frame
			currentFrame = 0;
		}
		return steps;
		*/
		return 0.0;
	}
	
	/**
	 * Clear values for the next window.
	 * Will be basically the same, but with cardioVektor and timeVector
	 */
	private void clearValues(){
		cardioVector.clear();
		timeVector.clear();
	}
	
	/**
	 * Add a timestamp for the reading
	 * @param time
	 */
	private void addTime(long time){
		timeVector.add(time);
	}
	
	private void addValues(double cardio){
		cardioVector.add(cardio);
	}
	
	/*
	 * This complicated method is going to be the same method from ActivityFeatureExtractor
	 * but with modified file names. 
	 */
	private HashSet<String> mergeFiles(String inputDir) {
		/**
		 * Following code is directly from ActivityFeatureExtractor
		 */
		String cardioFile = inputDir+"/cardio.csv";
		String emaFile = inputDir+"/ema.csv";
		String mergeFile = inputDir+"/merge.csv";

		HashSet<String> activities = new HashSet<String>();
		LinkedList<Long> timeList =  new LinkedList<Long>();
		LinkedList<Boolean> isStartList = new LinkedList<Boolean>();
		LinkedList<String> labelList = new LinkedList<String>();

		try{
			BufferedReader br = new BufferedReader(new FileReader(emaFile));
			String s = null;

			//First read all the events
			while((s=br.readLine())!=null) {
				String tokens[] = s.split(",");
				long timeInMillis = Long.parseLong(tokens[0]);
				String label = tokens[4].trim().toLowerCase();
				if(!label.equals("start") && !label.equals("end")) {
					System.out.println("Did not find start or end, Skipping this entry");
					continue;
				}
				boolean isStart = label.equals("start");
				//Read next line to get the activity
				s = br.readLine();
				if(s==null) break;
				tokens = s.split(",");
				String activity = tokens[4].trim().toLowerCase();
				activities.add(activity);

				timeList.add(timeInMillis);
				labelList.add(activity);
				isStartList.add(isStart);
			}
			br.close();
			System.out.println("Finished Reading EMA File: "+emaFile);

		}catch(IOException e){
			e.printStackTrace();
			System.out.println("ERROR: Problem occurred while reading EMA file");
			System.exit(1);
		}

		//Now sanitize the event list
		boolean keep[] = new boolean[timeList.size()];
		for(int i=0;i<keep.length;i++)
			keep[i] = true;
		for(int i=0;i<keep.length;i++) {
			boolean currentLabel = isStartList.get(i);
			boolean nextLabel = (i<keep.length-1?isStartList.get(i+1):true);
			boolean prevLabel = (i>0?isStartList.get(i-1):false);
			if(currentLabel == true && nextLabel == true)
				keep[i] = false; // Encountered two consecutive starts; ignoring the first start
			if(currentLabel == false && prevLabel == false)
				keep[i] = false; // Encountered two consecutive ends; ignoring the second end
		}
		for(int i=keep.length-1;i>=0;i--) {
			if(!keep[i]){
				timeList.remove(i);
				isStartList.remove(i);
				labelList.remove(i);
			}
		}
		System.out.println("Sanitized the event list, number of valid periods found: "+timeList.size()/2);

		try{
			// Now read and write merged file
			BufferedReader br = new BufferedReader(new FileReader(cardioFile));
			BufferedWriter bw = new BufferedWriter(new FileWriter(mergeFile));
			System.out.println("Writing Merged File: "+mergeFile);
			int cIndex = 0;
			long beginTime = timeList.get(0);
			long endTime = timeList.get(timeList.size()-1);
			String s = null;
			while((s=br.readLine())!=null){
				String tokens[] = s.split(",");
				long time = Long.parseLong(tokens[0]);
				if(time<beginTime)
					continue;
				if(time>endTime)
					break;
				long cStart = timeList.get(cIndex);
				long cEnd = timeList.get(cIndex+1);
				
				while(time>=cEnd) {
					cIndex = cIndex+2;
					if(cIndex>=timeList.size()-1)
						break;
					cStart = timeList.get(cIndex);
					cEnd = timeList.get(cIndex+1);
				}
				if(time<cStart) continue;

				//Now, we have a valid data
				s = s.replace("null", labelList.get(cIndex)); //Update the label
				bw.write(s+"\n");
			}
			System.out.println("Finished Merge");
			br.close();
			bw.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return activities;
		
	}
	
	/*
	 * This method is similar to mergeFiles in that we are mostly keeping the code the same,
	 * except we have much less features we are working with. The primary block of code that 
	 * needs changing is inside the while loop: while((s=br.readline()) != null). 
	 */
	private void generateArffFile(String inputDir, HashSet<String> activities){
		/**
		 * Following code is directly from ActivityFeatureExtractor
		 */
		String arffFile = inputDir+"/activity-data.arff";
		String mergeFile = inputDir+"/merge.csv";
		//TODO: change the feature names content.
		String featureNames[] = {"xMean","xDev","xCrossRate","xFFT1","xFFT2","xFFT3","xFFT4","xVelocityChange",
				"xDistance","yMean","yDev","yCrossRate","yFFT1","yFFT2","yFFT3","yFFT4","yVelocityChange",
				"yDistance","zMean","zDev","zCrossRate","zFFT1","zFFT2","zFFT3","zFFT4","zVelocityChange",
				"zDistance","speedMean","speedDev","speedCrossRate","speedFFT1","speedFFT2","speedFFT3",
				"energyMean","energyDev","energyCrossRate","energyFFT1","energyFFT2","energyFFT3","energyFFT4",
				"energyXYMean","energyXYDev","energyXYCrossRate"};
		
		try{
			ReorientAxis roa = new ReorientAxis();
			BufferedReader br = new BufferedReader(new FileReader(mergeFile));
			BufferedWriter bw = new BufferedWriter(new FileWriter(arffFile));
			
			//First write the header information
			bw.write("@relation activity\n");
			for(int i=0;i<featureNames.length;i++)
				bw.write("@attribute "+featureNames[i]+" NUMERIC \n");
			String classes = "";
			Iterator<String>it = activities.iterator();
			int activityCount = 0;
			while(it.hasNext()) {
				String activity = it.next();
				classes+= ((activityCount>0?",":"")+activity);
				activityCount++;
			}
			bw.write("@attribute class{"+classes+"}\n");
			bw.write("@data\n");
			
			String s = null;
			String lastActivity = null;
			while((s=br.readLine())!=null) {
				String tokens[] = s.split(",");
				/*
				 * [1] = date (string)
				 * [2] = time: 13:05.3 format (string)
				 * [3] = EKG value (int or double should work)
				 * [4] = label (string)
				 */
				String date = tokens[0];
				long time = parseTime(tokens[1]);
				double EKG = Double.parseDouble(tokens[2]);
				String activityLabel = tokens[3];
				String activity = tokens[4].trim();
				//double ort[] = roa.getReorientedValues(acc_x, acc_y, acc_z);
				//Double features[] = extractFeatures(time, ort[0], ort[1], ort[2], acc_x, acc_y, acc_z);
				Double features[] = extractFeatures(time,EKG);
				//have not changed implementation from this point onwards in method
				if(features!=null) {
					String featureVector = "";
					for(int i=0;i<features.length;i++)
						featureVector+= ((i>0?",":"")+features[i]);
					if(lastActivity!=null) {
						featureVector+=(","+lastActivity);
						bw.write(featureVector+"\n");
					}
				}
				lastActivity = activity;
			}
			br.close();
			bw.close();
			System.out.println("Successfully generated Arff File:"+arffFile);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method that converts the time from CSV to milliseconds
	 * @param timeString
	 * @return
	 */
	private long parseTime(String timeString){
		//char[] time = timeString.toCharArray();
		long minutes = 0;
		long seconds = 0;
		long secFraction = 0;
		long millisec = 0;
		int colonIndex = timeString.indexOf(":");
		int periodIndex = timeString.indexOf(".");
		
		minutes = Long.parseLong(timeString.substring(0,colonIndex-1));
		seconds = Long.parseLong(timeString.substring(colonIndex+1,periodIndex-1));
		secFraction = Long.parseLong(timeString.substring(periodIndex+1));
		
		//overall time in milliseconds
		millisec = (minutes * 60000) + (seconds * 1000) + (secFraction * 100);
		return millisec;
	}
	//same as ActivityFeatureExtractor
	public void processFiles(String inputDir){
		HashSet<String> activities = mergeFiles(inputDir);
		generateArffFile(inputDir, activities);
	}
	
	//same as ActivityFeatureExtractor, except INPUT_DIR
	public static void main(String args[]){
		String INPUT_DIR = "/Users/fungw/Desktop/hi";
		//TODO: 5000 in the following line refers to WINDOW_IN_MILLISEC, but can't reference
		EKGFeatureExtractor efe = new EKGFeatureExtractor(5000);
		efe.processFiles(INPUT_DIR);
	}
	
}
