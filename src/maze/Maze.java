package maze;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;
import java.util.Collections;
import java.util.List;

public class Maze {
	private node adjMat[][]; // adjacency matrix
	
	public Maze(int i) // constructor
	{
		adjMat = new node[i][i];
	}
	
	public class node
	{
		int j;
		public node north;
		public node south;	
		public node east;		
		public node west;		
		public boolean wasVisited;	
		int xloc;
		int yloc;
		public node n[];
		
		public node(int x, int y, int z) // constructor
		{
			//System.out.println("here");		
			//false is the wall, true 
			north = south = east = west = null;
			wasVisited = false;
			n = null;	
			xloc = x;
			yloc = y;
			j = z;
		}	
	} 
		
	public static void main(String[] args) 
	{	
		Maze m = new Maze(5);	
		int d = -1;
		for(int i = 0; i < 5; i++)		
			for(int j = 0; j < 5; j++)		
				m.adjMat[i][j] = m.new node(i, j, ++d);	
		Stack<node> s = new Stack<node>();		
		int nodesvisited = 1;
		int x = 0;
		int y = 0;
		int nodetotal = 25;		
		node current = m.adjMat[x][y];
		ArrayList<node> neighborcelllist = new ArrayList<node>();
		List<Integer> list = new ArrayList<>();

		while(nodetotal > nodesvisited)
		{
			neighborcelllist.clear();
			
			//check x+1
			if(x + 1 < m.adjMat[0].length) //columns
			{
				if(m.adjMat[x + 1][y].north != null || m.adjMat[x + 1][y].south != null || 
						m.adjMat[x + 1][y].east != null || m.adjMat[x + 1][y].west != null)
					System.out.println("does not have wall somewhere");
				else
					neighborcelllist.add(m.adjMat[x + 1][y]);
			}
			
			//check y+1
			if(y + 1 < m.adjMat.length) // rows
			{
				if(m.adjMat[x][y + 1].north != null || m.adjMat[x][y + 1].south != null ||
						m.adjMat[x][y + 1].east != null || m.adjMat[x][y + 1].west != null)
					System.out.println("does not have wall somewhere");
				else
					neighborcelllist.add(m.adjMat[x][y + 1]);
			}
			
			//check x-1
			if(x - 1 >= 0)
			{
				if(m.adjMat[x + 1][y].north != null || m.adjMat[x + 1][y].south != null ||
						m.adjMat[x + 1][y].east != null || m.adjMat[x + 1][y].west != null)
					System.out.println("does not have wall somewhere");
				else
					neighborcelllist.add(m.adjMat[x - 1][y]);
			}
			
			//check y-1
			if(y - 1 >= 0)
			{
				if(m.adjMat[x][y - 1].north != null || m.adjMat[x][y - 1].south != null ||
						m.adjMat[x][y - 1].east != null || m.adjMat[x][y - 1].west != null)	
					System.out.println("does not have wall somewhere");
				else
					neighborcelllist.add(m.adjMat[x][y - 1]);
			}
			
			if(neighborcelllist.isEmpty())
				current = s.pop();	
			else
			{
			/*
				grab a random index of your list, connect the current node to the node in the list you grabbed
				also make sure to connect both ways, e.g. for [0][0] you need to make right point to [0][1], 
				but you also need [0][1] left to point to [0][0] index node. 
		     	Next add the nodes to each other's adjacency list, so they reflect what you did with the edges.
				Next push the current node on the stack, and make the node you grabbed from the list the current node, 
				then repeat the loop
			 */
				
				
				//node random = neighborcelllist.get(new Random().nextInt(neighborcelllist.size()));
				Random rand = new Random();
				int z = rand.nextInt(neighborcelllist.size());
				System.out.println("currentnode " + current.j);
				
				for(int i = 0; i < neighborcelllist.size(); i++)
					System.out.println("neighbor list index " + i + " value " + neighborcelllist.get(i).j);
				
				System.out.println("chosen index " + z);
				node chosenneighbor = neighborcelllist.get(z);
				int xresult = chosenneighbor.xloc - x;
				int yresult = chosenneighbor.yloc - y;
				
				if(xresult == 1)
				{
					chosenneighbor.north = current;
					current.south = chosenneighbor;
					s.push(current);
					current = chosenneighbor;
					nodesvisited++;
				}
				else if(xresult == -1)
				{
					chosenneighbor.south = current;
					current.north = chosenneighbor;
					s.push(current);
					current = chosenneighbor;
					nodesvisited++;
				}
				else if(yresult == 1)
				{
					chosenneighbor.west = current;
					current.east = chosenneighbor;
					s.push(current);
					current = chosenneighbor;
					nodesvisited++;
				}
				else if(yresult == -1)
				{
					chosenneighbor.east = current;
					current.west = chosenneighbor;
					s.push(current);
					current = chosenneighbor;
					nodesvisited++;
				}
				list.add(chosenneighbor.j);
				//s.push(current);
				//current = chosenneighbor;
				//nodesvisited++;

			}
			
			Collections.sort(list);
			System.out.println(list);
			
			for(int i = 0; i < 5; i++)
			{
				for (int j = 0; j < 5; j++)
				{
					System.out.print("+");
					
					if(m.adjMat[i][j].north == null)
						if (j == 0 && i == 0)
							System.out.print(" ");
						else
							System.out.print("-");
					else
						System.out.print(" ");
				}

				System.out.print("+\n");
				System.out.print("| ");

				for (int j = 0; j < 5; j++)
				{
					if(m.adjMat[i][j].east == null)
						System.out.print("| ");
					else
						System.out.print("  ");
				}
				System.out.print("\n");
			}

			for (int j = 0; j < 5; j++)
			{
				System.out.print("+");

				if(m.adjMat[4][j].south == null && j != 4)
					System.out.print("-");
				else
					System.out.print(" ");
			}
			System.out.print("+");
		}
	}	
	
