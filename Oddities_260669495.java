import java.util.Scanner;

class Oddities_260669495{
  public static void main(String[] args){
      Scanner sc = new Scanner(System.in);
      int test_num = Integer.parseInt(sc.nextLine());//set up the scanner
      String[] output_array = new String[test_num];

      for(int i = 0; i < test_num; i++){
        int input = Integer.parseInt(sc.nextLine());
        String result = "";
        if(Math.abs(input)%2==0){//evaluate if is odd
          result = "even";
        }else{
          result = "odd";
        }
        output_array[i] = input + " is " + result;

      }

      for(String output:output_array){
        System.out.println(output);
      }

  }
}
