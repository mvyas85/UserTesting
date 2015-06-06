package com.usertesting.mvyas.usertesting;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TestsListAdapter extends ArrayAdapter<JASONData> {
    final Context mContext;

    public TestsListAdapter(Context context, ArrayList<JASONData> tasks) {
        super(context, R.layout.disabled_list_item, tasks);
        mContext = context;
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent) {
        final JASONData data = getItem(position);
        TextView title, text1, text2, decline;
        Button acceptBtn ;

        String stateStr = data.getState(), line1text,line2text;

        String referenceStr = data.getReference_id();

        if (convertView == null) {
            if (stateStr.equals("reserved")) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.disabled_list_item, parent, false);
                title = (TextView) convertView.findViewById(R.id.disableTitle);
                text1 = (TextView) convertView.findViewById(R.id.disableText);
                text2 = (TextView) convertView.findViewById(R.id.disableText2);
                line1text = getContext().getResources().getString(R.string.reserved_test)+ " "+ referenceStr;
                line2text =  getContext().getResources().getString(R.string.need_assistance);
            } else {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.enabled_list_item, parent, false);
                title = (TextView) convertView.findViewById(R.id.enableTitle);
                text1 = (TextView) convertView.findViewById(R.id.enableText);
                text2 = (TextView) convertView.findViewById(R.id.enableText2);

                decline = (TextView) convertView.findViewById(R.id.decline);
                acceptBtn = (Button) convertView.findViewById(R.id.accbutton);
                final String question,ans1,ans2,wrong_right1,wrong_right2;
                if((data.getScreener()!=null)){
                    acceptBtn.setText(getContext().getResources().getString(R.string.take_screener));
                    acceptBtn.setWidth(450);

                    if(data.getScreener().getNext_question() != null){
                        question = data.getScreener().getNext_question().getQuestion();
                        ans1 = data.getScreener().getNext_question().getAnswer(0).getAns_text();
                        ans2 = data.getScreener().getNext_question().getAnswer(1).getAns_text();
                        wrong_right1 = data.getScreener().getNext_question().getAnswers()[0].getRight_wrong();
                        wrong_right2 =data.getScreener().getNext_question().getAnswers()[1].getRight_wrong();
                    }else {
                        question="";ans1="";ans2="";wrong_right1="";wrong_right2="";
                    }

                }else {
                    question="";ans1="";ans2="";wrong_right1="";wrong_right2="";
                }
                line1text = getContext().getResources().getString(R.string.requirments);
                line2text =getContext().getResources().getString(R.string.anyone) + " " + referenceStr;

                decline.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity.listAdapter.remove(data);
                        MainActivity.taskList.setAdapter(MainActivity.listAdapter);
                        MainActivity.listAdapter.notifyDataSetChanged();
                    }
                });

                acceptBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String captionBtn = ((Button) v).getText().toString();

                        if (captionBtn.equals(getContext().getResources().getString(R.string.take_screener))) {

                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle(question);
                            final String[] items = new String[]{ans1, ans2};

                            final String[] answers = new String[]{wrong_right1, wrong_right2};
                            builder.setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getContext(), items[which] + " Were chosen"
                                            +"\nANSWER: "+ answers[which], Toast.LENGTH_LONG).show();
                                    dialog.dismiss();
                                }
                            });
                            builder.show();
                        } else {
                            Intent intent = new Intent(getContext(), SecondActivity.class);
                            intent.putExtra("dataForSecondActivity", data);
                            getContext().startActivity(intent);
                        }
                    }
                });
            }
            title.setText(stateStr);
            text1.setText(line1text);
            text2.setText(line2text);
        }
        return convertView;
    }
}