package com.practice.main.util;

import java.util.HashSet;
import java.util.Set;

/**
 * IDTracker is a static class meant to help with keeping track of specific objects that are created and destroyed quickly.
 *
 * It uses
 *
 */
public class IDTracker {
	
	private static Set<Integer> ids = new HashSet<>();
	
	/**
	 * Gets a new unique random ID number and adds it to the List.
	 *
	 * @param numDigits the length of the desired ID
	 *
	 * @return a new random ID number
	 */
	public static int claimRandomID(int numDigits) {
		boolean numAvailable = false;
		int num = 0;
		while(!numAvailable) {
			num = Util.randomInt(numDigits);
			if(!ids.contains(num)) {
				numAvailable = true;
			}
		}
		ids.add(num);
		return num;
	}
	
	/**
	 * Gets the next available unique ID number and adds it to the List.
	 *
	 * @return a new unique ID number
	 */
	public static int claimNextID() {
		boolean numAvailable = false;
		int num = -1;
		while(!numAvailable) {
			num++;
			if(!ids.contains(num)) {
				numAvailable = true;
			}
		}
		ids.add(num);
		return num;
	}
	
	/**
	 * Gets the next available unique ID number and adds it to the List.
	 *
	 * @return a new unique ID number
	 */
	public static boolean returnID(int ID) {
		return ids.remove(ID);
	}
	
	/**
	 * Checks if an ID is Used.
	 *
	 * @return True if an object is already using that ID
	 */
	public static boolean checkIDUsed(int ID) {
		return ids.contains(ID);
	}
	
}
