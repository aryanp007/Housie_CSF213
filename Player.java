package com.company;

import java.util.ArrayList;

public class Player {
    public String playerName;
    public int playerId;
    Ticket playerTicket;

    Player(int i,String s) {
        this.playerId=i;
        this.playerName=s;
    }

    public boolean hasCurrentNumber(int n){
        for(int i=0;i<3;i++)
        {
            for (int j=0;j<9;j++)
            {
                if(playerTicket.playerTick[i][j]==n)
                {
                    playerTicket.playerTick[i][j]=-1;
                    System.out.println(n + " Crossed out in "+playerName+"'s ticket");
                    return true;
                }
            }
        }
        return false;
    }

    public String verifyWin(ArrayList<winningCondition> wincond){
        for(int i=0;i<4;i++)
        {
            if(wincond.get(i).checkAvailability())
            {
                return wincond.get(i).verifyCondition(playerTicket);
            }
        }
        return null;
    }
}
