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
	XYSeriesRenderer r1 = new XYSeriesRenderer();//(������һ���߶���) 
	XYSeriesRenderer r2 = new XYSeriesRenderer();
	XYSeriesRenderer r3 = new XYSeriesRenderer();
	XYSeries  series1;
	XYSeries  series2;
	XYSeries  series3;
	
	public Line() {
		// TODO �Զ����ɵĹ��캯�����
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
        //����ͼ���X��ĵ�ǰ����  
        mRenderer.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);  
        mRenderer.setXTitle(Xtitle);//����ΪX��ı���  
        mRenderer.setYTitle(Ytitle);//����y��ı���  
        mRenderer.setAxisTitleTextSize(20);//����������ı���С  
        mRenderer.setChartTitle(charttitle);//����ͼ�����  
        mRenderer.setChartTitleTextSize(30);//����ͼ��������ֵĴ�С  
        mRenderer.setLabelsTextSize(18);//���ñ�ǩ�����ִ�С  
        mRenderer.setLegendTextSize(20);//����ͼ���ı���С  
        mRenderer.setPointSize(10f);//���õ�Ĵ�С  
        mRenderer.setYAxisMin(10);//����y����Сֵ��0  
        mRenderer.setYAxisMax(15);  
        mRenderer.setYLabels(4);//����Y��̶ȸ�����ò�Ʋ�̫׼ȷ�� 
        mRenderer.addXTextLabel(0, "0s");
        mRenderer.addXTextLabel(2, "2s");
        mRenderer.addYTextLabel(0, "0s");
        mRenderer.addYTextLabel(2, "2s");
        mRenderer.setXAxisMin(0);
        mRenderer.setXAxisMax(15);  
        mRenderer.setXLabels(7);
        mRenderer.setShowGrid(true);//��ʾ����  
        //��x��ǩ��Ŀ��ʾ�磺1,2,3,4�滻Ϊ��ʾ1�£�2�£�3�£�4��  
        mRenderer.setMargins(new int[] { 200, 30, 15, 20 });//������ͼλ��  
        
       // XYSeriesRenderer r = new XYSeriesRenderer();//(������һ���߶���)  
        r1.setColor(Color.BLUE);//������ɫ  
        r1.setPointStyle(PointStyle.CIRCLE);//���õ����ʽ  
        r1.setFillPoints(true);//���㣨��ʾ�ĵ��ǿ��Ļ���ʵ�ģ�  
        r1.setDisplayChartValues(true);//�����ֵ��ʾ����  
        r1.setChartValuesSpacing(10);//��ʾ�ĵ��ֵ��ͼ�ľ���  
        r1.setChartValuesTextSize(25);//���ֵ�����ִ�С  
          
      //  r.setFillBelowLine(true);//�Ƿ��������ͼ���·�  
      //  r.setFillBelowLineColor(Color.GREEN);//������ɫ����������þ�Ĭ�����ߵ���ɫһ��  
        r1.setLineWidth(3);//�����߿�  
        mRenderer.addSeriesRenderer(r1);     
        
        r2.setColor(Color.RED);//������ɫ  
        r2.setPointStyle(PointStyle.CIRCLE);//���õ����ʽ  
        r2.setFillPoints(true);//���㣨��ʾ�ĵ��ǿ��Ļ���ʵ�ģ�  
        r2.setDisplayChartValues(true);//�����ֵ��ʾ����  
        r2.setChartValuesSpacing(10);//��ʾ�ĵ��ֵ��ͼ�ľ���  
        r2.setChartValuesTextSize(25);//���ֵ�����ִ�С  
          
      //  r.setFillBelowLine(true);//�Ƿ��������ͼ���·�  
      //  r.setFillBelowLineColor(Color.GREEN);//������ɫ����������þ�Ĭ�����ߵ���ɫһ��  
        r2.setLineWidth(3);//�����߿�  
        mRenderer.addSeriesRenderer(r2);     
        
        r3.setColor(Color.YELLOW);//������ɫ  
        r3.setPointStyle(PointStyle.CIRCLE);//���õ����ʽ  
        r3.setFillPoints(true);//���㣨��ʾ�ĵ��ǿ��Ļ���ʵ�ģ�  
        r3.setDisplayChartValues(true);//�����ֵ��ʾ����  
        r3.setChartValuesSpacing(10);//��ʾ�ĵ��ֵ��ͼ�ľ���  
        r3.setChartValuesTextSize(25);//���ֵ�����ִ�С  
          
      //  r.setFillBelowLine(true);//�Ƿ��������ͼ���·�  
      //  r.setFillBelowLineColor(Color.GREEN);//������ɫ����������þ�Ĭ�����ߵ���ɫһ��  
        r3.setLineWidth(3);//�����߿�  
        mRenderer.addSeriesRenderer(r3);     
        
    }
}
