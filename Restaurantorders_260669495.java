import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Restaurantorders_260669495
{
    static int number_of_items;
    static int[] items;
    static int number_of_orders;
    static int[] orders;
    static ArrayList<costInfo> solution = new ArrayList<>();
    static int MAX_VALUE = 0;

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        number_of_items = sc.nextInt();
        items = new int[number_of_items];
        for(int i = 0; i < number_of_items; i++)
        {
            items[i] = sc.nextInt();
        }

        number_of_orders = sc.nextInt();
        orders = new int[number_of_orders];
        solution.add(new costInfo(State.possible, number_of_items));

        for(int i = 0; i < number_of_orders; i++)
        {
            int curNum = sc.nextInt();// total cost
            if(curNum > MAX_VALUE)
            {
                int numberOfcostInfos = curNum - MAX_VALUE;
                for(int j = 0; j < numberOfcostInfos; j++)//add link for each cost from 1 to the largest cost
                {
                    solution.add(new costInfo(State.impossible, number_of_items));
                }
                findCombination(MAX_VALUE+1, curNum);//for each cost from 1 to curNum, process the combination of the price
                printSolution(curNum);
                MAX_VALUE = curNum;//remember the cost we just processed
            }
            else
            {
                printSolution(curNum);//if this cost has already been processed
            }
        }
        sc.close();
    }

    public static void findCombination(int startIndex, int size)
    {
        for(int i = startIndex; i <= size; i++)//iterate through the cost, for each cost
        {
            costInfo curcostInfo = solution.get(i);
            for(int j = 0; j < number_of_items; j++)//for each item calculate the difference
            {
                int result = i - items[j];// cost minus price

                if(result < 0)//i.e: cost is 1, price is 5, cost is less than price
                {
                    continue;
                }
                else if(result == 0)//if the cost if price
                {
                    if(curcostInfo.aState == State.possible)
                    {
                        curcostInfo.aState = State.ambiguous;
                    }
                    else if(curcostInfo.aState == State.impossible)
                    {
                        curcostInfo.aState = State.possible;
                        curcostInfo.code[j] = 1;//item j
                    }
                }
                else
                {
                    costInfo parentcostInfo = solution.get(result);
                    switch(parentcostInfo.aState)
                    {
                        case impossible :
                            continue;
                        case possible :
                            switch(curcostInfo.aState)
                            {
                                case impossible :
                                    curcostInfo.aState = State.possible;
                                    for(int w = 0; w < number_of_items; w++)
                                    {
                                        curcostInfo.code[w] = parentcostInfo.code[w];
                                    }
                                    curcostInfo.code[j] += 1;// increase number of item j
                                    continue;
                                case possible :
                                    if(!curcostInfo.equals(parentcostInfo, j))//curcostInfo may have been assigned more than once before
                                    //e.g: 14-4 = 10, then curcostInfo(14) is assigned as the child of 10 in impossible condition,
                                    //then 14-8 = 4, this time 14 is goes here in possible condition, but 14 is no child of 4,
                                    // so goes in this condition, and we set the state to ambiguous
                                    {
                                        curcostInfo.aState = State.ambiguous;
                                        break;
                                    }
                                    continue;//if parent is just the curcostInfo minus item j
                                case ambiguous :
                                    curcostInfo.aState = State.ambiguous;
                                    break;
                            }
                        case ambiguous :
                            curcostInfo.aState = State.ambiguous;
                            break;
                    }
                }

            }
        }
    }

    public static void printSolution(int index)
    {
        costInfo answer = solution.get(index);

        if(answer.aState == State.possible)
        {
            for(int i = 0; i < number_of_items; i++)
            {
                int numberOfPrint = answer.code[i];

                for(int j = 0; j < numberOfPrint; j++)
                {
                    System.out.print((i+1) + " ");
                }
            }
        }
        else if(answer.aState == State.impossible)
        {
            System.out.print("impossible");
        }
        else if(answer.aState == State.ambiguous)
        {
            System.out.print("ambiguous");
        }
        System.out.println("");
    }
}

class costInfo
{
    State aState;
    int[] code;

    public costInfo(State pState, int numberOfitems)
    {
        aState = pState;
        code = new int[numberOfitems];
    }

    public boolean equals(costInfo acostInfo, int indexToIncrement)
    {
        boolean isEqual = true;
        for(int i = 0; i < acostInfo.code.length; i++)
        {
            if(i == indexToIncrement)
            {
                int value = acostInfo.code[i] + 1;
                if(value != this.code[i])
                {
                    isEqual = false;
                    break;
                }
            }
            else
            {
                if(acostInfo.code[i] != this.code[i])
                {
                    isEqual = false;
                    break;
                }
            }
        }
        return isEqual;
    }

}

enum State
{
    impossible, ambiguous, possible;
}
