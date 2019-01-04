public class PQ{
    Node array[] = new Node[362881];
    int ptr = 0; //this is the location where the last element is stored
    
    public PQ(){
        array[0] = null;
    }
    
    public int size(){
        return ptr;
    }
    
    public void add(Node node){
        ptr++;
        array[ptr] = node;
        PercolateUp(ptr);
    }
    
    public Node removeTop(){
        Node temp = array[1];
        array[1] = array[ptr];
        ptr--;
        PercolateDown(1);
        
        return temp;
    }
    
    public void PercolateDown(int index){
        if(2*index > ptr){} //no children

        else if(2*index == ptr){ // 1 child
            if(array[2*index].distance < array[index].distance){
                Node temp = array[index]; array[index] = array[2*index]; array[2*index] = temp;
            }
            
            else if(array[2*index].distance == array[index].distance && array[2*index].numberOfMovements < array[index].numberOfMovements){
                Node temp = array[index]; array[index] = array[2*index]; array[2*index] = temp;
            }
        }
        
        else{ //has 2 children
            int j;
            if(array[2*index].distance == array[2*index + 1].distance){
                if(array[2*index].numberOfMovements < array[2*index + 1].numberOfMovements){j = 2*index;}
                else{j = 2*index + 1;}
            
            }
            
            else{
                if(array[2*index].distance < array[2*index + 1].distance){j = 2*index;}
                else{j = 2*index + 1;}
            }
            
            if(array[j].distance < array[index].distance){Node temp = array[index]; array[index] = array[j]; array[j] = temp; PercolateDown(j);}
            else if(array[j].distance == array[index].distance && array[j].numberOfMovements < array[index].numberOfMovements){Node temp = array[index]; array[index] = array[j]; array[j] = temp; PercolateDown(j);}
        }
    }
    
    public void PercolateUp(int index){
        int parent = (int)(index/2);
        
        //base case
        if(parent == 0){return;}
        
        //recursive relation
        if((array[parent].distance > array[index].distance) || (array[parent].distance == array[index].distance && array[parent].numberOfMovements > array[index].numberOfMovements)){
            Node temp = array[index];
            array[index] = array[parent];
            array[parent] = temp;
            
            PercolateUp(parent);
        }
    }
}