package dataStructure;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import elements.Edge;
import elements.Node;

public class DGraph implements Serializable,graph 
{
	private static final long serialVersionUID = 1L;
	private int edge_size = 0;
	private int mode_counter = 0;
	public HashMap<Integer, node_data> nodes_map = new HashMap<>();
	@Override
	public node_data getNode(int key) 
	{
		if(nodes_map.get(key)!=null) return nodes_map.get(key);
		else return null;
	}
	@Override
	public edge_data getEdge(int src, int dest) 
	{
		if((nodes_map.get(src)!=null)&&(nodes_map.get(dest)!=null))	return ((Node) getNode(src)).sides.get(dest);
		else return null;
	}
	@Override
	public void addNode(node_data n) 
	{
		if(nodes_map.containsKey(n.getKey())) throw new RuntimeException("Duplicate nodes, key already registered");
		else 
		{
			nodes_map.put(n.getKey(), (Node) n);
			mode_counter++;
		}				
	}
	@Override
	public void connect(int src, int dest, double w) 
	{
		if((nodes_map.get(src)==null) || (nodes_map.get(dest)==null)) throw new RuntimeException("Error in source/destination input");
		if(((Node)nodes_map.get(src)).sides.containsKey(dest)) throw new RuntimeException("Edge already exist, Error");
		Edge e = new Edge(src, dest, 0, w , null);
		edge_size++;
		((Node)nodes_map.get(src)).sides.put(dest, e);
		mode_counter++;
	}
	@Override
	public Collection<node_data> getV() 
	{
		return nodes_map.values();
	}
	@Override
	public Collection<edge_data> getE(int node_id) 
	{
		return ((Node) nodes_map.get(node_id)).sides.values();
	}
	@Override
	public node_data removeNode(int key) 
	{
		if(nodes_map.containsKey(key)) 
		{
			edge_size-=((Node)nodes_map.get(key)).sides.values().size();
			((Node)nodes_map.get(key)).sides.clear();
			Iterator<Integer> it = nodes_map.keySet().iterator();
			while(it.hasNext())	removeEdge(it.next(), key);
			mode_counter++;
			return nodes_map.remove(key);
		}
		else return null;
	}
	@Override
	public edge_data removeEdge(int src, int dest) 
	{
		if(((Node)nodes_map.get(src)).sides.containsKey(dest)) 
		{
			edge_size--;
			mode_counter++;
			return ((Node)nodes_map.get(src)).sides.remove(dest);
		}
		else return null;
	}
	@Override
	public int nodeSize() 
	{
		return nodes_map.size();
	}
	@Override
	public int edgeSize() 
	{
		return edge_size;
	}
	@Override
	public int getMC() 
	{
		return mode_counter;
	}
	public Iterator<node_data> node_iterator() 
	{
		return nodes_map.values().iterator();
	}
}