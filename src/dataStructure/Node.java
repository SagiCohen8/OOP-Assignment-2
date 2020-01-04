package dataStructure;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import utils.Point3D;

public class Node implements node_data, Serializable
{
	private static final long serialVersionUID = 1L;
	public HashMap<Integer, edge_data> sides = new HashMap<>();
	private int key, tag;
	private Point3D location;
	private Double weight;
	private String information;
	public Node() 
	{
		key = 0;
		tag = 0;
		weight =0.0;
		location = null;
		information = "";
	}
	public Node(int key,Point3D location, double Weight, String info, int tag) 
	{
		this.information = info;
		this.weight = Weight;
		this.location = location;
		this.tag = tag;
		this.key = key;
	}
	public Node(Node other) 
	{
		this.key = other.key;
		this.weight = other.weight;
		this.location = other.location;
		this.information = other.information;
		this.tag = other.tag;
	}
	@Override
	public int getKey() 
	{
		return key;
	}
	@Override
	public Point3D getLocation() 
	{
		return location;
	}
	@Override
	public void setLocation(Point3D p) 
	{
		location = p;
	}
	@Override
	public double getWeight() 
	{
		return weight;
	}
	@Override
	public void setWeight(double w) 
	{
		weight = w;
	}
	@Override
	public String getInfo() 
	{
		return information;
	}
	@Override
	public void setInfo(String s) 
	{
		information = s;
	}
	@Override
	public int getTag() 
	{
		return tag;
	}
	@Override
	public void setTag(int t) 
	{
		tag = t;
	}
	public Iterator<edge_data> edge_iterator() 
	{
		return sides.values().iterator();
	}
	public String toString() 
	{
		return this.key+"";
	}
}