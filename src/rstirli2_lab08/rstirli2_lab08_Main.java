package rstirli2_lab08;

import java.util.Scanner;

public class rstirli2_lab08_Main {

	public static void main(String[] args) {
		rstirli2_lab08_TaskNetwork network = new rstirli2_lab08_TaskNetwork();
		Scanner sc = new Scanner(System.in);
		int numTasks;
		int duration;
		int parent, child, link=0;
		boolean a=true;
		
		System.out.print("Please input the number of tasks: ");
		numTasks=sc.nextInt();		
		
		for (int i=0; i<numTasks; i++)
		{
			System.out.print("Please input the duration of task "+(i+1)+": ");
			duration = sc.nextInt();
			network.addNextTask(new rstirli2_lab08_Task((i+1), duration) );
		}
		
		System.out.println("When finished inputing links enter 0, 0");

		while (a)
		{
			link++;

			System.out.println("Please input link "+link+": ");
			parent = sc.nextInt();
			child = sc.nextInt();
			if ((parent>0&&parent<=numTasks)&&(child>0&&child<=numTasks))
			{
				network.addConnection(network.getTask(parent), network.getTask(child));
				System.out.print(" Link created.");	
			}
			
			else if (parent==0&&child==0)
			{
				System.out.println("All links created");
				a=false;
			}
			
			else
			{
				System.out.println("One of the tasks does not exist.");
				System.out.println("Task id's must range from 1-"+numTasks+".");
			}

		}
		
		network.setEarlies();
		network.setLates();
		network.setCrits();
		network.printAll();
		

	}

}
