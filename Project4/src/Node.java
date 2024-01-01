public class Node {
    int id = 0;
    String FirstName = "";
    String LastName = "";
    String TicketCatagory = "";
    Node next = null;

    /**
     * the function receives person's array including:first name,last name,id and catagory
     * the function builds a new person node
     */
    public Node(String[] person){
        this.id = Integer.valueOf(person[0]);
        this.FirstName = person[1];
        this.LastName = person[2];
        this.TicketCatagory = person[3];
    }


    /**
     * the function receives a node
     * the function set's his next node
     */
    public void setNext(Node node){
        Node temp = this.next;
        this.next = node;
        node.next = temp;
    }
    /**
     * the function returns person's array
     */
    public String[] getData(){
        String id =Integer.toString(this.id);
        String[] person = {id, this.FirstName, this.LastName, this.TicketCatagory};
        return person;
    }
    /**
     * the function returns the next node
     */
    public Node getNext(){
        return this.next;
    }
}
