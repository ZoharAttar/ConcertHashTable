public class HashOpen {
    int[] Table = null;
    int m = 0;
    /**
     *constructor of hashtable in size m (same size of the registered people)
     */
    public HashOpen(int m) {
        this.Table = new int[m];
        this.m = m;
    }
    /**
     * the function receives person id and number of hash function to use
     * the function returns the number of steps to insert this person
     */
    public int insert(int id, int hashFunc) {
        int Steps = 0;
        int[] Functions = new int[2]; // creates an array with two optional hash functions
        boolean available = true;
        Functions[0] = id;
        Functions[1] = reverse(id);
        int Index = Functions[hashFunc - 1] % m;
        if (Table[Index] == 0) {
            Table[Index] = id;
        } else {
            int counter = 1;
            int location = 0;
            Steps = 1;
            //inserts the person into the hash table by looking for available seat -  first to his right and then to his left and so on
            while (available) {
                location = Index + counter;
                if (location < Table.length) {
                    if (Table[location] == 0) {
                        Table[location] = id;
                        available = false;
                        continue;
                    } else Steps++;
                }
                location = Index - counter;
                if (location >= 0 && location <= m - 1) {
                    if (Table[location] == 0) {
                        Table[location] = id;
                        available = false;
                    } else Steps++;
                }
                counter++;
            }
        }
        return Steps;
    }
    /**
     * the function returns the number of people in the hashtable at this moment
     */
    public int getNumberElements() {
        int counter = 0;
        for (int i = 0; i < m; i++) {
            if (Table[i] != 0)
                counter++;
        }
        return counter;
    }
    /**
     * the function returns the size of the hashtable
     */
    public int getSize() {
        return m;
    }
    /**
     * the function receives person id
     * the function returns the reverse number of the persons id
     */
    public int reverse(int num) {
        int reversed = 0;
        while (num != 0) {
            int digit = num % 10;
            reversed = reversed * 10 + digit;
            num /= 10;
        }
        return reversed;
    }
}
