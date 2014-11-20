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
		//TODO: figure out how to calculate RR-distance
		return null;
	}
	
	/**
	 * Clear values for the next window.
	 * Will be basically the same, but with cardioVektor and timeVector
	 */
	private void clearValues(){
		
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
		return null;
	}
	
	/*
	 * This method is similar to mergeFiles in that we are mostly keeping the code the same,
	 * except we have much less features we are working with. The primary block of code that 
	 * needs changing is inside the while loop: while((s=br.readline()) != null). 
	 */
	private void generateArffFile(String inputDir, HashSet<String> activities){
		
	}
	
	//same as ActivityFeatureExtractor
	public void processFiles(String inputDir){
		
	}
	
	//same as ActivityFeatureExtractor, except INPUT_DIR
	public static void main(String args[]){
		
	}
	
}
