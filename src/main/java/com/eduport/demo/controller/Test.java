package com.eduport.demo.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public  class Test{


    public int func(int[]arr){

        int min =Integer.MIN_VALUE;
        int minValIndex=-1;

        for (int i=0,j=2,k=1,l=3;l<arr.length;i++,k++,j++,l++){

            if(Math.abs(arr[i]-arr[j])>min) {
                min = (int) Math.abs(arr[i] - arr[j]);
                minValIndex=arr[i]<arr[j]?i:j;
            }

            if(Math.abs(arr[k]-arr[l])>min)
                min=(int)Math.abs(arr[k]-arr[l]);
            minValIndex=arr[l]<arr[k]?l:k;
        }
        return minValIndex;
}


}