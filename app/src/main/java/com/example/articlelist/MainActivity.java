package com.example.articlelist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lv_form;
    private ArrayList<NewsInfo> arrayList;
    private Context mContext;
    private NewAdapter Adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fillData();
    }

    private void initView(){
        lv_form=(ListView) findViewById(R.id.lv_form);
    }
    private void fillData(){
        new Thread(){
            private HttpURLConnection conn;
            @Override
            public void run(){
                String path="https://www.wanandroid.com/article/list/0/json";
                try{
                    URL url=new URL(path);
                    conn=(HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(5000);
                    InputStream is =conn.getInputStream();
                    String ret =is2String(is);
                    //System.out.println(ret);
                    arrayList=parseArrayList(ret);
                    for(NewsInfo a:arrayList){
                        a.toString();
                        //System.out.println(a.toString());
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mContext=MainActivity.this;
                            Adapter=new NewAdapter((ArrayList<NewsInfo>)arrayList,mContext);
                            lv_form.setAdapter(Adapter);
                            lv_form.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    Intent intent=new Intent(MainActivity.this, Linkactivity.class);
                                    intent.putExtra("link",arrayList.get(i).getLink());
                                    startActivity(intent);
                                }
                            });
                        }
                    });

                }catch (Exception e){
                    e.printStackTrace();
                }
                conn.disconnect();
            }

        }.start();
    }

    private String is2String(InputStream inputStream0)throws IOException{
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        byte[] buffer =new byte[1024];
        int len=0;
        while((len=inputStream0.read(buffer))!=-1){
            os.write(buffer,0,len);
        }
        inputStream0.close();
        return new String(os.toByteArray(),"UTF-8");
    }

    private ArrayList<NewsInfo> parseArrayList(String s){
        ArrayList<NewsInfo> list=new ArrayList<>();
        NewsInfo newsInfo;
        try{
            JSONObject object=new JSONObject(s);
            int errCode=object.optInt("errorcode");
            if(errCode==0){
                JSONArray array=object.optJSONObject("data").optJSONArray("datas");
                for(int i=0;i<array.length();i++){
                    newsInfo=new NewsInfo();
                    JSONObject obj=array.optJSONObject(i);
                    newsInfo.setId(obj.optInt("id"));
                    newsInfo.setTitle(obj.optString("title"));
                    newsInfo.setChapterName(obj.optString("chapterName"));
                    newsInfo.setShareUser(obj.optString("shareUser"));
                    newsInfo.setNiceDate(obj.optString("niceDate"));
                    newsInfo.setLink(obj.optString("link"));
                    newsInfo.setCollcet(obj.optBoolean("collect"));
                    list.add(newsInfo);


                }
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;

    }
}