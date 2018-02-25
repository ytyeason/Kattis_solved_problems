import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class BankQueue_260669495 {
    public static void main(String[] args)
    {
        // money made will be the total money that the bank will process
        int solution = 0;

        Scanner sc = new Scanner(System.in);

        int peopleInQueue = sc.nextInt();
        int timeUntilClosing = sc.nextInt();

        // to store all the people in queue
        ArrayList<personInfo> listOfPeople = new ArrayList<personInfo>(peopleInQueue);

        for( int i = 0; i < peopleInQueue; i++)
        {
            int curPersonMoney = sc.nextInt();
            int curPersonTime = sc.nextInt();
            listOfPeople.add(new personInfo(curPersonMoney, curPersonTime));
        }

        //  sort list that people with the most money in descing order
        listOfPeople.sort(new Comparator<personInfo>()
        {
            @Override
            public int compare(personInfo person1, personInfo person2)
            {
                // The person with the most monet should be placed first
                return person2.money - person1.money;
            }
        } );

        // A person will be give the last possible slot that can be schedule for his max waiting time.
        boolean[] reserved = new boolean[timeUntilClosing+1];// imagine this is the time slot for the bank

        for(int j = 0; j < peopleInQueue; j++)
        {
            personInfo curPerson = listOfPeople.get(j);
            // System.out.println(curPerson.money);
            int counter = curPerson.time;
            while(!curPerson.isServed)
            {
                if (counter < 0 )// no slot for the person
                {
                    break;
                }
                else if(reserved[counter] == false)// if we can find a free slot
                {
                    curPerson.isServed = true;
                    reserved[counter] = true;
                    solution = solution + curPerson.money;
                }
                else // last slot wasn't available, go to one earlier one
                {
                    counter = counter - 1;
                }
            }
        }

        System.out.println(solution);
        sc.close();
    }
}

// this is the object that holds the tuple.
class personInfo
{
    int money;
    int time;
    boolean isServed = false;

    public personInfo(int money, int time)
    {
        this.money = money;
        this.time = time;
    }

}
