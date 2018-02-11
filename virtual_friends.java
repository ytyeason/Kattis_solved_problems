import java.util.Scanner;
import java.util.*;

class virtual_friends{

  public static void main(String[] args){
     Scanner sc = new Scanner(System.in);//set up the scanner
     int test_num = Integer.parseInt(sc.nextLine());
     HashMap<String, Integer> map = new HashMap<String, Integer>();
     StringBuilder results = new StringBuilder();

     while(test_num-- > 0){

       int pairs = Integer.parseInt(sc.nextLine());
       UnionFind<String> disjoint_set = new UnionFind<>();

       while(pairs-- > 0){
         String pair = sc.nextLine();
         String[] splited_pair = pair.trim().split("\\s+");

         disjoint_set.makeSet(splited_pair[0]);
         disjoint_set.makeSet(splited_pair[1]);
         disjoint_set.unionSet(splited_pair[0],splited_pair[1]);
         results.append(disjoint_set.numInSet(splited_pair[0])).append('\n');
       }

     }

     System.out.println(results);
  }

  static class UnionFind<T> {

    private HashMap<T, T> map;
    private HashMap<T, Integer> rank;

    public UnionFind() {
      this.map = new HashMap<>();
      this.rank = new HashMap<>();
    }

    public void makeSet(T p) {
      if (!map.containsKey(p)) {// if no p element yet
        map.put(p, p);
        rank.put(p, 1);
      }
    }

    public void unionSet(T p1, T p2) {//try to union two set
      T set1 = findSet(p1);
      T set2 = findSet(p2);

      if (!set1.equals(set2)) {//if they are no in same set, then merge them
        int rank1 = rank.get(set1);
        int rank2 = rank.get(set2);

        if (rank1 < rank2) {
          map.put(set1, set2);
          rank.put(set2, rank2 + rank1);
        }
        else {
          map.put(set2, set1);
          rank.put(set1, rank1 + rank2);
        }
      }
    }

    public boolean isSameSet(T p1, T p2) {
      return findSet(p1).equals(findSet(p2));
    }

    public T findSet(T p) {
      if (map.get(p).equals(p)) {
        return p;
      }
      else {
        T set = findSet(map.get(p));
        map.put(p, set);
        return set;
      }
    }

    public int numInSet(T p) {
      T set = findSet(p);
      return rank.get(set);
    }
  }
}
