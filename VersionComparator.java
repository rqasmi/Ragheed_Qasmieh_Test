import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;


/**
 * 
 * @author Ragheed
 * Last updated: 2019-10-19
 *
 */

public class VersionComparator {
	
	/**
	 * This method takes two version strings as input and returns a string indicating if the first one is greater than, smaller than or equal
	 * to the second one
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static String versionCompare(String v1, String v2) {
		
		String[] v1_arr = v1.split("\\.",0); //use the string split method that returns an array of substrings split at regex or literal
		String[] v2_arr = v2.split("\\.",0);
		
		List<String> v1_list = new ArrayList<String>(Arrays.asList(v1_arr)); //convert the array to an Arraylist to be able to append
		List<String> v2_list = new ArrayList<String>(Arrays.asList(v2_arr));
		int v1_len = v1_list.size(); //get the size of the first version list
		int v2_len = v2_list.size();
		//if the first version list length is greater than that of the second, append zeros to the second list until they have the same length
		if(v1_len > v2_len) {
			for(int i = 0; i < v1_len - v2_len; i++) {
				v2_list.add("0");
			}
		// else if the first version list length is less than that of the second, append zeros to the second list until they have the same length
		}else if(v1_len < v2_len) {
			for(int i = 0; i < v2_len - v1_len; i++) {
				v1_list.add("0");
			}
		}
		
		//iterate over the strings of the two version lists and compare the integer values of the string
		for(int i = 0; i < v1_list.size(); i++) {
			if(Integer.parseInt(v1_list.get(i)) > Integer.parseInt(v2_list.get(i))){
				return "\""+ v1 +"\" " + "is greater than " + "\"" + v2 + "\"";
			}else if(Integer.parseInt(v1_list.get(i)) < Integer.parseInt(v2_list.get(i))){
				return "\""+ v1 +"\" " + "is less than " + "\"" + v2 + "\"";
			}
		}
		return "\""+ v1 +"\" " + "is equal to " + "\"" + v2 + "\"";
	}
	
	/**
	 * This is the main method that contains a collection of test cases
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println(versionCompare("1.0","1.1"));
		System.out.println(versionCompare("1.5","1.5.1"));
		System.out.println(versionCompare("1.4","1.3.9"));
		System.out.println(versionCompare("2.2.0","2.2"));
		System.out.println(versionCompare("2.2.0","2.2.0.0"));
		System.out.println(versionCompare("7.8.0.4","7.8.0"));
		System.out.println(versionCompare("3.5.10","3.5.1"));
		System.out.println(versionCompare("3.6.9","3.6.0.9"));
		System.out.println(versionCompare("1.","1.0"));
		System.out.println(versionCompare("1.0","1.0.0.0.0.0"));
		System.out.println(versionCompare("1.1","1.1.0.0.0.1"));
		//System.out.println(versionCompare("1.x","1.x"));
		System.out.println(versionCompare("1.2.","1.0."));

	}

}
