package com.google.firebase.assignment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    RecyclerView recyclerView2;
    private RecyclerView.Adapter m2Adapter;


    ArrayList<DataSource> dataSources;

    String URL_CODES="http://demo8716682.mockable.io/cardData";

    ArrayList<String> allNames,allAge,allLoc;

    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        dataSources=new ArrayList<>();


        allNames=new ArrayList<>();
        allAge=new ArrayList<>();
        allLoc=new ArrayList<>();


        recyclerView=findViewById(R.id.recycleview);



        new FetchData().execute(URL_CODES);


    }

    public void setData(String result){

        try {

            JSONArray array = new JSONArray(result);    // result is whole json file

            for(int i=0;i<array.length();i++) {

                JSONObject obj = array.getJSONObject(i);

                allNames.add( obj.getString("name"));
                allAge.add(obj.getString("age"));
                allLoc.add(obj.getString("location"));

                new ImageLoadTask().execute(obj.getString("url"));
            }

        }catch (Exception e){}

    }



    //inner class

    public class FetchData extends AsyncTask<String,Void,String> {


        @Override
        protected String doInBackground(String... urls) {

            if((urls.length<1)||(urls[0]==null)){
                return null;
            }

            try {
                return Utils.getJSonString(urls[0]);
            }catch (IOException e){}

            return "error";
        }

        @Override
        protected void onPostExecute(String s) {
            if(s==null){
                return;
            }
          setData(s);
        }


    }

    // inner class

    public class ImageLoadTask extends AsyncTask<String,Void,Bitmap> {

        @Override
        public Bitmap doInBackground(String... urls) {

            Bitmap bitmap=null;
            try{

                    InputStream inputStream = new java.net.URL(urls[0]).openStream();
                    bitmap = BitmapFactory.decodeStream(inputStream);


            } catch (Exception e){}

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            dataSources.add(new DataSource(allNames.get(i),allAge.get(i),allLoc.get(i),bitmap));
            i++;

            mAdapter=new MyAdapter(dataSources,getApplicationContext());
            recyclerView.setAdapter(mAdapter);





        }
    }
}
