package Tests;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import algorithms.Graph_Algo;
import dataStructure.Node;
import dataStructure.edge_data;
import dataStructure.node_data;
import utils.Point3D;

class Graph_AlgoTest 
{
	@Test
	void testInit() 
	{
		Graph_Algo algo_graph = new Graph_Algo();
		algo_graph.graph.addNode(new Node(1, new Point3D(25,0), 0, "", 0));
		algo_graph.graph.addNode(new Node(2, new Point3D(30,0), 0, "", 0));
		algo_graph.graph.addNode(new Node(3, new Point3D(0,10), 0, "", 0));
		algo_graph.graph.addNode(new Node(4, new Point3D(50,100), 0, "", 0));
		algo_graph.save("init_test.txt");
		Graph_Algo init = new Graph_Algo();
		init.init("init_test.txt");
	}
	@Test
	void testSave() 
	{
		Graph_Algo algo_graph = new Graph_Algo();
		algo_graph.graph.addNode(new Node(1, new Point3D(25,0), 0, "", 0));
		algo_graph.graph.addNode(new Node(2, new Point3D(30,0), 0, "", 0));
		algo_graph.graph.addNode(new Node(3, new Point3D(0,10), 0, "", 0));
		algo_graph.graph.addNode(new Node(4, new Point3D(50,100), 0, "", 0));
		algo_graph.save("save_test.txt");
	}
	@Test
	void testIsConnected() 
	{
		Graph_Algo algo_graph = new Graph_Algo();
		algo_graph.graph.addNode(new Node(1, new Point3D(25,0), 0, "", 0));
		algo_graph.graph.addNode(new Node(2, new Point3D(30,0), 0, "", 0));
		algo_graph.graph.addNode(new Node(3, new Point3D(0,10), 0, "", 0));
		algo_graph.graph.addNode(new Node(4, new Point3D(50,100), 0, "", 0));
		algo_graph.graph.connect(1, 2, 1);
		algo_graph.graph.connect(2, 3, 1);
		algo_graph.graph.connect(3, 4, 1);
		algo_graph.graph.connect(4, 1, 1);
		assertTrue(algo_graph.isConnected());
	}
	@Test
	void testShortestPathDist() 
	{
		Graph_Algo algo_graph = new Graph_Algo();
		algo_graph.graph.addNode(new Node(1, new Point3D(25,0), 0, "", 0));
		algo_graph.graph.addNode(new Node(2, new Point3D(30,0), 0, "", 0));
		algo_graph.graph.addNode(new Node(3, new Point3D(0,10), 0, "", 0));
		algo_graph.graph.addNode(new Node(4, new Point3D(50,100), 0, "", 0));
		algo_graph.graph.connect(1, 2, 1);
		algo_graph.graph.connect(2, 3, 2);
		algo_graph.graph.connect(3, 4, 3);
		assertTrue(algo_graph.shortestPathDist(1, 3) == 3);
	}

	@Test
	void testShortestPath() 
	{
		Graph_Algo algo_graph = new Graph_Algo();
		algo_graph.graph.addNode(new Node(1, new Point3D(25,0), 0, "", 0));
		algo_graph.graph.addNode(new Node(2, new Point3D(30,0), 0, "", 0));
		algo_graph.graph.addNode(new Node(3, new Point3D(0,10), 0, "", 0));
		algo_graph.graph.addNode(new Node(4, new Point3D(50,100), 0, "", 0));
		algo_graph.graph.connect(1, 2, 1);
		algo_graph.graph.connect(2, 3, 2);
		algo_graph.graph.connect(3, 4, 3);
		List<node_data> expected = new ArrayList<>();
		expected.add(new Node(1, new Point3D(25,0), 0, "", 0));
		expected.add(new Node(2, new Point3D(30,0), 0, "", 0));
		expected.add(new Node(3, new Point3D(0,10), 0, "", 0));
		expected.add(new Node(4, new Point3D(50,100), 0, "", 0));
		List<node_data> result = algo_graph.shortestPath(1, 4);
		boolean flag = true;
		for(int i=0; i<expected.size();i++) 
		{
			if(expected.get(i).getKey()!=result.get(i).getKey()) 
				flag = false;	
		}
		assertTrue(flag);
	}

	@Test
	void testTSP() 
	{
		boolean equal = true;
		Graph_Algo algo_graph = new Graph_Algo();
		algo_graph.graph.addNode(new Node(1, new Point3D(25,0), 0, "", 0));
		algo_graph.graph.addNode(new Node(2, new Point3D(30,0), 0, "", 0));
		algo_graph.graph.addNode(new Node(3, new Point3D(0,10), 0, "", 0));
		algo_graph.graph.addNode(new Node(4, new Point3D(50,100), 0, "", 0));
		algo_graph.graph.connect(1, 2, 1);
		algo_graph.graph.connect(2, 3, 2);
		algo_graph.graph.connect(3, 4, 3);
		algo_graph.graph.connect(4, 1, 4);
		ArrayList<Integer> array_list = new ArrayList<>();
		array_list.add(1);
		array_list.add(2);
		array_list.add(3);
		array_list.add(4);
		for (int i = 0; i < array_list.size(); i++) 
		{
			if(algo_graph.TSP(array_list).get(i).getKey()!=array_list.get(i)){
				equal = false;
			}
		}
		assertTrue(equal);
	}
	@Test
	void testCopy() 
	{
		Graph_Algo algo_graph = new Graph_Algo();
		algo_graph.graph.addNode(new Node(1, new Point3D(25,0), 0, "", 0));
		algo_graph.graph.addNode(new Node(2, new Point3D(30,0), 0, "", 0));
		algo_graph.graph.addNode(new Node(3, new Point3D(0,10), 0, "", 0));
		algo_graph.graph.addNode(new Node(4, new Point3D(50,100), 0, "", 0));
		algo_graph.graph.connect(1, 2, 3);
		boolean equal = false;
		Graph_Algo algo_graph_copy = new Graph_Algo();
		algo_graph_copy.graph =  algo_graph.copy();
		for(edge_data edge : algo_graph_copy.graph.getE(1)) 
		{
			if(edge.getDest()==2 && edge.getSrc()==1 && edge.getWeight()==3)
				equal = true;
		}
		assertTrue(equal);
	}
}