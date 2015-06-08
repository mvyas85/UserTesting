package com.usertesting.mvyas.usertesting;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends Activity {

    public static ListView taskList;
    public static TestsListAdapter listAdapter ;
    public static ArrayList<JASONData> list;

    static URLReader urlr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlr = new URLReader(this);
        urlr.execute();

        taskList = (ListView)findViewById(R.id.mainListView);

        list = new ArrayList<JASONData>();
        listAdapter = new TestsListAdapter(this, list);

        listAdapter.notifyDataSetChanged();
    }

    public static void setupListAdapter(){

        for(int i =0;i< urlr.allData.size();i++){
            boolean isOSAndroid = false;
            for(int j= 0; j<urlr.allData.get(i).getOperating_systems().length;j++){
                isOSAndroid = false;

               if(urlr.allData.get(i).getOperating_system(j).equals("android"))
                {
                    isOSAndroid = true;
                    break;
                }
            }
            if(isOSAndroid)
                list.add(urlr.allData.get(i));
        }
        taskList.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
    }



}
