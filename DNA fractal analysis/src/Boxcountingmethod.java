import java.util.ArrayList;
import java.util.List;

public class Boxcountingmethod 
{
	public int boxCountingMethod(double domain,double range, int xNumberOfBoxes1, int yNumberOfBoxes1,int xMin,int xMax, int yMin, int yMax, int maxCharacters, int xValues[], int yValues[])
	{
    	double xBoxes1 [] = new double [xNumberOfBoxes1+1];
    	double yBoxes1 [] = new double [yNumberOfBoxes1+1];
    	
    	double xSizeBetweenBoxes1;
    	double ySizeBetweenBoxes1;
    	
    	if (domain == 0)
    	{
    			xSizeBetweenBoxes1 = 0;
    	}
    	else
    	{
    		xSizeBetweenBoxes1 = domain/xNumberOfBoxes1;
    	}
    	
    	if (range == 0)
    	{
    			ySizeBetweenBoxes1 = 0;
    	}
    	else 
    	{
    		ySizeBetweenBoxes1 = range/yNumberOfBoxes1;
    	}
    	
    	//System.out.println("Size of x boxes = " + xSizeBetweenBoxes1);
    	//System.out.println("Size of y boxes = " + ySizeBetweenBoxes1);
    	
    	System.out.println();
    	System.out.println("---Box counting method for " + xNumberOfBoxes1 + " by " + yNumberOfBoxes1 + " sized boxes---");
    	
    	//Establish boxes boundaries for analysis
    	xBoxes1[0] = xMin;
    	//System.out.println(xBoxes1[0]);
    	for (int i = 1; i < xNumberOfBoxes1+1; i++)
    	{
    		xBoxes1[i] = xBoxes1[i-1] + xSizeBetweenBoxes1;
    		// System.out.println(xBoxes1[i]);
    	}
    	
    	yBoxes1[0] = yMin;
    	//System.out.println(yBoxes1[0]);
    	for (int i = 1; i < yNumberOfBoxes1+1; i++)
    	{
    		yBoxes1[i] = yBoxes1[i-1] + ySizeBetweenBoxes1;
    		//System.out.println(yBoxes1[i]);
    	}
    	
    	System.out.println("Size of divisions for x axis = " + xSizeBetweenBoxes1);
    	System.out.println("Size of divisions for y axis = " + ySizeBetweenBoxes1);
    	
    	List<Integer> numberOfPointsInBox = new ArrayList<Integer>();
    	
    	for(int i = 1; i < yNumberOfBoxes1+1; i++)
    	{
    		for (int j = 1; j < xNumberOfBoxes1+1; j++)
    		{
    			int numberOfPoints = 0;
    			for (int k = 0; k < maxCharacters; k++)
    			{
    				if (xValues[k] < xBoxes1[j] && yValues[k] < yBoxes1[i] && xValues[k] >= xBoxes1[j-1] && yValues[k] >= yBoxes1[i-1])
    				{
    					numberOfPoints++;
    				}
    				if(j == xNumberOfBoxes1 && xValues[k] == xBoxes1[xNumberOfBoxes1])
    				{
    					numberOfPoints++;
    				}
    				if(i == yNumberOfBoxes1 && yValues[k] == yBoxes1[yNumberOfBoxes1])
    				{
    					numberOfPoints++;
    				}
    				if (k == maxCharacters-1)
    				{
    					//System.out.println("Number of points for box y = " + i + " x = " + j + " is: " + numberOfPoints);
    					numberOfPointsInBox.add(numberOfPoints);
    				}
    			}
    		}
    	}
    	
    	//System.out.println(numberOfPointsInBox);
    	
    	Integer [] numberOfPointsInBoxArray = numberOfPointsInBox.toArray(new Integer[numberOfPointsInBox.size()]);
    	
    	int totalNumberOfBoxesThatContainPoints = 0;
    	
    	for (int i = 0; i < numberOfPointsInBox.size(); i++)
    	{
    		if(numberOfPointsInBoxArray[i]>0)
    		{
    			totalNumberOfBoxesThatContainPoints++;
    		}
    		//System.out.println(numberOfPointsInBoxArray[i]);
    	}
    	
    	//System.out.println(totalNumberOfBoxesThatContainPoints);
		return totalNumberOfBoxesThatContainPoints;
	 }
	 
	 public double fractaldimension(int totalnumberofboxes1,int totalnumberofboxes2)
	 {
		 double fractalDimension = (Math.log10(1.0*totalnumberofboxes1/totalnumberofboxes2)) /(Math.log10(2));
		 return fractalDimension;
	 }
}
