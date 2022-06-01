package com.example.myapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandList {
    public static HashMap<String , List<String >> getData() {
        HashMap<String, List<String>> expandListDetail = new HashMap<String, List<String>>();
        List<String> Vegies = new ArrayList<String>();
        Vegies.add("\n\t\t You can Take vegies unlimitedly"+"\n\t\t Stay Connected with leafy Vegetables");

        List<String> Fruits = new ArrayList<String>();

        Fruits.add("\t\t\tFood Items"+"\t\t\t\t\t\t\t\t"+"Portion Size");
        Fruits.add("\t1. Apple "+"\t\t\t\t\t\t\t\t\t"+"1 Daily");
        Fruits.add("\t2. Pear "+"\t\t\t\t\t\t\t\t"+"One ");
        Fruits.add("\t3. Dates"+"\t\t\t\t\t\t\t\t"+"5");
        Fruits.add("\t4. Papaya"+"\t\t\t\t\t\t\t\t\t"+"4-5 slices");
        Fruits.add("\t5. Stawberry"+"\t\t\t\t\t\t\t\t\t\t\t"+"As per your Choice");
        Fruits.add("\n\t\t You can also make a yummy Fruit Salad or Fruit Juices according to your Taste");



        List<String> Proteins = new ArrayList<String>();

        Proteins.add("\t\t\tFood Items"+"\t\t\t\t\t\t\t\t"+"Portion Size");
        Proteins.add("\t1. Shami Kabab"+"\t\t\t\t\t\t\t\t\t"+"2 piece");
        Proteins.add("\t2. Chicken Breast  "+"\t\t\t\t\t\t\t\t"+"Palm size");
        Proteins.add("\t3. fruits "+"\t\t\t\t\t\t\t\t"+"Avoid banana and Mango");
        Proteins.add("\t4. Fish"+"\t\t\t\t\t\t\t\t\t"+"Air Fry 1 piece");
        Proteins.add("\t5. Mutton without fats"+"\t\t\t\t\t\t\t\t\t\t\t"+"Palm size");
        Proteins.add("\n\t\t Have 4 portion of Proteins everyday,Maximum 1 portion at a time");

        List<String> Carbs = new ArrayList<String>();
        Carbs.add("\t\t\tFood Items"+"\t\t\t\t\t\t\t\t"+"Portion Size");
        Carbs.add("\t1. Poridge"+"\t\t\t\t\t\t\t\t\t"+"1 Cup");
        Carbs.add("\t2. Roti   "+"\t\t\t\t\t\t\t\t"+"Palm Size");
        Carbs.add("\t3. Bread Bran "+"\t\t\t\t\t\t\t\t"+"2-slices");
        Carbs.add("\t4. ChickPeas"+"\t\t\t\t\t\t\t\t\t"+"With Salad 1 Bowl");
        Carbs.add("\t5. ReadBeans"+"\t\t\t\t\t\t\t\t\t\t\t"+"1 Cup");
        Carbs.add("\t5. Rice"+"\t\t\t\t\t\t\t\t\t\t\t"+"7 Tbps");

        Carbs.add("\n\t\t Have 4 portion of Carbs everyday,Maximum 1 portion at a time");



        List<String> Dairy= new ArrayList<String>();
        Dairy.add("\t\t\tFood Items"+"\t\t\t\t\t\t\t\t"+"Portion Size");
        Dairy.add("\t1. Low Fat Milk"+"\t\t\t\t\t\t\t\t\t"+"1 Glass");
        Dairy.add("\t2. Low Fat Yogurt"+"\t\t\t\t\t\t\t\t"+"4-6 tbps");
        Dairy.add("\t3. Low Fat Cheese"+"\t\t\t\t\t\t\t\t"+"Match Box Size");
        Dairy.add("\t4. Low Fat Cottage Cheese"+"\t\t\t\t"+"Half Cup");
        Dairy.add("\t5. Nuts Milk"+"\t\t\t\t\t\t\t\t\t\t\t"+"1 Glass");
        Dairy.add("\n\t\t Have 3 portion of dairy everyday,Maximum 1 portion at a time");



        List<String> Bavareges = new ArrayList<String>();
        Bavareges.add("\t\t\tFood Items"+"\t\t\t\t\t\t\t"+"Portion Size");
        Bavareges.add("\t1.Green Tea"+"\t\t\t\t\t\t\t\t\t\t"+"1 cup");
        Bavareges.add("\t2.Cumin Tea"+"\t\t\t\t\t\t\t\t\t"+"1 cup");
        Bavareges.add("\t3.Lemon Ginger Tea"+"\t\t\t\t\t"+"1 cup");
        Bavareges.add("\t4.Cinamom Tea"+"\t\t\t\t\t\t\t\t"+"1 cup");
        Bavareges.add("\t5.Fresh Fruit Juices"+"\t\t\t\t\t"+"1 Glass");
        Bavareges.add("\t6.Fresh Vegetable Juices"+"\t\t\t"+"1 Glass");
        Bavareges.add("\t7.Chai without Sugar"+"\t\t\t\t\t"+"1 cup");
        Bavareges.add("\n\t\t Have 2-3 Portions of Tea and 1 portions of Juices Daily " );
        Bavareges.add("\n\n\n\n ");



        expandListDetail.put("Vegetables",Vegies);
        expandListDetail.put("Fruits ",Fruits);
        expandListDetail.put("Proteins ",Proteins);
        expandListDetail.put("Carbs",Carbs);
        expandListDetail.put(" Dairy",Dairy);
        expandListDetail.put(" Bavareges",Bavareges);

        return expandListDetail;
    }
}