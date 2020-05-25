/**
 * 
 * @author Ragheed Qasmieh
 * Last Updated: 2019-10-19
 *
 */

class line{
	int x1;
	int x2;
	
	line(int x1, int x2){
		this.x1 = x1;
		this.x2 = x2;
	}
}

public class DoesOverlap {
	
	/**
	 * 
	 * This method takes two lines as input and returns a boolean value representing if the two lines overlap or not
	 * 
	 * @param l1
	 * @param l2
	 * @return doesOverlap
	 */
	private static boolean doesOverlap(line l1, line l2) {
		//this boolean value checks if l1 is before l2 (i.e. the finish of l2 is greater than the start of l1)
		boolean l1BeforeL2 = Math.max(l2.x1, l2.x2) > Math.min(l1.x1, l1.x2);
		//the lines overlap if the finish point of l1 is greater than the start point of l2 and l1 is before l2
		boolean doesOverlap = (Math.max(l1.x1,l1.x2) > Math.min(l2.x1, l2.x2) && l1BeforeL2);
		return doesOverlap;
	}
	
	
	/**
	 * This is the main method that contains a collection of test cases
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println(doesOverlap(new line(1,5),new line(2,6)));
		System.out.println(doesOverlap(new line(1,5),new line(6,8)));
		System.out.println(doesOverlap(new line(5,1),new line(8,5)));
		System.out.println(doesOverlap(new line(-1,3),new line(4,3)));
		System.out.println(doesOverlap(new line(7,2),new line(1,3)));
		System.out.println(doesOverlap(new line(6,3),new line(0,2)));
	}
	
	
}
