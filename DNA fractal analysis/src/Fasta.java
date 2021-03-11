import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Fasta 
{
	 public static void main(String[] args) throws IOException 
	 { 
		 //Creating objects for other classes to receive functions from them
		 Boxcountingmethod boxcountingmethodobject = new Boxcountingmethod();
		 Createchart createChartobject = new Createchart();
		 Randomwalk randomWalkobject = new Randomwalk();
		 
		 //Process of scanning for the file with the DNA sequence and reading the file
	        boolean first = true;
	        int maxCharacters = 0;
	        String fastADNA= "";
	 
	        try (Scanner sc = new Scanner(new File("test.fasta"))) 
	        {
	            while (sc.hasNextLine()) 
	            {
	                String line = sc.nextLine().trim();
	                if (line.charAt(0) == '>')
	                {
	                    if (first)
	                    {
	                        first = false;
	                    }
	                    else
	                    {
	                        //System.out.println();
	                    	//System.out.printf("%s: ", line.substring(1));
	                    }
	                } else 
	                {
	                	fastADNA = fastADNA + line;
	                    //System.out.print(line);      
	                }  
	            }
	        } catch (Exception ex)
	        {
	            ex.printStackTrace();
	        }
	            
	            maxCharacters = fastADNA.length();
            	System.out.println("Total number of characters in DNA sequence: " + maxCharacters);
            	
            	//Create series for the pseudo random walk
	            final XYSeries series = new XYSeries("DNA pseudo-random walk",false);
	            
	            //Initialize x any y values, as well as A,C,G, and T to count the number of times they appear in the sequence
	            int x = 0;
	         	int y = 0;
	         	int aIterations = 0;
	         	int cIterations = 0;
	         	int gIterations = 0;
	         	int tIterations = 0;
	         	
	         	//Create arrays to hold the x and y values at each point
	         	int xValues[]= new int[maxCharacters];
	         	int yValues[]= new int[maxCharacters];
	         	
	         	//Conduct the random walk using the function from the Randomwalk class
	            randomWalkobject.conductRandomWalk(maxCharacters, fastADNA, series, x, y, aIterations, cIterations, gIterations, tIterations, xValues, yValues);
            	
            	//Arrays to store the x and y values to sort and then find the max and min of
            	int sortedxValues [] = new int[maxCharacters];
            	int sortedyValues [] = new int[maxCharacters];
            	
            	//Sorting x and y values in tables to find the max and min of graph
            	for (int i = 0; i < maxCharacters; i++)
            	{
            		sortedxValues[i] = xValues[i];
            		//System.out.println(sortedxValues[i]);
            		sortedyValues[i] = yValues[i];
            		//System.out.println(sortedyValues[i]);
            	}
            	
            	//Finding the bounds (min and max) of the pseudo random walk for x and y
            	System.out.println();
            	System.out.println("---Max and min values of the graph---");
            	Arrays.sort(sortedxValues);
            	int xMin = sortedxValues[0];
            	int xMax = sortedxValues[sortedxValues.length-1];
            	System.out.println("Minimum x value of graph = " + xMin);
            	System.out.println("Maximum x value of graph = " + xMax);
            	
            	Arrays.sort(sortedyValues);
            	int yMin = sortedyValues[0];
            	int yMax = sortedyValues[sortedyValues.length-1];
            	System.out.println("Minimum y value of graph = " + yMin);
            	System.out.println("Maximum y value of graph = " + yMax);
            	
            	double domain = xMax - xMin;
            	double range = yMax - yMin;
            	//System.out.println("Domain = " + domain);
            	//System.out.println("Range = " + range);
            	
            	//Start of conducting box counting method for different box sizes
            	int totalNumberOfBoxes2x2 = boxcountingmethodobject.boxCountingMethod(domain,range,2,2,xMin,xMax,yMin,yMax,maxCharacters,xValues,yValues);
            	System.out.println("Total number of boxes the graph passes through = " + totalNumberOfBoxes2x2);
            	
            	int totalNumberOfBoxes4x4 = boxcountingmethodobject.boxCountingMethod(domain,range,4,4,xMin,xMax,yMin,yMax,maxCharacters,xValues,yValues);
            	System.out.println("Total number of boxes the graph passes through = " + totalNumberOfBoxes4x4);
            	
            	int totalNumberOfBoxes8x8 = boxcountingmethodobject.boxCountingMethod(domain,range,8,8,xMin,xMax,yMin,yMax,maxCharacters,xValues,yValues);
            	System.out.println("Total number of boxes the graph passes through = " + totalNumberOfBoxes8x8);
            	
            	//Fractal Dimension analysis done on 8x8 & 16x16 in this case, the rest of the values are used to graph logs
            	int totalNumberOfBoxes16x16 = boxcountingmethodobject.boxCountingMethod(domain,range,16,16,xMin,xMax,yMin,yMax,maxCharacters,xValues,yValues);
            	System.out.println("Total number of boxes the graph passes through = " + totalNumberOfBoxes16x16);
            	
            	int totalNumberOfBoxes32x32 = boxcountingmethodobject.boxCountingMethod(domain,range,32,32,xMin,xMax,yMin,yMax,maxCharacters,xValues,yValues);
            	System.out.println("Total number of boxes the graph passes through = " + totalNumberOfBoxes32x32);
            	
            	int totalNumberOfBoxes64x64 = boxcountingmethodobject.boxCountingMethod(domain,range,64,64,xMin,xMax,yMin,yMax,maxCharacters,xValues,yValues);
            	System.out.println("Total number of boxes the graph passes through = " + totalNumberOfBoxes64x64);
            	
            	//Adding values to the box analysis comparison graph
            	final XYSeries series2 = new XYSeries("Box Analysis",false);
            	series2.add(Math.log10(2),Math.log10(totalNumberOfBoxes2x2));
            	series2.add(Math.log10(4),Math.log10(totalNumberOfBoxes4x4));
            	series2.add(Math.log10(8),Math.log10(totalNumberOfBoxes8x8));
            	series2.add(Math.log10(16),Math.log10(totalNumberOfBoxes16x16));
            	series2.add(Math.log10(32),Math.log10(totalNumberOfBoxes32x32));
            	series2.add(Math.log10(64),Math.log10(totalNumberOfBoxes64x64));
            	 	     	
                //Fractal dimension analysis comparing 8x8 box counting method to 16x16 box counting method
                double fractalDimension = boxcountingmethodobject.fractaldimension(totalNumberOfBoxes16x16,totalNumberOfBoxes8x8);
            	System.out.println();
            	System.out.println("Fractal dimension of graph for 8x8 and 16x16 methods = " + fractalDimension);
            	
            	//Create the series for the pseudo-random walk and the logarithmic analysis
            	final XYSeriesCollection data = new XYSeriesCollection(series);
            	final XYSeriesCollection data2 = new XYSeriesCollection(series2);
            	
            	//Create charts for the graphs
            	final JFreeChart chart = null;
            	final JFreeChart chart2 = null;
            	
            	//Call to function in createChart.java class file to create and export the graphs
            	createChartobject.createChartFunction(data, series, chart, "DNA pseudo-random walk", "Changes in C and G",  "Changes in A and T", "DNA_random_walk.jpeg");       	
            	createChartobject.createChartFunction(data2, series2, chart2, "Analysis comparing box sizes to number of boxes containing points", "log(size of box)", "log(number of occupied boxes)", "boxsize_comparison_graph.jpeg");
	 }
}
