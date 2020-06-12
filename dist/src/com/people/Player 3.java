package com.people;

import com.part.Level;
import com.part.Point;

import java.awt.*;
import java.util.List;

public class Player extends People
{
    int KillNumber=0;
    public int xMax=1000,xMin=30,yMax=500,yMin=40;
    public int speed;
    private Level level;

    EnemyList EnList = EnemyList.getInstance();

    public Player(int HP, List<Image> picList, int x, int y, int height, int width,int speed,int attack) 
    {
        super(HP, picList, x, y, height, width,attack);
        this.speed=speed;
        this.level = new Level();
    }

    public void update(Point[] points){
        ++this.KillNumber;
        int temp1, temp2 = this.KillNumber, div = 100;
        for(int i = 0;i < 3; ++i){
            temp1 = temp2 / div;
            temp2 %= div;
            points[i].update(temp1);
            div /= 10;
        }
        level.update(0);
    }

    public void update0(Point[] points){
        //++this.KillNumber;
        int temp1, temp2 = this.KillNumber, div = 100;
        for(int i = 0;i < 3; ++i){
            temp1 = temp2 / div;
            temp2 %= div;
            points[i].update(temp1);
            div /= 10;
        }
        level.update(0);
    }
    
    public void updateCnt(){
        level.updateCnt();
    }

    public void setMapLimit()
    {
        //设置地图大小限制(默认)
        xMin=0; xMax=1000;
        yMin=0; yMax=500;
    }

    public void setMapLimit(int ax1,int ax2,int ay1,int ay2)
    {
        //设置地图大小限制(指定)
        xMin=ax1; xMax=ax2;
        yMin=ay1; yMax=ay2;
    }
    

    //移动
    public void MoveUp()
    {
        y=y-speed;
        if (y<=yMin) y=yMin+10;
    }

    public void MoveDown()
    {
        y=y+speed;
        if (y>=yMax) y=yMax-10;
    }

    public void MoveLeft()
    {
        if (this.Dir==1)//左向
        {
            x=x-speed;
            if (x<=xMin) x=xMin+10;
        }
        else if (this.Dir==0) //右向
        {
            this.Dir=1; //调整方向
        }
    }

    public void MoveRight()
    {
        if (this.Dir == 0)//右向
        {
            x = x + speed;
            if (x >= xMax) x = xMax - 10;
        }
        else if (this.Dir == 1) //左向
        {
            this.Dir = 0; //调整方向
        }
    }

    //攻击
    public void Attack(int type,int distance,int damage)
    {
        //type规定了攻击方式。1为半圆形近距离攻击。2为长距离直线攻击。
        if (type==1)
        {
            AttackType1(distance,damage);
        }
        else if (type==2)
        {
            AttackType2(distance,damage);
        }
    }

    public void AttackType1(int distance,int damage)
    {
        //type1 1为半圆形近距离攻击。
        for (int i=0;i<EnList.enemyList.size();i++)
        {
            if (TouchOrNot(x, y, EnList.enemyList.get(i).x, EnList.enemyList.get(i).y,distance))
            {
                EnList.enemyList.get(i).DieOrAlive(false,damage);
            }
        }
        //切换图片未添加
    }

    public boolean TouchOrNot(int x,int y,int a,int b,int distance)
    {
        double dis=Math.sqrt((x-a)*(x-a)+(y-b)*(y-b));

        if (dis>distance+1) return false;

        return true;
    }

    public  void AttackType2(int distance,int damage)
    {
        //type2 2为长距离直线攻击。。
        for (int i=0;i<EnList.enemyList.size();i++)
        {
            if ((Dir==1&&x>=EnList.enemyList.get(i).x)||(Dir==0)&&(x<=EnList.enemyList.get(i).x))
            {
                if (Math.abs(y-EnList.enemyList.get(i).y)<=height)
                {
                    EnList.enemyList.get(i).DieOrAlive(false,damage);
                }
            }
            else continue;
        }
        //切换图片未添加
    }
}