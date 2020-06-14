package com.people;

import com.base.photo;

import java.awt.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class People implements photo {
    public int HP;//总血量
    public int curHP;//现血量
    public CopyOnWriteArrayList<Image> PicList;
    public int x,y,height,width;
    public int curNum=1;//从1开始
    public int Dir=0;//1是左向，0是右向
    public int Attack = 50;

    @Override
    public Image getImage()
    {
        return PicList.get(curNum-1);
    }

    public boolean SwitchImage(int cur)
    {
        if(cur>PicList.size()+1)
            return false;
        else if(cur<1)
            return false;
        curNum=cur;
        return true;
    }

    public boolean DieOrAlive(boolean dir,int blood){
        //对于dir，true是加血，false掉血;函数返回false时即死亡
        if(!dir){
            curHP-=blood;
            if(curHP<=0){
                curHP=0;
                return false;
            }
            return true;
        }
        curHP+=blood;
        if(curHP>HP)
            curHP=HP;
        return true;
    }


    public People(){

    }

    public People(int HP, CopyOnWriteArrayList<Image> picList, int x, int y, int height, int width, int attackPoint) {
        this.HP = HP;
        PicList = picList;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.Attack=attackPoint;
        curHP=HP;
        curNum=1;
        Dir=0;
    }

    public void AddImage(Image a)
    {
        PicList.add(a);
    }

    public void RemoveImage(Image a)
    {
        PicList.remove(a);
    }


    public int getHP()
    {
        return HP;
    }

    public void setHP(int HP)
    {
        this.HP = HP;
    }

    public int getCurHP()
    {
        return curHP;
    }

    public void setCurHP(int curHP)
    {
        this.curHP = curHP;
    }

    public List<Image> getPicList()
    {
        return PicList;
    }

    public void setPicList(CopyOnWriteArrayList<Image> picList)
    {
        PicList = picList;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getCurNum()
    {
        return curNum;
    }

    public void setCurNum(int curNum)
    {
        this.curNum = curNum;
    }

    public int getDir()
    {
        return Dir;
    }

    public void setDir(int dir)
    {
        Dir = dir;
    }

    public int getAttack()
    {
        return Attack;
    }

    public void setAttack(int attackPoint)
    {
        Attack = attackPoint;
    }

}