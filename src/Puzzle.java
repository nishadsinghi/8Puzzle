//IMPORTANT: suppose that you are doing dijkstra's. You have to find the node with the minimum distance using the priority queue. Now, Suppose we have 2 (or possibly more) nodes A and B. A.distance = 50 = B.distance; but A.numberOfMovements = 20 and B.numberOfMovements = 50; Also, there exists an edge (i.e., they are adjacent) in-between A and B, which costs 0. While choosing the minimum one, it is important to note that if you choose the node B first, it will not give you the path with the minimum number of movements. because, you will move B to the cloud and then do nothing with it. All its neighbours will get a numberOfMovements = 50+1. On the other hand, if you take A first, then you will change B's previous to A because you can reach B with the same cost but with only 21 movements via A. Hence, it's a better path. It may be argued that even if this problem arises, then when we take A as the minimum, it will fix the goal's prev to A because it will provide a path of 20 + 1 movements. But That may not be the case always (it may take a lot many movements to reach goal from A, so the total number of movements required to reach the goal via A may be larger than the path via B).

import java.util.*;
import java.io.*;

public class Puzzle{
        static int cost[] = new int[8];
        
        public boolean solvable(String a, String b)// a : initial, b : final
	{
            Puzzle object = new Puzzle();
            
            int gap_a = a.indexOf('G');
            int gap_b = b.indexOf('G');
            
            String s1 = a.substring(0, gap_a) + a.substring(gap_a + 1);
            String s2 = b.substring(0, gap_b) + b.substring(gap_b + 1);
            
            char array1[] = s1.toCharArray();
            char array2[] = s2.toCharArray();
            
            for(int i = 0; i < 7; i++){
                for(int j = i + 1; j < 8; j++){
                    if(array2[i] > array2[j]){
                        object.swap(array2, i, j);
                        object.swap(array1, i, j);
                    }
                }
            }
            
            int c =0;
            for(int i=0 ; i < 7; i++){
		for(int j = i+1; j<8; j++){
                    if(array1[j] < array1[i]){
			c++;
                    }
                }
            }

            if(c%2==0){return true;}
            return false;	
	}

    
        public String move(String current, String previous){
            String output = "";
            
            int possibleSwappings[][] = new int[][]{
                {0, 3, 0, 4, 0, 0, 0, 0, 0}, 
                {2, 0, 3, 0, 4, 0, 0, 0, 0},
                {0, 2, 0, 0, 0, 4, 0, 0, 0},
                {1, 0, 0, 0, 3, 0, 4, 0, 0},
                {0, 1, 0, 2, 0, 3, 0, 4, 0},
                {0, 0, 1, 0, 2, 0, 0, 0, 4},
                {0, 0, 0, 1, 0, 0, 0, 3, 0},
                {0, 0, 0, 0, 1, 0, 2, 0, 3},
                {0, 0, 0, 0, 0, 1, 0, 2, 0}
            };
            
            //finding where the zero lies in the previous node
            int locationOfZero_prev = -1;
            for(int i = 0; i < 9; i++){ //finding where the zero lies in the previous string
                if(previous.charAt(i) == 'G'){locationOfZero_prev = i;}
            }
            
            //finding where the zero lies in current node
            int locationOfZero_curr = -1;
            for(int i = 0; i < 9; i++){ //finding where the zero lies in the current string
                if(current.charAt(i) == 'G'){locationOfZero_curr = i;}
            }
            
            if(possibleSwappings[locationOfZero_prev][locationOfZero_curr] == 1){
                //System.out.println("1");
                output += String.valueOf(previous.charAt(locationOfZero_curr));
                output += "D";
            }
            
            else if(possibleSwappings[locationOfZero_prev][locationOfZero_curr] == 2){
                //System.out.println("2");
                output += String.valueOf(previous.charAt(locationOfZero_curr));
                output += "R";
            }
            
            else if(possibleSwappings[locationOfZero_prev][locationOfZero_curr] == 3){
                //System.out.println("3");
                output += String.valueOf(previous.charAt(locationOfZero_curr));
                output += "L";
            }
            
            else if(possibleSwappings[locationOfZero_prev][locationOfZero_curr] == 4){
                //System.out.println("1");
                output += String.valueOf(previous.charAt(locationOfZero_curr));
                output += "U";
            }
            
            return output;
        }
        
	public void swap(char[] string, int i, int j){ //swap two elements of a character array
		char temp = string[i];
		string[i] = string[j];
		string[j] = temp;
	}

	public void permutations(String input, int low, int high, HashMap <String, Node> graph){ //makes all permutations of a given string, and adds them to the graph
		Puzzle object = new Puzzle();

		//base case:
		if(low == high){
                    char s[] = input.toCharArray();
                    
                    Node n = new Node(input);
                    addChildren(s, n);
                    graph.put(input, n);
		}

		//recursive relation:
		else{
                    char s[] = input.toCharArray();

                    for(int i = low; i <= high; i++){
			object.swap(s, low, i);
                        input = new String(s);
			
                        permutations(input, low+1, high, graph);
                        
                        object.swap(s, low, i);
                        //input = new String(s);
                    }
		}
	}
        
