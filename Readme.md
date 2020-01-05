#Assignment 2, The Maze Of Waze.

Class: DGraph.

Methods: 

getNode(int key): This method will return a specified node by a given key, the method will return null if the node doesn't exist.

getEdge(int source, int destination): This method will return a specified edge by two given keys, one is the source and the other is the destination. the method will return null if one of the nodes doesn't exist.

addNode(node_data node): This method will add a new node to the hash map pool only if the node key is not already in the data structure, if he is, we will throw an exception.

connect(int source, int destination, double weight): This method will connect nodes on the graph(creates an Edge), the method will throw an exception in the cases of invalid source / destination inputs or in case the edge already exist.

getV() & getE() methods will return the collections of the nodes & edges values.

removeNode(int key): This method will remove the specified node by the given key and will remove the edges that are related to him. if the node doesn't exit by the given key the method will return null.

removeEdge(int source, int destination): This method will remove a specified edge by a given key.

nodeSize() & edgeSize(): This two methods will return the amount of nodes & edges in the graph.

getMC(): This method will return the amount of mode counter changes registered in the graph. 

Class: Graph_Algo.

Methods: 

init(graph g): This method will initiate a graph_algo object by the given graph g.

init(String file_name): This method will initiate a graph_algo object from a given text file.

save(String file_name): This method will save a graph_algo object as a text file.

isConnect(): This method performs a simple check, it checks if all the nodes are connected to each other.

shortestPathDist(int source, int destination): This method is based on the algorithm of Dijkastra. the method will calculate the shortest path from the given int source to the given int destination by considering the smallest weight.

shortestPath(int source, int destination): This method will return a list of node_data that represent the shortest path from the given int source node to the given int destination node.

TSP(List<Integer> targets): This method finds the shortest route that visits all the given target list nodes.

copy(): This method will create a copied algo_graph object for (this) graph_algo.

Class: Graph_GUI.

Methods: 

init(String name): This method will initiate and paint a graph by a given txt file.

paintGraph(): This method will draw a graph using the java class StdDraw.

paint Edges,Nodes,Canvas methods will draw the elements on the graph & the canvas using the methods & abilities StdDraw has given to us.