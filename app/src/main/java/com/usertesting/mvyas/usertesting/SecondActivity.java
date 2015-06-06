package com.usertesting.mvyas.usertesting;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class SecondActivity extends ActionBarActivity {

    JASONData data;
    TextView id,  state,operating_systems, introduction,reference_id, screener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        data = (JASONData)intent.getSerializableExtra("dataForSecondActivity");

        id = (TextView) findViewById(R.id.ID);
        state = (TextView) findViewById(R.id.state);
        operating_systems = (TextView) findViewById(R.id.os);
        introduction = (TextView) findViewById(R.id.introduction);
        reference_id = (TextView) findViewById(R.id.reference_id);

        id.setText(data.getId());
        state.setText( data.getState());

        String str = "";
        for(int i =0;i<data.getOperating_systems().length;i++){
            str += (i+1) + "." +data.getOperating_system(i)+"\n";
        }
        operating_systems.setText(str);
        introduction.setText( data.getIntroduction());
        reference_id.setText( data.getReference_id());
        //screener.setText( data.getScreener());


    }


}
