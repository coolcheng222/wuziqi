package com.sealll.dao;

import org.springframework.stereotype.Repository;

/**
 * @author sealll
 * @time 2021/6/19 18:41
 */
public class ChessMap {
    private int[][] map;
    private int size;

    public ChessMap(int size){
        map = new int[size][size];
    }
    public ChessMap(){
        this(16);
    }

    public boolean setChess(Integer color,int x,int y){
        if(map[x][y] != 0){
            map[x][y] = color;
            return true;
        }else{
            return false;
        }
    }
    public boolean isWin(Integer color,int x,int y){
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        for(int i = -4 ;i <= 4;i++){
            if(isValidPoint(x + i, y)){
                if(map[x][y] == color){
                    count1++;
                    if(count1 >= 5){
                        return true;
                    }
                }else{
                    count1 = 0;
                }
            }
            if(isValidPoint(x,y + i)){
                if(map[x][y] == color){
                    count2++;
                    if(count2 >= 5){
                        return true;
                    }
                }
            }
            if(isValidPoint(x + i,y + i)){
                if(map[x][y] == color){
                    count3++;
                    if(count3 >= 5){
                        return true;
                    }
                }
            }
            if(isValidPoint(x - i,y + i)){
                if(map[x][y] == color){
                    count4++;
                    if(count4 >= 5){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean isValidPoint(int x,int y){
        return x >= 0 && x < this.size && y >= 0 && y <= this.size;
    }
}
