package algorithms;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import dataStructure.DGraph;
import dataStructure.Node;
public class Graph_Algo implements graph_algorithms,Serializable
{
	private static final long serialVersionUID = 1L;
	public graph graph;
	public Graph_Algo() 
	{
		graph = new DGraph();
	}
	public Graph_Algo(graph other) 
	{
		this.graph = other;
	}
	@Override
	public void init(graph g)
	{
		this.graph = (DGraph)g;
	}
	@Override
	public void init(String file_name) 
	{
		try
		{    
			FileInputStream file = new FileInputStream(file_name); 
			ObjectInputStream input = new ObjectInputStream(file); 
			graph = (DGraph) input.readObject(); 
			input.close(); 
			file.close(); 
		} 
		catch(IOException ex) 
		{ 
			System.out.println("IOException"); 
		} 

		catch(ClassNotFoundException ex) 
		{ 
			System.out.println("ClassNotFoundException"); 
		}
	}
	@Override
	public void save(String file_name) 
	{
		try
		{    
			FileOutputStream file = new FileOutputStream(file_name); 
			ObjectOutputStream output = new ObjectOutputStream(file); 
			output.writeObject(graph);  
			output.close(); 
			file.close(); 
		}   
		catch(IOException ex) 
		{ 
			System.out.println("IOException"); 
		}
	}
	@Override
	public boolean isConnected() 
	{
		if(graph.nodeSize()<=1)	return true;
		Queue<Node> queue = new ArrayBlockingQueue<Node>(graph.nodeSize());
		reset_tags();
		for (node_data node : graph.getV() ) 
		{
			Node my_node = (Node) node;
			if (my_node.sides.values()== null) return false;
			queue .add(my_node);
			my_node.setTag(1);
			while (!queue .isEmpty()) 
			{
				for (edge_data edge : queue .peek().sides.values()) 
				{
					Node destination = (Node) graph.getNode(edge.getDest());
					if(destination.getTag()==0) 
					{
						destination.setTag(1);
						queue .add(destination);
					}
				}
				queue .remove();
			} 
			for (node_data nodes : graph.getV()) 
			{
				if (nodes.getTag()==0) return false;
				else nodes.setTag(0);
			}
		}
		return true;
	}
	@Override
	public double shortestPathDist(int source, int destination) 
	{
		if(source==destination)	return 0;
		String info = "";
		for (node_data nodes : graph.getV()) 
		{
			nodes.setWeight(Double.POSITIVE_INFINITY);
			nodes.setTag(0);
		}
		graph.getNode(source).setWeight(0);
		recursive_shortestPathDist(source, destination, info);
		return graph.getNode(destination).getWeight();
	}
	private void recursive_shortestPathDist(int src, int dest, String info) 
	{
		if(graph.getNode(src).getTag() == 1 && graph.getNode(src) == graph.getNode(dest)) return;
		for (edge_data edge : graph.getE(src)) 
		{
			double weight = edge.getWeight() + graph.getNode(edge.getSrc()).getWeight();
			double current_weight = graph.getNode(edge.getDest()).getWeight();
			if(weight < current_weight) {
				graph.getNode(edge.getDest()).setWeight(weight);
				graph.getNode(edge.getDest()).setInfo(info + "->" + src);
				graph.getNode(edge.getSrc()).setTag(1);
				recursive_shortestPathDist(edge.getDest(), dest, info + "->" + src);
			}	
		}
	}
	@Override
	public List<node_data> shortestPath(int src, int dest) 
	{
		if(shortestPathDist(src, dest) == Double.POSITIVE_INFINITY)	return null;
		List<node_data> answer = new ArrayList<>();
		String str = graph.getNode(dest).getInfo();
		str = str.substring(2);
		String[] arr =str.split("->");
		for (int i = 0; i < arr.length; i++) answer.add(graph.getNode(Integer.parseInt(arr[i])));
		answer.add(graph.getNode(dest));
		return answer;
	}
	@Override
	public List<node_data> TSP(List<Integer> targets) 
	{
		if(targets.isEmpty()) return null;
		List<node_data> targetsToNode = new ArrayList<>();
		for(Integer tar : targets) if(!(targetsToNode.contains(graph.getNode(tar)))) targetsToNode.add(graph.getNode(tar));
		if(targets.size()==1) return targetsToNode;
		Queue<Node> queue = new ArrayBlockingQueue<Node>(targets.size());
		for (node_data nodes : targetsToNode) nodes.setTag(0);
		for (node_data node : targetsToNode) 
		{
			Node n=(Node) node;
			if (n.sides.values()== null) return null;
			queue.add(n);
			n.setTag(1);
			while (!queue.isEmpty()) 
			{
				for (edge_data edge : queue.peek().sides.values()) 
				{
					Node destination = (Node) graph.getNode(edge.getDest());
					if(destination.getTag()==0) 
					{
						destination.setTag(1);
						queue.add(destination);
					}
				}
				queue.remove();
			} 
			for (node_data nodes : targetsToNode) 
			{
				if (nodes.getTag()==0) return null;
				else nodes.setTag(0);
			}
			reset_tags();
		}
		List<node_data> answer = new ArrayList<>();
		while(targetsToNode.size()>1) 
		{
			int source = targetsToNode.get(0).getKey();
			int destination = targetsToNode.get(1).getKey();
			List<node_data> list = shortestPath(source, destination);
			for(node_data node : list) 
			{
				if(targetsToNode.contains(node)&&targetsToNode.size()>1) 
				{
					if(targetsToNode.size()!=1)	targetsToNode.remove(node);					
				}
				answer.add(node);
			}
		}
		if(!answer.contains(targetsToNode.get(targetsToNode.size()-1)))	answer.add(targetsToNode.get(targetsToNode.size()-1));
		reset_tags();
		return answer;
	}
	@Override
	public graph copy() 
	{
		String file_to_copy = "copy.txt";
		save(file_to_copy);
		Graph_Algo newGraph =new Graph_Algo();
		newGraph.init(file_to_copy);
		return newGraph.graph;
	}
	private void reset_tags() 
	{
		for (node_data nodes : graph.getV()) nodes.setTag(0);
	}
}