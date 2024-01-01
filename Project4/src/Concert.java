import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Concert {
    /**
     * the function receives a file name
     * the function returns a linkedlist that contains all people inside the file
     */
    public static LinkedList getFromFile(String FileName) {
        LinkedList Guests = new LinkedList();
        try {
            File file = new File(FileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) { //reads every line in the input and creates a new person node of it
                String[] Person = line.split(",");
                Guests.add(Person);
            }
            fr.close();
            return Guests;
        } catch (IOException e) {
        }
        return Guests;
    }
    /**
     * the function receives a file path
     * the function returns a hashtable that contains all the registered people in the concert
     */
    public static HashClosed registerCrowd(String file_path) {
        LinkedList Guests = new LinkedList();
        Guests = getFromFile(file_path);
        int Size = Guests.getSize();
        HashClosed Registers = new HashClosed(Size);
        Node node = Guests.getHead();
        for (int i = 0; i < Size; i++) { //going throw the input file and insert all the people in the file into the hash closed
            if (node == null) {
                break;
            }
            Registers.insert(node.getData());
            node = node.next;
        }
        return Registers;
    }
    /**
     * the function receives a file path and a hashtable of all the registered people
     * the function returns a sorted array that contains all the arrived people and if not all registered people came, the function adds people by they category till the hall is full
     */
    public static int[] reception(String file_path, HashClosed registered) {
        int N = registered.getNodesSizeSum(); // represents the number of people that registered into the concert
        int[] res = new int[N];

        //linked list of all ticket catagories
        LinkedList vip = new LinkedList();
        LinkedList GR = new LinkedList();
        LinkedList IR = new LinkedList();
        LinkedList OR = new LinkedList();

        int arrivels_registered = 0; //represents the number of people that arrived and was registered into the concert
        int arrivels = 0; //represents the number of people that arrived into the concert

        try {
            File file = new File(file_path);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            //going throw each person in the input file and adds it to the ticket list or to the res array if he registered before
            while ((line = br.readLine()) != null) {
                String[] Person = line.split(",");
                arrivels++;
                if (registered.search(Person)[0] == 1) {
                    res[arrivels_registered] = Integer.valueOf(Person[0]);
                    arrivels_registered++;
                } else {
                    if (Person[3].compareTo("VIP") == 0) {
                        vip.add(Person);
                    } else if (Person[3].compareTo("GOLDEN_RING") == 0) {
                        GR.add(Person);
                    } else if (Person[3].compareTo("INNER_RING") == 0) {
                        IR.add(Person);
                    } else {
                        OR.add(Person);
                    }

                }

            }

            fr.close();
            // in case of all the people in the file registered before
            if (vip.Head == null && OR.Head == null && GR.Head == null && IR.Head == null) {
                int[] new_res = new int[arrivels_registered];
                //copy the array without the 0's at the end
                new_res = copy_array(res, arrivels_registered);
                return sort(new_res, 0, arrivels_registered - 1);
            } else {
                // in case of available seats, sells tickets by their category
                for (int i = arrivels_registered; i <= arrivels; i++) {
                    if (i == N) {
                        break;
                    }
                    if (vip.Head != null) {
                        res[i] = vip.Head.id;
                        vip.Head = vip.Head.next;
                    } else if (GR.Head != null) {
                        res[i] = GR.Head.id;
                        GR.Head = GR.Head.next;
                    } else if (IR.Head != null) {
                        res[i] = IR.Head.id;
                        IR.Head = IR.Head.next;
                    } else if (OR.Head != null) {
                        res[i] = OR.Head.id;
                        OR.Head = OR.Head.next;
                    }
                }
                if (N <= arrivels) {
                    return sort(res, 0, res.length - 1);
                }
                int[] new_res = new int[arrivels];
                //copy the array without the 0's at the end
                new_res = copy_array(res, arrivels);
                return sort(new_res, 0, arrivels - 1);
            }
        } catch (IOException e) {
            return res;
        }
    }
    /**
     * the function receives an array, start index, mid index and end index
     * the function merge the array
     */
    public static void merge(int[] arr, int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    /**
     * the function that sorts arr[l..r] using merge()
     */
    public static int[] sort(int[] arr, int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
        return arr;
    }
    /**
     * the function receives an array and size
     * the function returns an array in the received size
     */
    public static int[] copy_array(int[] arr, int size) {
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = arr[i];
        }
        return res;
    }
    /**
     * the function receives a file path and hashtable
     * the function receives the average steps to search all the people inside the file
     */
    public static int reception_AverageSteps(String file_path, HashClosed registered) {
        int sum = 0; //represents the sum of all steps till empty seat
        int counter = 0; //counts the number of people in the file
        try {
            File file = new File(file_path);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] Person = line.split(",");
                sum += registered.search(Person)[1];
                counter++;
            }
            fr.close();

        } catch (IOException e) {
        }
        return sum / counter;
    }
    /**
     * the function receives a sorted crowed array, a hashtable and the number of hash function
     * the function returns a statistics of steps that we need to take to insert all the people inside the hall for the specific hash function
     */
    public static int[] seatingArrangement(int[] sortedCrowed, HashClosed registered, int functionNum) {
        int[] res = new int[4];
        int sum = registered.getNodesSizeSum(); //represents the number of registered people

        HashOpen seats = new HashOpen(sum);
        int steps = 0;
        int N = sortedCrowed.length;
        //going throw the sorted list, counting the number of steps to find their seats and calculates the statistics
        for (int i = 0; i < N; i++) {
            steps += seats.insert(sortedCrowed[i], functionNum);
            if (i+1 == (int)(N / 2)) {
                res[0] = steps;
            }
            if (i+1 == (int)(3 * N / 4)) {
                res[1] = steps;
            }
            if (i+1 == (N - (int)(Math.sqrt(N)))) {
                res[2] = steps;
            }
        }
        res[3] = steps - res[2];
        return res;
    }
}
