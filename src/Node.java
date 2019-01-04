import java.util.*;

public class Node{
	//fields
        int numberOfMovements;
	int distance = -1;
	boolean known = false;
	String state;
	List <Edge> children = new ArrayList<Edge>();
        Node previous = null;

	//constructor
	public Node(String s){
		this.state = s;				
	}
        
        public Node(String s, int distance){
            this.state = s;
            this.distance = distance;
        }
}

