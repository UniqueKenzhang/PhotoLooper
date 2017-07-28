package sample;

import android.app.Activity;
import android.os.Bundle;

import com.example.kenzhang.photorecycler.R;
import com.ftc.kenzhang.photolooper.PhotoPagerAdapter;
import com.ftc.kenzhang.photolooper.PhotoRecyclerView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private PhotoPagerAdapter mPhotoPagerAdapter;
    private PhotoRecyclerView mPhotoPager;
    private ArrayList<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mData = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            mData.add("");
        }

        mPhotoPagerAdapter = new PhotoPagerAdapter(mData);
        mPhotoPager = (PhotoRecyclerView) findViewById(R.id.content);
        mPhotoPager.setAdapter(mPhotoPagerAdapter);
    }
}
