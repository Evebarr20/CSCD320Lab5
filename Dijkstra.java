import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Dijkstra {

    public static void main(final String[] args) throws FileNotFoundException {
        Scanner read = new Scanner(new File(args[0])); //open file
        int input = Integer.parseInt(args[1]); //set input to the first argument
        int count = 0; // create counter variable
        while (read.hasNextLine()) { //create a while loop that will go through and count each line of the file
            read.nextLine();
            count++;
        }
        read.close(); //close the read scanner
        Scanner insert = new Scanner(new File(args[0])); //open file again

        // created a linkedList same size as lines in file
        LinkedList[] list = new LinkedList[count];
        int i;
        while (insert.hasNextLine()) { //go through each line in the file again
            String line = insert.nextLine();
            String[] graph = line.split(":"); //split lines with :
            int number = Integer.parseInt(graph[0]);
            list[number] = new LinkedList(); //create a new linkedList
            if (graph.length > 1) {
                String[] graph1 = graph[1].split(";"); //split numbers with semicolons ;
                i = 0;
                while (i < graph1.length) {
                    String[] graph2 = graph1[i].split(","); //split files with commas ,
                    Edge edge = new Edge(number, Integer.parseInt(graph2[0]), Integer.parseInt(graph2[1])); // create a edge
                    list[number].add(edge); //add the edge into the list
                    i++;
                }
            }
        }
        insert.close(); //close insert scanner
        Vertex[] vertex = Dijkstra(list, input); //call the Dijkstra method
        printMethod(vertex, input); //call the print method
    }
    public static void printMethod(Vertex[] vertex, int input) {
        int i = 0;
        while (i < vertex.length) {
            if (i == input) { //if i equals the number entered as argument 1
                i++; //then increment the i by 1
                continue; //continue through the while loop
            }
            Vertex Q = vertex[i];
            int d = Q.d;
            if (d < Integer.MAX_VALUE) { //if d is less than infinity
                String string;
                string = "[" + i + "] shortest path: (" + input + ", "; //create a string that will have "shortest path"
                StringBuilder string1;
                string1 = new StringBuilder(i + ")  shortest distance: " + d);  //use string builder to have "shortest d"
                if (Q.p.i != input) { //if doesn't equal the number entered as argument 1
                    do {
                        Q = Q.p;
                        string1.insert(0, Q.i + ", "); //insert q.i and a comma to string1
                    } while (Q.p.i != input);
                }
                System.out.println(string + string1); //print out the string + string1
            } else { //else it's going to print unreachable
                String string3;
                string3 = "[" + i + "] unreachable";
                System.out.println(string3);
            }
            i++; //increment i by 1
        }
    }

    public static class LinkedList { //create a linkedList class
        private int size;
        private Node head;


        //inner class for ListNode
        private static class Node {
            private final Object data;
            private Node next;

            private Node(Object d) {
                this.data = d;
                this.next = null;
            }
        }


        //inner class for Node
        public int size() {
            return this.size;
        }

        public void add(Object e) { //add Object to list
            Node node = new Node(e);
            if (this.size != 0) {
                Node cur = this.head;
                if (cur.next != null) {
                    do {
                        cur = cur.next;
                    } while (cur.next != null);
                }
                cur.next = node;
            } else {
                this.head = node;
            }
            this.size++;
        }


        public Object get(int index) { //gets index of the current node
            if (index < 0 || index >= this.size) {
                throw new IndexOutOfBoundsException(" Provided index is out of bounds! \n" + index);
            }
            Node cur = this.head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            return cur.data;

        }
    }


    public static class Heap { //create public static Heap class

        public static void Build_Min_Heap(Vertex[] A) { //make a build_Min_heap method that accepts Vertex array
            int size = A.length;
            for (int i = size / 2 - 1; i >= 0; i--)
                Min_heapify(A, size, i); //call the Min_heapify method
        }
        static void Min_heapify(Vertex[] A, int size, int i) {
            int parent = (int) Math.floor(i); //parent node
            int leftChild = (int) Math.floor(2 * i); //left child tree node
            int rightChild = (int) Math.floor(2 * i) + 1; //right child tree node

            if (leftChild < size && A[leftChild].d < A[parent].d) //if leftchild tree node is less than parent node
                parent = leftChild; //set parent to left child


            if (rightChild < size && A[rightChild].d < A[parent].d) //if rightChild tree node is less than parent node
                parent = rightChild; //set parent to rightchild

            if (parent != i) { //if parent doesn't equal to i
                Vertex temp = A[i]; //swap
                A[i] = A[parent];
                A[parent] = temp;
                Min_heapify(A, size, parent); //recursive call Min_heapify
            }
        }

        public static Vertex extractMin(Vertex[] A, int heapSize) { //extract min from heap
            if (A.length < 1) {
                throw new IllegalArgumentException("Length less than 1"); //if Vertex array is less than 1 throw error
            }
            Vertex temp = A[0]; //swap
            A[0] = A[heapSize -1];
            Min_heapify(A, heapSize, 0); //call Min_heapify method
            return temp; //return temp
        }
    }

    public static class Vertex { //vertex class
        int d, i; //create 2 integers
        Vertex p; //create vertex previous

        public Vertex() { //create a constructor
            this.p = null;
            this.d = d;
            this.i = i;
        }

    }

    public static class Edge { //create a edge class
        final int s, d, w; //have 3 integers source, destination, and weight

        public Edge(int source, int destination, int weight) { //create a constructor
            this.s = source;
            this.d = destination;
            this.w = weight;
        }

    }

    public static Vertex[] Dijkstra(LinkedList[] G, int s) {
        Vertex[] dist = new Vertex[G.length]; //create a vertex array that is the size of linkedList array G
        int i = 0;
        if (i < dist.length) { //if counter is less than vertex length
            do {
                dist[i] = new Vertex();
                dist[i].i = i;
                dist[i].d = Integer.MAX_VALUE; //set vertex d to infinity
                dist[i].p = null; //set vertex p to null
                i++; //increment counter i
            } while (i < dist.length); //continue doing this until i is no longer less than vertex array length
        }
        dist[s].d = 0;
        Vertex[] Q = new Vertex[dist.length];
        for (int j = 0; j < Q.length; j++) {
           Q[j] = dist[j]; //copy elements of vertex array to the new vertex arrray
        }
        Heap.Build_Min_Heap(Q); //call build min heap method
        int length = Q.length; //create a variable length that is to Q length
        if (length != 0) { //if length does not equal 0
            do {
                Vertex u = Heap.extractMin(Q, length); //extract minimum from heap
                if (u.d == Integer.MAX_VALUE) { //if u.d equals infinity
                    break; //then break the loop
                }
                int k = 0; //create counter k
                while (k < G[u.i].size()) {
                    Edge edge = (Edge) G[u.i].get(k); //create Edge variable that gets the index of k
                    if (dist[edge.d].d > u.d + edge.w) { //relax edge (compares the edges)
                        dist[edge.d].d = u.d + edge.w;
                        dist[edge.d].p = u;
                    }
                    k++; //increment k
                }
                length--; //decrement length
                Heap.Build_Min_Heap(Q); //call build min heap
            } while (length != 0); //continue the loop till length equals 0
        }
        return dist; //return vertex array
    }
}



