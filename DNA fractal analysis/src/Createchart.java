import java.awt.BasicStroke;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Createchart 
{
	public void createChartFunction(XYSeriesCollection data, final XYSeries series, JFreeChart chart, String title, String x_value, String y_value,String file_name) throws IOException 
	{
		//Initialize size of graph
		int width = 1920;
		int height = 1080;
		data = new XYSeriesCollection(series);
		chart = ChartFactory.createXYLineChart(title, x_value, y_value, data, PlotOrientation.VERTICAL, true, true, false);
		File lineChart = new File(file_name);
		
		//Change the chart background color to white
		chart.getPlot().setBackgroundPaint(Color.WHITE);
		
		//Add origin lines to the graph
		XYPlot plot = (XYPlot) chart.getPlot();  
		ValueMarker marker = new ValueMarker(0);  
		marker.setPaint(Color.GRAY);
		plot.addDomainMarker(marker);
		plot.addRangeMarker(marker);
    
		//Increase line thickness
		int seriesCountValue = plot.getSeriesCount();
		for (int i = 0; i < seriesCountValue; i++) 
		{
			plot.getRenderer().setSeriesStroke(i, new BasicStroke(2));
		}
		
		//Change the color of the line to black
		plot.getRenderer().setSeriesPaint(0, Color.BLACK);
		
		//Export the chart as a .jpeg file
		ChartUtilities.saveChartAsJPEG(lineChart, chart, width, height); 
		System.out.println();
	}
}
