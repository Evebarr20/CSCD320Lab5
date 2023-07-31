# Dijkstra's Shortest Path Algorithm

## Introduction
This Java program implements Dijkstra's algorithm to find the shortest paths from a given source vertex to all other vertices in a weighted directed graph. The graph is represented using an adjacency list with LinkedList data structures. The program takes a text file representing the graph as input and the source vertex for which the shortest paths need to be computed.

Dijkstra's algorithm efficiently computes the shortest paths in a graph with non-negative edge weights. It iteratively relaxes the edges and updates the distance and predecessor of each vertex as needed until all vertices are processed.

## How to Use

1. Make sure you have Java installed on your machine.

2. Clone this repository to your local machine
3. Create a text file representing the graph. Each line of the file should start with a vertex number, followed by a colon (:) and a semi-colon (;)-separated list of adjacent vertices and their corresponding edge weights. For example:

   0: 1,10; 2,5
   
   1: 2,2
   
   2: 3,4
   
   3:
4. Run the program with the input text file and the source vertex as command-line arguments

## Graph Representation
The graph is represented using an adjacency list with LinkedList data structures. The LinkedList class represents a linked list of adjacent vertices and edge weights for each vertex. The Vertex class represents each vertex in the graph, storing its distance from the source and predecessor. The Edge class represents the edges between vertices with their weights.

## About Dijkstra's Algorithm
Dijkstra's algorithm is a fundamental shortest-path algorithm used to find the shortest paths in graphs with non-negative edge weights. It is widely used in various real-world applications, such as finding optimal routes in transportation networks, network routing, and resource allocation


