package gui;
import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;
import java.util.Collection;
import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import utils.StdDraw;
public class Graph_GUI implements Serializable 
{
	private static final long serialVersionUID = 1L;
	private Graph_Algo _graphAlgo;
	private graph _graph;
	public Graph_GUI() 
	{
		_graphAlgo=new Graph_Algo();
		_graph=new DGraph();
		StdDraw.setGui(this);
	}
	public Graph_GUI(graph g) 
	{
		this._graph = g;	
		_graphAlgo=new Graph_Algo();
		_graphAlgo.init(this._graph);
		StdDraw.setGui(this);
	}
	public void init(graph gr) 
	{
		this._graph = gr;
		this._graphAlgo._graph = gr;
	}
	public void init(String name) 
	{
		this._graphAlgo.init(name);
		this._graph=_graphAlgo._graph;
		paintGraph();
	}
	public void paintGraph() 
	{
		paintCanvas();
		paintEdges();
		paintNodes();
	}
	public void paintCanvas() 
	{
		double minX=0, maxX=0, minY=0, maxY=0;
		Collection<node_data> points = _graph.getV();
		if(points.isEmpty()) 
		{
			StdDraw.setCanvasSize(600,600);
			StdDraw.setXscale(-150,150);
			StdDraw.setYscale(-150,150);
		}
		else 
		{
		minX = points.iterator().next().getLocation().x();
		maxX = points.iterator().next().getLocation().x();
		minY =points.iterator().next().getLocation().y();
		maxY =points.iterator().next().getLocation().y();
		for (node_data nodes : points) {
			if(nodes.getLocation().x()>maxX)
				maxX = nodes.getLocation().x();

			if(nodes.getLocation().x()<minX)
				minX = nodes.getLocation().x();

			if(nodes.getLocation().y()>maxY)
				maxY = nodes.getLocation().y();

			if(nodes.getLocation().y()<minY)
				minY = nodes.getLocation().y();
		}
			StdDraw.setCanvasSize((int)(Math.abs(minX)+Math.abs(maxX))+900,(int)(Math.abs(minY)+Math.abs(maxY))+900);
			StdDraw.setXscale(minX-10, maxX+10);
			StdDraw.setYscale(minY-10, maxY+10);
		}
	}
	public void paintNodes() 
	{
		StdDraw.setPenColor(Color.RED);
		StdDraw.setPenRadius(0.03);
		Collection<node_data> points = _graph.getV();
		for (node_data nodes : points) 
		{
			StdDraw.point(nodes.getLocation().x(), nodes.getLocation().y());
			StdDraw.setFont(new Font("Ariel", Font.ROMAN_BASELINE, 20));
			StdDraw.text(nodes.getLocation().x(), nodes.getLocation().y()+5, ""+ nodes.getKey());
		}
	}
	public void paintEdges() 
	{
		StdDraw.setPenRadius(0.008);
		Collection<node_data> points = _graph.getV();
		for(node_data nodes: points) {
			Collection<edge_data> e = _graph.getE(nodes.getKey());
			for (edge_data edge : e) {
				double x0= nodes.getLocation().x();
				double y0= nodes.getLocation().y();
				double x1= _graph.getNode(edge.getDest()).getLocation().x();
				double y1= _graph.getNode(edge.getDest()).getLocation().y();
				StdDraw.setPenRadius(0.005);

				StdDraw.setPenColor(Color.ORANGE);
				StdDraw.line(x0, y0, x1, y1);

				StdDraw.setFont(new Font("Ariel", Font.BOLD, 20));

				StdDraw.setPenColor(Color.GREEN);
				StdDraw.setPenRadius(0.05);
				StdDraw.point((x0+x1*3)/4, (y0+y1*3)/4);

				StdDraw.setPenColor(Color.black);
				StdDraw.text((x0+x1*3)/4, (y0+y1*3)/4, ""+ edge.getWeight());
			}
		}
	}
	public Graph_Algo getGraphAlgo() 
	{
		return _graphAlgo;
	}
	public graph getGraph() 
	{
		return _graph;
	}
}