	public void DFS(Maze m)
	{
		//stack data structure
		Stack<node> s = new Stack<node>();
		node current = adjMat[0][0];
		s.push(current);
		current.wasVisited = true;
		
		while(!s.isEmpty())
		{
			node temp = s.peek();
			
			if(temp.equals(m.adjMat[m.adjMat.length][m.adjMat[0].length])) //Goal
				System.out.println("Solved");
			else if((temp.north == null || temp.north.wasVisited == true) && 
					(temp.west == null || temp.west.wasVisited == true) &&
					(temp.south == null || temp.south.wasVisited == true) &&
					(temp.east == null || temp.east.wasVisited == true))
				s.pop();
			else
			{
				if(temp.north != null && temp.north.wasVisited == false)
				{
					temp.north.wasVisited = true;
					temp = temp.north;
					s.push(temp);
				}
				else if(temp.west != null && temp.west.wasVisited == false)
				{
					temp.west.wasVisited = true;
					s.push(temp.west);
				}
				else if(temp.south != null && temp.south.wasVisited == false)
				{
					temp.south.wasVisited = true;
					s.push(temp.south);
				}
				else if(temp.east != null && temp.east.wasVisited == false)
				{
					temp.east.wasVisited = true;
					s.push(temp.east);
				}	
			}
		}
	}
	
	public void BFS(Maze m)
	{
		// Queue data structure
		Queue<node> queue = new LinkedList<node>();
		node current = adjMat[0][0];
		queue.add(current);
		current.wasVisited = true;
		
		while(!queue.isEmpty())
		{
			node temp = queue.remove();
			
			if(temp.equals(m.adjMat[m.adjMat.length][m.adjMat[0].length])) //Goal
				System.out.println("Solved");
			else
			{
				if(temp.north != null && temp.north.wasVisited == false)
				{
					temp.north.wasVisited = true;
					queue.add(temp.north);
				}
				if(temp.west != null && temp.west.wasVisited == false)
				{
					temp.west.wasVisited = true;
					queue.add(temp.west);
				}
				if(temp.south != null && temp.south.wasVisited == false)
				{
					temp.south.wasVisited = true;
					queue.add(temp.south);
				}
				if(temp.east != null && temp.east.wasVisited == false)
				{
					temp.east.wasVisited = true;
					queue.add(temp.east);
				}	
			}
		}
	}
}
		
