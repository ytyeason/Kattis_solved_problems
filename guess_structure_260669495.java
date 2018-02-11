import java.util.Scanner;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Stack;

class guess_structure_260669495{

  public static void main(String[] args){

			Scanner scanner = new Scanner(System.in);//define new scanner
      ArrayList<String> result_list = new ArrayList<String>();

			while (scanner.hasNextLine()) {

        //initialize every time
        PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                if (o1 >= o2) {
                    return -1;
                }
                return 1;
            }
        });//define a new comparator, because we dont want natural ordering
        ArrayList<Integer> queue = new ArrayList<Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        int is_priority_queue = 1;
        int is_queue = 1;
        int is_stack = 1;//to indicate if the data structure is stack

        String[] splitStr = scanner.nextLine().trim().split("\\s+");//split the input line to tokens
        int operations = 0;

        if(splitStr.length==1){//if not operations
          operations = Integer.parseInt(splitStr[0]);
        }

        for(int i = 0; i< operations; i++){//inside each data structure
          splitStr = scanner.nextLine().trim().split("\\s+");

          if(Integer.parseInt(splitStr[0])==1){//for operation 1
            if(is_priority_queue==1){
              pQueue.offer(Integer.parseInt(splitStr[1]));//put in priority queue
            }

            if(is_queue==1){
              queue.add(Integer.parseInt(splitStr[1]));//put in queue
            }
            // System.out.println(Arrays.toString(queue.toArray()));

            if(is_stack==1){
              stack.push(Integer.parseInt(splitStr[1]));//put in stack
            }

          }else{//for operation 2

            if(!pQueue.isEmpty()&&is_priority_queue==1){

              int a = pQueue.poll();
              if(a!=Integer.parseInt(splitStr[1])){//if not expected output
                is_priority_queue = 0;
              }
            }else{
              is_priority_queue = 0;
            }

            if(!queue.isEmpty()&&is_queue==1){

              int a = queue.remove(0);
              if(a!=Integer.parseInt(splitStr[1])){//if not expected output
                is_queue = 0;
              }
            }else{
              is_queue = 0;
            }

            if(!stack.empty()&&is_stack==1){

              int a = stack.pop();
              if(a!=Integer.parseInt(splitStr[1])){//if not expected output
                is_stack = 0;
              }
            }else{
              is_stack = 0;
            }

          }
        }

        if(is_stack+is_queue+is_priority_queue==0){//evaluation
          result_list.add("impossible");
        }else if (is_stack+is_queue+is_priority_queue>1){
          result_list.add("not sure");
        }else{
          if(is_stack==1){
            result_list.add("stack");
          }else if(is_queue==1){
            result_list.add("queue");
          }else{
            result_list.add("priority queue");
          }
        }
      }

      for(String a:result_list){
        System.out.println(a);
      }
			scanner.close();

  }
}
