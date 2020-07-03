package com.icandothisallday2020.ex89youtubeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class YoutubeDataActivity extends AppCompatActivity {
    EditText et;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_data);

        et=findViewById(R.id.et);
        tv=findViewById(R.id.tv);
    }

    public void search(View view) {
        //YouTube Data API 사용
        // 검색 기능 API- REST 방식(@GET,@POST)으로 데이터(json) 제공
        // GET https://www.googleapis.com/youtube/v3/search
        //요청 파라미터 :key(필수), part(필수), q(검색어),maxResults(결과 개수 지정 : 0~50)

        String key="AIzaSyDF8TiHRhFvSxZlhfWLqAxJCE13bsOoYKI",part="snippet",q=et.getText().toString();
        int maxResults=10;

        //Retrofit - 결과를 스트링으로 받을 것
        Retrofit retrofit=RetrofitHelper.getInstance();
        RetrofitService service=retrofit.create(RetrofitService.class);
        Call<String> call=service.searchVideos(key,part,q,maxResults);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String data=response.body();
                tv.setText(data);
                Toast.makeText(YoutubeDataActivity.this, "success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(YoutubeDataActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
