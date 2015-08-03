package rstirli2_lab08;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;



public class rstirli2_lab08_Task {
	
	//members
	private int id;
	private int duration;
	private int earlyStart=0;
	private int lateStart=0;
	private boolean critical;
	
	//connections
	private Queue<rstirli2_lab08_Task> prevNodes; 
	private Queue<rstirli2_lab08_Task> nextNodes; 	
	
	//setters
	public void setCrit(boolean c){critical = c;}
	public void setID(int i){id=i;}
	public void setDuration(int d){duration=d;}
	public void setEarly(int e)
	{
		if(earlyStart<=e)
		{
			earlyStart = e;
		}
	}
	
	public void setLateMax(int l)
	{
		if (lateStart<l)
		{
			lateStart=l;
		}
	}
	
	public void setLate(int l)
	{
		if (lateStart>l)
		{
			lateStart=l;
		}
		
	}
	
	//getters
	public boolean getCrit(){return critical;}
	public int getID(){return id;}
	public int getDuration(){return duration;}
	public int getEarly(){return earlyStart;}
	public int getLate(){return lateStart;}
	public Queue<rstirli2_lab08_Task> getNext() {return nextNodes;}
	public Queue<rstirli2_lab08_Task> getPrev() {return prevNodes;}

	//constructor
	public rstirli2_lab08_Task(int i, int d){
		setID(i);
		setDuration(d);
		prevNodes = new LinkedList<rstirli2_lab08_Task>();
		nextNodes = new LinkedList<rstirli2_lab08_Task>();
	}
	
	public void addToNext(rstirli2_lab08_Task node)
	{
		nextNodes.add(node);
	}
	
	public void addToPrev(rstirli2_lab08_Task node)
	{
		prevNodes.add(node);
	}
	
	
}
