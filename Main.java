package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the Number of players");
        int numberOfPlayers = sc.nextInt();
        sc.nextLine();

        ArrayList<Player> player = new ArrayList<>();
        for(int i=0; i<numberOfPlayers; i++){
            System.out.println("please enter name of player number "+(i+1));
            String s = sc.nextLine();
            Player p = new Player(i,s);
            player.add(p);
        }

        System.out.println("Choose the winning conditions you want to set :-");
        System.out.println("The index of winning condition you want to choose should be separated by a space character ' ' ");
        System.out.println("1. First Row Complete");
        System.out.println("2. Second Row Complete");
        System.out.println("3. Third Row Complete");
        System.out.println("4. Full House Complete");
        String winNum = sc.nextLine();
        String[] array = winNum.split(" ");
        ArrayList<Integer> conditions=new ArrayList<>();
        for (String s : array) {
            conditions.add(Integer.parseInt(s));
        }

        Moderator mod = new Moderator(player,numberOfPlayers);
        mod.setWincond(conditions);
        mod.assignTicket();
        while(!mod.checkEndGame()) {
            int temp=mod.drawNumber();
        }


    }
}
