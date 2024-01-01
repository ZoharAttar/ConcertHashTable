public class LinkedList {
    Node Head = null;
    int Length = 0;
    /**
     * constructor
     */
    public LinkedList() {
        this.Head = null;
    }
    /**
     * the function receives person's array
     * the function builds a new node from the array and adds it to the list
     */
    public void add(String[] person) {
        Node node = new Node(person);
        if(Head==null) {
            this.Head = node;
        }
        Node temp = Head;
        while (null != temp.next) {
            temp = temp.next;
        }
        temp.setNext(node);
        Length++;
    }
    /**
     * the function returns the length of the list
     */
    public int getSize() {
        return Length;
    }
    /**
     * the function returns the head of the list
     */
    public Node getHead() {
        return this.Head;
    }
    /**
     * the function receives person id
     * the function returns an array in size 2 in first cell 0 in case of the id isn't in exists inside the linkedlist and 1 if it does, in the second cell
     * represents the number of steps to find the specific id inside the linkedlist
     */
    public int[] Search(int id) {
        Node temp = this.Head;
        int[] res = new int[2];
        int counter = 1; // counting the steps till finding the person
        while (temp != null && temp.id != id) {
            temp = temp.next;
            counter++;
        }
        res[1] = counter;
        if (temp != null) {
            res[0] = 1;
            return res;
        }
        res[0] = 0;
        return res;
    }

}
