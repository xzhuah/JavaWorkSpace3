package com.credit;

import java.util.Collections;
import java.util.Vector;

public class DataAnalysis {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double test[]={203.1, 203.1, 203.0, 202.95, 202.95, 202.9, 202.65, 202.6, 202.5, 202.4, 4.2728, 4.187344, 4.11, 4.10359712, 1.4888888888888};
		Vector<Double> list=new Vector<Double>();
		Vector<Double> Time=new Vector<Double>();
		for(int i=0;i<test.length;i++){
			list.add(test[i]);
			Time.add(test.length-i*1.0);
		}
		System.out.println(predict(list,Time,new Long(1)));
	}
	public static Vector<BuyDetail> cleanData(Vector<BuyDetail> data,Vector<Double> list){
		Vector<BuyDetail> clean=new Vector<BuyDetail>();
		double median=midian(list);
		double outlen=outlength(list);
		 for(int i=0;i<list.size();i++){
	        	double a=list.get(i);
	        	if(a>=median-outlen&&a<=median+outlen){
	        		clean.add(data.get(i));
	        	}
	      }
		 return clean;
	}
	
	public static Vector<SellDetail> cleanData2(Vector<SellDetail> data,Vector<Double> list){
		Vector<SellDetail> clean=new Vector<SellDetail>();
		double median=midian(list);
		double outlen=outlength(list);
		 for(int i=0;i<list.size();i++){
	        	double a=list.get(i);
	        	if(a>=median-outlen&&a<=median+outlen){
	        		clean.add(data.get(i));
	        	}
	      }
		 return clean;
	}
	public static double predict(Vector<Double> list,Vector<Double> Time,Long future){
		Vector<Double> clean=new Vector<Double>();
		Vector<Double> cleanTime=new Vector<Double>();
       
        double median=midian(list);
        double outlen=outlength(list);
        
        for(int i=0;i<list.size();i++){
        	double a=list.get(i);
        	if(a>=median-outlen&&a<=median+outlen){
        		clean.add(a);
        		cleanTime.add(Time.get(i));
        	}
        }
        
       
        double avgN=avg(clean);
        Vector<Double> clean2=new Vector<Double>();
        for(int i=0;i<clean.size();i++){
        	if(Math.abs(clean.get(i)-avgN)<Math.abs(median-avgN)+20){
        		clean2.add(clean.get(i));
        	}
        }
        clean=clean2;
        //System.out.println(clean);
        if(clean.size()<2){
        	return clean.get(0);
        }
        double TimeAvg=avg(cleanTime);
        double Avg=avg(clean);
       // System.out.println(TimeAvg+" "+Avg);
        double up=0;
        double down=0;
        for(int i=0;i<clean.size();i++){
        	up+=(clean.get(i)-Avg)*(cleanTime.get(i)-TimeAvg);
        	down+=(cleanTime.get(i)-TimeAvg)*(cleanTime.get(i)-TimeAvg);
        }
        double b=up/down;
        double a=Avg-TimeAvg*b;
        
        return a+b*(System.currentTimeMillis()+future);
        
        //return a+b*(list.size()+future);
       
	}
	public static double avg(Vector<Double> list){
		double sum=0;
		for(double a:list){
			sum+=a;
		}
		return sum/list.size();
	}
	public static double max(Vector<Double> list){
		double result=list.get(0);
		for(int i=1;i<list.size();i++){
			if(list.get(i)>result){
				result=list.get(i);
			}
		}
		return result;
	}
	public static double midian(Vector<Double> list){
		Vector<Double> newlist=(Vector<Double>) list.clone();
		Collections.sort(newlist);
		return newlist.get(newlist.size()/2);
	}
	public static double outlength(Vector<Double> list){
		Vector<Double> newlist=(Vector<Double>) list.clone();
		Collections.sort(newlist);
		double min=newlist.get(newlist.size()*1/4);
		double max=newlist.get(newlist.size()*3/4);
		return 1.5*(max-min);
		
	}
	
	public static double min(Vector<Double> list){
		double result=list.get(0);
		for(int i=1;i<list.size();i++){
			if(list.get(i)<result){
				result=list.get(i);
			}
		}
		return result;
	}

}
