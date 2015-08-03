package rstirli2_lab08;

import java.util.AbstractList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

public class rstirli2_lab08_TaskNetwork {
	
	rstirli2_lab08_Task head;
	Queue<rstirli2_lab08_Task> nodes; 
	int size;
	int max;
	
	public rstirli2_lab08_TaskNetwork ()
	{
		nodes = new LinkedList<rstirli2_lab08_Task>();
		size=0;
	}
	
	public void addNextTask(rstirli2_lab08_Task n)
	{
		nodes.add(n);
		size++;
	}
	
	public rstirli2_lab08_Task getTask(int id)
	{
		rstirli2_lab08_Task test;
		ListIterator<rstirli2_lab08_Task> iterator = ((AbstractList<rstirli2_lab08_Task>) nodes).listIterator();
		while(iterator.hasNext())
		{
			test = iterator.next();
			if (test.getID()==id)
			{
				return test;
			}
		}
		
		return null;
	}
	
	public void addConnection(rstirli2_lab08_Task prev, rstirli2_lab08_Task next)
	{
		prev.addToNext(next);
		next.addToPrev(prev);
	}
	
	public void setEarlies()
	{
		Queue<rstirli2_lab08_Task> list= new LinkedList<rstirli2_lab08_Task>(); 
		
		rstirli2_lab08_Task node1, node2;
		ListIterator<rstirli2_lab08_Task> inner;
		ListIterator<rstirli2_lab08_Task> outer = ((AbstractList<rstirli2_lab08_Task>) nodes).listIterator();
		while(outer.hasNext())
		{
			node1 = outer.next();
			if (!list.contains(node1))
			{
				list.add(node1);
			}
			inner = ((AbstractList<rstirli2_lab08_Task>) node1.getNext()).listIterator();
			while(inner.hasNext())
			{
				node2=inner.next();
				list.add(node2);
			}
		}
		outer = ((AbstractList<rstirli2_lab08_Task>) list).listIterator();
		while (outer.hasNext())
		{
			node1=outer.next();
			inner = ((AbstractList<rstirli2_lab08_Task>) node1.getPrev()).listIterator();
			while(inner.hasNext())
			{
				node2=inner.next();
				node1.setEarly(node2.getDuration()+node2.getEarly());
				if (node1.getEarly()>max)
				{
					max=node1.getEarly();
				}
			}
		}
		setAllLates(max);
		
	}
	
	public void setAllLates(int max)
	{
		ListIterator<rstirli2_lab08_Task> iterator = ((AbstractList<rstirli2_lab08_Task>) nodes).listIterator();
		while (iterator.hasNext())
		{
			iterator.next().setLateMax(max);
		}
	}
	
	public void setLates()
	{
		Queue<rstirli2_lab08_Task> list= new LinkedList<rstirli2_lab08_Task>(); 
		
		rstirli2_lab08_Task node1, node2;
		ListIterator<rstirli2_lab08_Task> inner;
		ListIterator<rstirli2_lab08_Task> outer = ((AbstractList<rstirli2_lab08_Task>) nodes).listIterator();
		while(outer.hasNext())
		{
			outer.next();
		}
		while (outer.hasPrevious())
		{
			node1 = outer.previous();
			list.add(node1);
			inner = ((AbstractList<rstirli2_lab08_Task>) node1.getPrev()).listIterator();
			while(inner.hasNext())
			{
				node2=inner.next();
				list.add(node2);
			}

		}
		outer = ((AbstractList<rstirli2_lab08_Task>) list).listIterator();
		int late=0;
		while(outer.hasNext())
		{
			node1=outer.next();
			inner = ((AbstractList<rstirli2_lab08_Task>) node1.getNext()).listIterator();
			while(inner.hasNext())
			{
				node2=inner.next();
				late = node2.getLate()-node1.getDuration();
				node1.setLate(late);
			}

		}
	}
	
	public void setCrits()
	{
		rstirli2_lab08_Task info;
		ListIterator<rstirli2_lab08_Task> iterator = ((AbstractList<rstirli2_lab08_Task>) nodes).listIterator();
		while(iterator.hasNext())
		{
			info = iterator.next();
			if (info.getEarly()==info.getLate())
			{
				info.setCrit(true);
			}
			else
			{
				info.setCrit(false);
			}
			
		}
	}
	
	public void printAll()
	{
		rstirli2_lab08_Task info;
		ListIterator<rstirli2_lab08_Task> iterator = ((AbstractList<rstirli2_lab08_Task>) nodes).listIterator();
		while(iterator.hasNext())
		{
			info = iterator.next();
			System.out.println();
			System.out.println("Task ID: "+ info.getID()+".");
			System.out.println("Task duration: "+info.getDuration()+".");
			System.out.println("Earliest start time: "+info.getEarly()+".");
			System.out.println("Latest start time: "+info.getLate()+".");
			if (info.getCrit())
			{
				System.out.println("Task "+info.getID()+" is a critical task.");
			}
			else
			{
				System.out.println("Task "+info.getID()+" is not a critical task.");
			}
			System.out.println();
		}
	}
}
