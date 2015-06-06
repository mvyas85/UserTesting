package com.usertesting.mvyas.usertesting;

/**
 * Created by manisha.vyas on 6/5/15.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by manisha.vyas on 6/5/15.
 *
 */


public class URLReader extends AsyncTask<String, Void, Void> {


    String SampleJSONURL ="https://s3-us-west-1.amazonaws.com/candidate-test/sample_json";
    Context mContext;

    String mfileName = "";
    static JSONObject jObj = null;

    ArrayList<JASONData> allData = new ArrayList<JASONData>();

    URLReader(Context context){
        mContext = context;
       // allData = new AllData();

    }

    public void DownloadFromUrl(String DownloadUrl, String fileName) {


        mfileName = fileName;
        try {
            // Create a URL for the desired page
            URL url = new URL(SampleJSONURL);

            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String str;
            while ((str = in.readLine()) != null) {

                JSONArray obj = new JSONArray(str);

                for (int i = 0; i < obj.length(); i++) {

                    JSONObject aTest = obj.getJSONObject(i);
                    JSONObject screenerArr = null;
                    if(!aTest.isNull("screener") )
                        screenerArr = aTest.getJSONObject("screener");

                    Screener newScreener= null;
                    if( screenerArr != null) {
                        Log.d("MRV=========>","not nill"+aTest.getString("reference_id"));
                        if(!screenerArr.isNull("next_question") ){

                            Log.d("MRV===22====>","not null"+aTest.getString("reference_id"));
                            JSONObject next_question = screenerArr.getJSONObject("next_question");
                            JSONArray ans_obj = next_question.getJSONArray("answers");

                            String ans_text = null;
                            String ans_acceptable = null;

                            Answers ans_group[] = new Answers[ans_obj.length()];
                            for (int j = 0; j < ans_obj.length(); j++) {
                                ans_text = ans_obj.getJSONObject(j).getString("text");
                                ans_acceptable = ans_obj.getJSONObject(j).getString("acceptable");

                                ans_group[j] = new Answers(ans_text, ans_acceptable);
                            }
                            NextQuestion nq = new NextQuestion(next_question.getString("question"), ans_group);
                            newScreener = new Screener(screenerArr.getString("id"), nq);
                        }
                    }
                    JSONArray os_array_obj = aTest.getJSONArray("operating_systems");

                    String[] os_array = new String[os_array_obj.length()];
                    for(int j =0;j<os_array_obj.length();j++){
                        os_array[j] = os_array_obj.getString(j);
                    }

                    JASONData data = new JASONData(aTest.getString("id")
                            ,aTest.getString("state")
                            ,os_array
                            ,aTest.getString("introduction")
                            ,aTest.getString("reference_id")
                            ,newScreener);

                    allData.add(data);
                }
            }
            in.close();
        } catch (JSONException e){
            e.printStackTrace();
        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
    @Override
    protected Void doInBackground(String... params) {
        DownloadFromUrl(SampleJSONURL, "storedJASON");
        return null;
    }

    @Override
    protected void onPostExecute(Void x) {
        MainActivity.setupListAdapter();
    }
}
