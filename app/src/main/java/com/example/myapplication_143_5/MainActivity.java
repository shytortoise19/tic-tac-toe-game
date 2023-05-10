package com.example.myapplication_143_5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int x=1;
    int y=0;
    boolean winner=false;
    int [][]win={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int []game={0,0,0,0,0,0,0,0,0};
    public void load(View view)
    {
        y++;
        ImageView v=(ImageView) view;
        int tag=Integer.parseInt(v.getTag().toString());
        if(game[tag]!=0 && !winner)
            Toast.makeText(this,"Choose different location",Toast.LENGTH_SHORT).show();
        else
        {
            game[tag]=x;
            if (x==1)
            {
                v.setImageResource(R.drawable.img_3);
                x=2;
            }
            else {
                v.setImageResource(R.drawable.img_2);
                x = 1;
            }
            int c=check_win();
            if(c!=-1)
            {
                Toast.makeText(this,"PLAYER "+((x==2)?1:2)+" WON",Toast.LENGTH_SHORT).show();
                winner=true;
                //(new Handler()).postDelayed(this::restart(), 5000);
                restart();
            }
            if(y==9)
            {
                Toast.makeText(this,"Draw.. Restarting",Toast.LENGTH_SHORT).show();
                restart();
            }
        }
    }
    int check_win()
    {
        for(int i=0;i<8;i++)
        {
            int x=win[i][0],y=win[i][1],z=win[i][2];
            if(game[x]==game[y] && game[x]==game[z] && game[x]!=0 )
                return game[win[i][0]];
        }
        return -1;
    }
    public void restart()
    {
        androidx.gridlayout.widget.GridLayout gridLayout=findViewById(R.id.gridLayout);
        for(int i=0;i<9;i++)
        {
            ImageView v=(ImageView) gridLayout.getChildAt(i);
            v.setImageDrawable(null);
            game[i]=0;
        }
        x=1;
        winner=false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}