import java.util.Scanner;
import java.util.*;

class easiest_260669495{
  public static void main(String[] args){
      Scanner sc = new Scanner(System.in);//set up scanner
      int input = Integer.parseInt(sc.nextLine());
      ArrayList<Integer> output_array = new ArrayList<Integer>();

      while(input!=0){
        int input_sum = sum(input);
        for(int i = 11; i<=100; i++){//trying the value one by one till 100
          int product = i*input;
          int product_sum = sum(product);//compute the sum of the product
          if(input_sum==product_sum){
            output_array.add(i);
            break;
          }
        }
        input = Integer.parseInt(sc.nextLine());
      }

      for(int a: output_array){
        System.out.println(a);
      }

  }

  public static int sum(int input){//compute the sum of a number
    int product_sum = 0;
    while(input!=0){
      product_sum = input%10 + product_sum;
      input = input/10;
    }
    return product_sum;
  }
}
