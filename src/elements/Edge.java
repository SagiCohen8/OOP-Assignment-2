package elements;

import java.io.Serializable;

import dataStructure.edge_data;

public class Edge implements edge_data, Serializable
{
	private static final long serialVersionUID = 1L;
	private int source, destination, tag;
	private double weigth;
	private String information;
	
	public Edge() 
	{
		source = 0;
		destination = 0;
		tag = 0;
		weigth = 0.0;
		information = "";
	}
	public Edge(int source, int destination, int tag, double weigth, String information)
	{
		this.source = source;
		this.destination = destination;
		this.tag = tag;
		this.weigth = weigth;
		this.information = information;
	}

	public Edge(Edge other)
	{
		this.source = other.source;
		this.destination = other.destination;
		this.tag = other.tag;
		this.weigth = other.weigth;
		this.information = other.information;
	}
	@Override
	public int getSrc() 
	{
		return source;
	}
	@Override
	public int getDest() 
	{
		return destination;
	}
	@Override
	public double getWeight() 
	{
		return weigth;
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
}
