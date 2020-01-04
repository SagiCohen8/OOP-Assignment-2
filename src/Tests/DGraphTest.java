package Tests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import dataStructure.DGraph;
import dataStructure.Node;
import utils.Point3D;

class DGraphTest 
{
	@Test
	void testAddNode() 
	{
		DGraph graph = new DGraph();
		graph.addNode(new Node(1, new Point3D(25,0), 0, "", 0));
		graph.addNode(new Node(2, new Point3D(0,50), 0, "", 0));
		graph.addNode(new Node(5, new Point3D(8,52), 0, "", 0));
		assertTrue(graph.nodeSize()==3);	
	}
	@Test
	void testConnect() 
	{
		DGraph graph = new DGraph();
		graph.addNode(new Node(1, new Point3D(25,0), 0, "", 0));
		graph.addNode(new Node(2, new Point3D(0,50), 0, "", 0));
		graph.connect(1, 2, 1);
		assertTrue(graph.edgeSize()==1);	
	}
	@Test
	void testRemoveNode()
	{
		DGraph graph = new DGraph();
		graph.addNode(new Node(1, new Point3D(25,0), 0, "", 0));
		graph.addNode(new Node(2, new Point3D(0,50), 0, "", 0));
		assertTrue(graph.nodeSize()==2);
		graph.removeNode(1);
		assertTrue(graph.nodeSize()==1);	
	}

	@Test
	void testRemoveEdge() 
	{
		DGraph graph = new DGraph();
		graph.addNode(new Node(1, new Point3D(25,0), 0, "", 0));
		graph.addNode(new Node(2, new Point3D(0,50), 0, "", 0));
		graph.connect(1, 2, 1);
		assertTrue(graph.edgeSize()==1);
		graph.removeEdge(1, 2);
		assertTrue(graph.edgeSize()==0);	
	}
	@Test
	void testNodeSize() 
	{
		DGraph temp = new DGraph();
		temp.addNode(new Node(1, new Point3D(2,5), 0, "", 0));
		temp.addNode(new Node(2, new Point3D(1,3), 0, "", 0));
		temp.addNode(new Node(3, new Point3D(8,4), 0, "", 0));
		int size = temp.nodeSize();
		assertTrue(size == 3);
	}
}