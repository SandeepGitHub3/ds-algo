package com.sherlock.learn.dynamicprogramming;

import com.google.common.base.Stopwatch;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

//
public class UglyNumbersNaive
{
    public static void main( String[] args )
    {
        Stopwatch sw = Stopwatch.createStarted();
        int i =1500;
        int j =1;
        while(i!=0){
            if(isUgly(j)) {
                System.out.println(j);
                i--;
            }
            j++;
        }
        sw.stop();
        System.out.println("Time:"+sw.elapsed(MILLISECONDS));
    }

    static int maxDivide(int a, int b)
    {
        while(a % b == 0)
            a = a/b;
        return a;
    }

    /* Function to check if a number
    is ugly or not */
    static boolean isUgly(int no)
    {
        no = maxDivide(no, 2);
        no = maxDivide(no, 3);
        no = maxDivide(no, 5);

        return no == 1;
    }
}
