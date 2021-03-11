import org.jfree.data.xy.XYSeries;

public class Randomwalk {
//Initialize object from Printcharacters class to call function
Printcharacters printCharactersobject = new Printcharacters();

 	public void conductRandomWalk(int maxCharacters, String fastADNA, final XYSeries series, int x, int y, int aIterations, int cIterations, int gIterations, int tIterations, int xValues[], int yValues[])
 	{
 	//Assigning a value to each letter found in the file and then adding those values to a series
 	//to graph the walk
 		for (int i = 0; i < maxCharacters; i++) 
 		{
 			char value = fastADNA.charAt(i);
 			if (value == 'A' | value == 'a')
 			{
 				y = y + 1;    
 				series.add(x,y);
 				aIterations++;
 			}
 			else if (value == 'C' | value == 'c')
 			{
 				x = x - 1;    		
 				series.add(x,y);
 				cIterations++;
 			}
 			else if (value == 'G' | value == 'g')
 			{
 				x = x + 1;       			
 				series.add(x,y);
 				gIterations++;
 			}
 			else if (value == 'T' | value == 't')
 			{
 				y = y - 1;			
 				series.add(x,y);
 				tIterations++;
 			}
 			xValues[i] = x;
 			yValues[i] = y;
 			//System.out.println(xValues[i]);
 			//System.out.println(yValues[i]);
 		}  
 		//Display count of how many times A,G,C and T appear in the file
 		printCharactersobject.printNumberOfTimesCharacterAppears(aIterations, cIterations, gIterations, tIterations);
 	}
}
