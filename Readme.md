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