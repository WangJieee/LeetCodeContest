import java.util.*;
public class LCcontest155 {
    public static String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        // declare array to store parent node index
        int [] parent = new int[s.length()];
        // initialise the parent node to the node index itself
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < pairs.size(); i++) {
            // Reassign parent index to each node by union find
            union(parent, pairs.get(i).get(0), pairs.get(i).get(1));
        }
        
        //create a hashmap to store each disjoint parent and its children
        HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < parent.length; i++) {
            int p = find(parent, i);
            if (!map.containsKey(p)) {
                map.put(p, new ArrayList<Integer>());
            }
            map.get(p).add(i);
        }

        char[] result = new char[s.length()];
        //sort the children characters in each group
        for(Map.Entry<Integer, List<Integer>> entry: map.entrySet()) {
            List<Integer> children = entry.getValue();
            List<Character> li = new ArrayList<Character>();
            for(int idx: children) {
                li.add(s.charAt(idx));
            }
            Collections.sort(li);
            //place the sorted characters at the corresponding index
            int i = 0;
            for(int idx: children) {
                result[idx] = li.get(i);
                i++;
            }
        }

        return new String(result);
    }
    
    public static void union(int[] parent, int a, int b) {
        //find root parent index of node at a and b
        int pa = find(parent, a);
        int pb = find(parent, b);
        
        //assign pb to pa
        parent[pa] = pb;
    }
    
    public static int find(int[] parent, int n) {
        if (parent[n] == n) {
            return n;
        } else {
            return find(parent, parent[n]);
        }
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int n = in.nextInt();
        List<List<Integer>> pairs = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
            List<Integer> pair = new ArrayList<Integer>();
            int a = in.nextInt();
            int b = in.nextInt();
            pair.add(a);
            pair.add(b);
            pairs.add(pair);
        }
        
        System.out.println(smallestStringWithSwaps(str,pairs));
        
    }
}
