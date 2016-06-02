
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// Actually it was much more wiser and right to use some of the methods, but for the start it was much more easier for me
// :D and deadline was near, so :D let it stay this way, and sorry if it is hard to understand this "stuff" :DDDD

public class Solution2 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Person> nameList = new ArrayList<>(); // array for info about names ,services, and payments
        List<Person> nameCost = new ArrayList<>(); // array for names and sum of payments

        // Data insert by console

        String sname; // name
        String sservice; // service name
        double scost; // payment

        while (true) { // "infinite" data entry while all conditions are right
            System.out.println("Type Name or press enter to exit / or get results : ");
            sname = reader.readLine(); // reading name from console

            if (!sname.equals("")) { //if name isn't null
                double acost = 0;
                while (true) {

                    System.out.println("Enter Service Name or press enter to exit : ");
                    sservice = reader.readLine();// reading service name from console

                    if (!sservice.equals("")) //if service isn't null
                    {
                            System.out.println("Enter Service Cost : ");
                            scost = Double.parseDouble(reader.readLine()); //reading service cost from console

                            if (scost >= 0) //if service cost is not less than 0
                            {
                                acost = acost + scost;
                                nameList.add(new Person(sname, sservice, scost));
                            } else
                            System.out.println("Sorry, you entered negative number, so i decided to" +
                                    " give you one more chance to enter data starting from Name ^_^ GL&HF");
                                break;
                    } else break;
                }

                nameCost.add(new Person(sname, acost)); // putting every Person's name and sum of payments in nameCost array;
            } else break;
        }

        // Payment's information output. Info about Names and services + payments made according to Name
        for (Person s : nameList) {
            System.out.println(s);
        }

        // Counting total Sum of all Person's payments
        double sum = 0;

        for (int i = 0; i < nameList.size(); i++) {
            sum = sum + nameList.get(i).value;
        }
        System.out.println("\n" + "Total: " + Math.round(sum) + "$");

        // Counting avrg sum for each Person to pay
        double per = 1;

        for (int i = 0; i < nameList.size() - 1; i++) {
            if (nameList.get(0).name != null) {
                if (!nameList.get(i).name.equals(nameList.get(i + 1).name)) {
                    per++;
                }

            } else break;
        }
        System.out.println("Average: " + (sum / per) + "$ to be paid by mate.");


        // Expenses output
        double avg = (sum/per); // getting average sum each to pay

        System.out.println("\n" + "Expenses:"); // output of all Names and their payments sum
        for (Person a : nameCost) {
            System.out.println(a);
        }


        double max;
        double min;
        String maxName;
        String minName;
        // Transactions to be made
            System.out.println("\nTransactions to be made : ");

            List<Person> minList = new ArrayList<>(); //creating array for payments that were under Avg
            List<Person> maxList = new ArrayList<>(); //creating array for payments that were above Avg

            for (int i = 0; i < nameCost.size(); i++)
            {

                if (nameCost.get(i).value >= avg)
                {
                    max = nameCost.get(i).value;
                    maxName = nameCost.get(i).name;
                    maxList.add(new Person(maxName,max)); //adding value under avg in to minList array
                }

                if (nameCost.get(i).value < avg)
                {
                    min = nameCost.get(i).value;
                    minName = nameCost.get(i).name;
                    minList.add(new Person(minName,min)); //adding value over avg in to maxList array
                }
            }


        /// Main part of counting transaction sums to every Person
        //  Main idea is using difference between min avg and max to get value of needed transaction more precise.
        //  It was much easier and faster , when it wasn't supposed to count cents
        //  When min or max has reached avg , we do changes in (i) (j) values , count needed payments and take next
        //  (i) or (j) according to situation

        double pay;
        double sums;
        double asums;

        for (int i = 0; i < minList.size(); i++) {
            for (int j = 0; j < maxList.size(); j++) {

                min = minList.get(i).value;
                max = maxList.get(j).value;

                minName = minList.get(i).name;
                maxName = maxList.get(j).name;

                while (min != avg && max != avg) {  // while min or max aren't reaching avg

                    min = Math.round((min + 0.01)*100.0)/100.0; // we increase min by 0.01 ( for each possible cent);
                    max = Math.round((max - 0.01)*100.0)/100.0; // decreasing max by 0.01 (for eah possible cent);
                    // rounding min and max till .00 cause in this case it was making smth like 0.99999+ step not 0.1 dk why;

                    if (min == avg) { // if min reached avg first

                        pay = avg - minList.get(i).value;
                        sums = minList.get(i).value + pay; //increasing value of item (i) in minList
                        asums = maxList.get(j).value - pay; // decreasing value of item (j) in maxList


                            System.out.println(minName + " -> " + maxName + " " + Math.round((pay)*100.0)/100.0 + "$");
                            minList.get(i).setValue(sums); //saving new value into minList
                            if (sums == avg && asums != avg) {
                                maxList.get(j).setValue(asums); //saving new value into maxList
                            }

                    }

                    if (max == avg) // if max reached avg first
                    {
                        pay = maxList.get(j).value - avg;
                        sums = minList.get(i).value + pay; //increasing value of item (i) in minList
                        asums = maxList.get(j).value - pay;// decreasing value of item (j) in maxList
                        if (pay != 0 & pay > 0.1) {
                            System.out.println(minName + " -> " + maxName + " " + Math.round((pay)*100.0)/100.0 + "$");
                            minList.get(i).setValue(sums); //saving new value into minList
                            maxList.get(j).setValue(asums); //saving new value into maxList

                        }
                    }
                }
            }
        }

        /// Printing out the results of all transactions in all Person's total "pocket" sum
        System.out.println("\nResults : \n");
        for (Person x : minList)
        {
            System.out.println(x);
        }

        for (Person z : maxList)
        {
            System.out.println(z);
        }
    }
}






