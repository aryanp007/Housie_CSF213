package com.company;

import java.util.*;

public class Moderator {
    Random random = new Random();
    ArrayList<Integer> rand = new ArrayList<Integer>();
    int numberOfPlayers;
    ArrayList<Player> playerList = new ArrayList<Player>();
    ArrayList<String> winners = new ArrayList<String>();

    Moderator(ArrayList<Player> pL,int num){
        this.numberOfPlayers=num;
        this.playerList=pL;
    }

    public ArrayList<winningCondition> wincond=new ArrayList<winningCondition>();

    public void setWincond(ArrayList<Integer> set){
        firstRow fr=new firstRow();
        secondRow sr=new secondRow();
        thirdRow tr=new thirdRow();
        fullHouse fh=new fullHouse();
        wincond.add(fr);
        wincond.add(sr);
        wincond.add(tr);
        wincond.add(fh);
        while(!set.isEmpty())
        {
            wincond.get(set.get(0)-1).setAvailability(true);

            set.remove(0);
        }
//        for(int i=0;i<4;i++)
//        {
//            System.out.println(wincond.get(i).checkAvailability());
//        }
    }
    // Draws a number to announce randomly.
    public int drawNumber() {
        int upperbound = 89;
        int int_random = random.nextInt(upperbound) + 1;
        while(rand.contains(int_random)){
            int_random = random.nextInt(upperbound) + 1;
        }
        rand.add(int_random);
        System.out.println("Number Drawn is: " + int_random);
        for(int i=0; i<numberOfPlayers; i++){
            if(playerList.get(i).hasCurrentNumber(int_random))
            {
                String temp=playerList.get(i).verifyWin(wincond);
                if(temp!=null)
                {
                    winners.add(playerList.get(i).playerName+": "+temp);
                }
            }
        }
        return int_random;
    }
    // Generates a ticket randomly.
    public int[][] generateTicket() {

        int[][] playerTicket = new int[3][9];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                int rand = random.nextInt(8);
                while (playerTicket[i][rand] == -1) {
                    rand = random.nextInt(8);
                }
                playerTicket[i][rand] = -1;
            }
        }
        for (int i = 0; i < 9; i++) {
            int count = 0;
            if (playerTicket[0][i] == -1) {
                count++;
            }
            if (playerTicket[1][i] == -1) {
                count++;
            }
            if (playerTicket[2][i] == -1) {
                count++;
            }
            int lowerbound = i * 10 + 1;
            int upperbound = (i + 1) * 10;
            if (count == 0) {
                ArrayList<Integer> check = new ArrayList<Integer>();
                for (int k = 0; k < 3; k++) {
                    int rand = random.nextInt(9) + lowerbound;
                    while (check.contains(rand)) {
                        rand = random.nextInt(9) + lowerbound;
                    }
                    check.add(rand);
                }
                Collections.sort(check);

                playerTicket[0][i] = check.get(0);
                playerTicket[1][i] = check.get(1);
                playerTicket[2][i] = check.get(2);
                check.clear();
            }
            if (count == 1) {
                ArrayList<Integer> check = new ArrayList<Integer>();
                for (int k = 0; k < 2; k++) {
                    int rand = random.nextInt(9) + lowerbound;
                    while (check.contains(rand)) {
                        rand = random.nextInt(9) + lowerbound;
                    }
                    check.add(rand);
                }
                Collections.sort(check);
                for (int l = 0; l < 3; l++) {
                    if(playerTicket[l][i] != -1){
                        playerTicket[l][i] = check.get(0);
                        check.remove(0);
                    }
                }
            }
            if (count == 2) {
                ArrayList<Integer> check = new ArrayList<Integer>();
                for (int k = 0; k < 1; k++) {
                    int rand = random.nextInt(9) + lowerbound;
                    while (check.contains(rand)) {
                        rand = random.nextInt(9) + lowerbound;
                    }
                    check.add(rand);
                }
                Collections.sort(check);
                for (int l = 0; l < 3; l++) {
                    if(playerTicket[l][i] != -1){
                        playerTicket[l][i] = check.get(0);
                        check.remove(0);
                    }
                }
            }
        }
        return playerTicket;
    }

    public void assignTicket(){
        for(int i=0; i<numberOfPlayers; i++){
            Ticket tick = new Ticket(generateTicket());
            playerList.get(i).playerTicket=tick;
            System.out.println( playerList.get(i).playerName + "'s ticket is" );
            tick.printTicket();
        }
    }

    public boolean checkEndGame(){
        boolean win=true;
        for (int i=0;i<4;i++)
        {
            if(wincond.get(i).checkAvailability())
            {
                win=false;
            }
        }
        if(win)
        {
            for (int i=0;i<winners.size();i++) {
                System.out.println(winners.get(i));
            }
            return true;
        }
        return false;
    }


}