        public void addChildren(char[] s, Node n){ //for a given permutation, makes an adjacency list (sort of)
            Puzzle o = new Puzzle();
            
            int possibleSwappings[][] = new int[][]{
                {0, 3, 0, 4, 0, 0, 0, 0, 0}, 
                {2, 0, 3, 0, 4, 0, 0, 0, 0},
                {0, 2, 0, 0, 0, 4, 0, 0, 0},
                {1, 0, 0, 0, 3, 0, 4, 0, 0},
                {0, 1, 0, 2, 0, 3, 0, 4, 0},
                {0, 0, 1, 0, 2, 0, 0, 0, 4},
                {0, 0, 0, 1, 0, 0, 0, 3, 0},
                {0, 0, 0, 0, 1, 0, 2, 0, 3},
                {0, 0, 0, 0, 0, 1, 0, 2, 0}
            };   
            
            int locationOfZero = -1;
            
            for(int i = 0; i < 9; i++){
               if(s[i] == 'G'){locationOfZero = i; break;}
            }
            
            for(int j = 0; j < 9; j++){
                if(possibleSwappings[locationOfZero][j] > 0){
                    int c = cost[s[j] - 49]; 
                    o.swap(s, locationOfZero, j);
                    String b = new String(s); //System.out.println("Swapped String: " + b);
                    //Edge e = new Edge(b, c); 
                    n.children.add(new Edge(b, c));
                    o.swap(s, locationOfZero, j);
                }
            }
        }
        
        public static void main(String args[]) throws IOException{
            long start=System.currentTimeMillis();
            Puzzle object = new Puzzle();
            
            Scanner file = new Scanner(new File(args[0]));
            
            int numberOfInputs = file.nextInt();
            
            
            for(int k = 0; k < numberOfInputs; k++){
            String garbage = file.nextLine();
            String input = file.nextLine();
            
            String source = input.substring(0, 9);
            String goal = input.substring(10);
            
            //check if the goal and source are the same, just return 0 0 and blank line:
            if(source.equals(goal)){
                System.out.println("0 0");
                System.out.println();
                continue;
            }
            
            //information from the input file
            for(int i = 0; i < 8; i++){
                int temp = file.nextInt();
                cost[i] = temp;
            }
            
            //check if this is solvable:
            if(object.solvable(source, goal) == false){System.out.println("-1 -1"); System.out.println(); continue;}
            
            HashMap <String, Node> graph = new HashMap <String, Node> ();
            object.permutations("12345678G", 0, 8, graph);
            
            //DIJSKTRA's
            Set<String> keys = graph.keySet();
            
            graph.get(source).distance = 0; //here, this string is the source node.
            graph.get(source).numberOfMovements = 0;
            
            PQ queue = new PQ(); //create a priority queue in which all nodes whose approximation is known are stored.
            
            queue.add(new Node(source)); //add the source to the queue
            graph.get(source).known = true;
            
            while(queue.size() != 0){
                String min = queue.removeTop().state; //find the minimum of the queue, to move it to the cloud (i.e., remove from the priority queue) 
                Node minNode = graph.get(min);
                minNode.known = true;
                
                if(minNode.state.equals(goal)){break;}
                
                List <Edge> childrenOfMin = graph.get(min).children; //start updating all adjacent nodes of min
                Iterator <Edge> itr = childrenOfMin.listIterator();
                
                while(itr.hasNext()){
                    Edge childEdge = itr.next();
                    Node childNode = graph.get(childEdge.value);
                    
                    if(childNode == null){System.out.println("child is null. edge = " + childEdge.value);}
                    
                    if(childNode.known == true){continue;}
                    
                    else{
                        int distance = minNode.distance + childEdge.cost;
                        if(childNode.distance == -1){childNode.distance = distance; childNode.previous = minNode; queue.add(childNode); childNode.numberOfMovements = minNode.numberOfMovements + 1;}
                        else if(distance < childNode.distance){childNode.distance = distance; childNode.previous = minNode; childNode.numberOfMovements = minNode.numberOfMovements + 1;}
                        else if(distance == childNode.distance && minNode.numberOfMovements + 1 < childNode.numberOfMovements){childNode.distance = distance; childNode.previous = minNode; childNode.numberOfMovements = minNode.numberOfMovements + 1;}
                    }
                }
            }
            
            
            //PRINT THE SOLUTION
            //now, in our node with the goal state, we have all the back ponters that are required to go to the source.
            Node ptr = graph.get(goal);
            String output = "";
            int totalCost = ptr.distance;
            int counter = 0;
            
            while(ptr.previous != null){
                output = object.move(ptr.state, ptr.previous.state) + " " + output;
                ptr = ptr.previous;
                counter++;
            }
            
            System.out.println(counter + " " + totalCost);
            System.out.println(output);            
            
            }
            long end=System.currentTimeMillis();
            //System.out.println(end - start);
        }
}
