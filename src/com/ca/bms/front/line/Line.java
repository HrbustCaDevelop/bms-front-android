package com.ca.bms.front.line;

import java.sql.Date;

import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.graphics.Color;

public class Line {
	XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset(); 
	XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer(); 
	XYSeriesRenderer r1 = new XYSeriesRenderer();//(类似于一条线对象) 
	XYSeriesRenderer r2 = new XYSeriesRenderer();
	XYSeriesRenderer r3 = new XYSeriesRenderer();
	XYSeries  series1;
	XYSeries  series2;
	XYSeries  series3;
	
	public Line() {
		// TODO 自动生成的构造函数存根
		series1 = new  XYSeries("CO");
		series2 = new  XYSeries("Light");
		series3 = new  XYSeries("Temper");
		mDataset.addSeries(series1);
	   	mDataset.addSeries(series2);
	   	mDataset.addSeries(series3);
	}
	
	public void add(double x1,double x2,double x3,double y){
		
		mDataset.removeSeries(series1);
	   	mDataset.removeSeries(series2);
	   	mDataset.removeSeries(series3);
    	series1.add(y,x1);
    	series2.add(y,x2);
    	series3.add(y,x3);
    	mDataset.addSeries(series1);
	   	mDataset.addSeries(series2);
	   	mDataset.addSeries(series3); 
    }
	
	public XYMultipleSeriesDataset getmDataset(){
		return mDataset;
	}
	
	public XYMultipleSeriesRenderer getmRenderer(){
		return mRenderer;
	}
	
//	public void l_final(){
//		mDataset.addSeries(series1);
//	   	mDataset.addSeries(series2);
//	   	mDataset.addSeries(series3);
//	}
    public void set(String Xtitle ,String Ytitle,String charttitle){
        //XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();  
        //设置图表的X轴的当前方向  
        mRenderer.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);  
        mRenderer.setXTitle(Xtitle);//设置为X轴的标题  
        mRenderer.setYTitle(Ytitle);//设置y轴的标题  
        mRenderer.setAxisTitleTextSize(20);//设置轴标题文本大小  
        mRenderer.setChartTitle(charttitle);//设置图表标题  
        mRenderer.setChartTitleTextSize(30);//设置图表标题文字的大小  
        mRenderer.setLabelsTextSize(18);//设置标签的文字大小  
        mRenderer.setLegendTextSize(20);//设置图例文本大小  
        mRenderer.setPointSize(10f);//设置点的大小  
        mRenderer.setYAxisMin(10);//设置y轴最小值是0  
        mRenderer.setYAxisMax(15);  
        mRenderer.setYLabels(4);//设置Y轴刻度个数（貌似不太准确） 
        mRenderer.addXTextLabel(0, "0s");
        mRenderer.addXTextLabel(2, "2s");
        mRenderer.addYTextLabel(0, "0s");
        mRenderer.addYTextLabel(2, "2s");
        mRenderer.setXAxisMin(0);
        mRenderer.setXAxisMax(15);  
        mRenderer.setXLabels(7);
        mRenderer.setShowGrid(true);//显示网格  
        //将x标签栏目显示如：1,2,3,4替换为显示1月，2月，3月，4月  
        mRenderer.setMargins(new int[] { 200, 30, 15, 20 });//设置视图位置  
        
       // XYSeriesRenderer r = new XYSeriesRenderer();//(类似于一条线对象)  
        r1.setColor(Color.BLUE);//设置颜色  
        r1.setPointStyle(PointStyle.CIRCLE);//设置点的样式  
        r1.setFillPoints(true);//填充点（显示的点是空心还是实心）  
        r1.setDisplayChartValues(true);//将点的值显示出来  
        r1.setChartValuesSpacing(10);//显示的点的值与图的距离  
        r1.setChartValuesTextSize(25);//点的值的文字大小  
          
      //  r.setFillBelowLine(true);//是否填充折线图的下方  
      //  r.setFillBelowLineColor(Color.GREEN);//填充的颜色，如果不设置就默认与线的颜色一致  
        r1.setLineWidth(3);//设置线宽  
        mRenderer.addSeriesRenderer(r1);     
        
        r2.setColor(Color.RED);//设置颜色  
        r2.setPointStyle(PointStyle.CIRCLE);//设置点的样式  
        r2.setFillPoints(true);//填充点（显示的点是空心还是实心）  
        r2.setDisplayChartValues(true);//将点的值显示出来  
        r2.setChartValuesSpacing(10);//显示的点的值与图的距离  
        r2.setChartValuesTextSize(25);//点的值的文字大小  
          
      //  r.setFillBelowLine(true);//是否填充折线图的下方  
      //  r.setFillBelowLineColor(Color.GREEN);//填充的颜色，如果不设置就默认与线的颜色一致  
        r2.setLineWidth(3);//设置线宽  
        mRenderer.addSeriesRenderer(r2);     
        
        r3.setColor(Color.YELLOW);//设置颜色  
        r3.setPointStyle(PointStyle.CIRCLE);//设置点的样式  
        r3.setFillPoints(true);//填充点（显示的点是空心还是实心）  
        r3.setDisplayChartValues(true);//将点的值显示出来  
        r3.setChartValuesSpacing(10);//显示的点的值与图的距离  
        r3.setChartValuesTextSize(25);//点的值的文字大小  
          
      //  r.setFillBelowLine(true);//是否填充折线图的下方  
      //  r.setFillBelowLineColor(Color.GREEN);//填充的颜色，如果不设置就默认与线的颜色一致  
        r3.setLineWidth(3);//设置线宽  
        mRenderer.addSeriesRenderer(r3);     
        
    }
}
