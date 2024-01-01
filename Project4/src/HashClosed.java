public class HashClosed {
    int m = 0;
    LinkedList[] HashTable = null;

    /**
     * constructor of hashtable in size m (equals to a third from the registered people
     */
    public HashClosed(int m) {
        if (m < 3 && m > 0) {
            this.m = 1;
        } else {
            this.m = m / 3;
        }
        this.HashTable = new LinkedList[this.m];
        //initialize the table with new lists
        for (int i = 0; i < this.m; i++) {
            LinkedList list = new LinkedList();
            HashTable[i] = list;
        }
    }

    /**
     * register person to the concert
     */
    public void insert(String[] person) {
        int Index = Integer.valueOf(person[0]) % this.m;
        HashTable[Index].add(person);
    }

    /**
     * the function returns an array that every cell represents the length of the matches linkedlist
     */
    public int[] getNodesSize() {
        int[] ArraySize = new int[this.m];
        for (int i = 0; i < this.m; i++) {
            ArraySize[i] = HashTable[i].Length;
        }
        return ArraySize;
    }

    /**
     * the function receives a person array
     * the function returns an array in size 2 in first cell 0 in case of the id isn't in exists inside the linkedlist and 1 if it does, in the second cell
     * represents the number of steps to find the specific id inside the linkedlist
     */
    public int[] search(String[] person) {
        int Index = Integer.valueOf(person[0]) % this.m;
        return HashTable[Index].Search(Integer.valueOf(person[0]));
    }

    /**
     * the function returns the size of array of hashtable
     */
    public int getSize() {
        return this.m;
    }

    /**
     * the function returns the sum of every linkedlist in the hashtable
     */
    public int getNodesSizeSum() {
        int sum = 0;
        int[] elements = getNodesSize();

        for (int i = 0; i < elements.length; i++) {
            sum += elements[i];
        }
        return sum;
    }

}
