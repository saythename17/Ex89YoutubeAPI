package com.icandothisallday2020.ex89youtubeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

public class MainActivity extends AppCompatActivity {
    //YouTubePlayerView 는 반드시 YouTubeBaseActivity 안에서만 보여짐
    //YouTubeBaseActivity -- androidx. 에 관련한 모든 작업(Support Library 다 안됨) 불가
    //YouTubePlayerView youTubeView;

    YouTubePlayerFragment youTubeFragment;//유튜브뷰를 내부적으로 스스로 갖고 있기에
    //YouTubeBaseActivity 안에서 작업하지 않아도 됨
    YouTubePlayerFragment youTubeFragment2;
    YouTubeThumbnailView thumbnailView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //YouTubePlayerFragment 가 일반 Fragment 이기에 Support 버전을 관리하는 FragmentManager(
        youTubeFragment=(YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtubeFragment);
        youTubeFragment.initialize("first", new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("C13M23AQq4E");//바로 실행
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });

        youTubeFragment2= (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtubeFragment2);
        youTubeFragment2.initialize("second", new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo("xuSve4mLO1Q");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });

        thumbnailView=findViewById(R.id.thumbnail);
        thumbnailView.initialize("third", new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
                youTubeThumbnailLoader.setVideo("xuSve4mLO1Q");
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });

//        youTubeView=findViewById(R.id.youtubeView);
//        youTubeView.initialize("first", new YouTubePlayer.OnInitializedListener() {
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//                //유튜브플레이어뷰는 그냥 영상이 나오는 액자,TV 개념 - 유튜브플레이어가 플레이시킴
//                youTubePlayer.cueVideo(""); //유튜브 동영상 ID<--v= 파라미터 값C13M23AQq4E
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//
//            }
//        });
    }

    public void youtubeData(View view) {
        Intent intent=new Intent(this,YoutubeDataActivity.class);
        startActivity(intent);
    }
}
