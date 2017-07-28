package sample;

import android.app.Activity;
import android.os.Bundle;

import com.example.kenzhang.photorecycler.R;
import com.ftc.kenzhang.photolooper.PhotoAdapter;
import com.ftc.kenzhang.photolooper.PhotoLooperView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            data.add("");
        }

        PhotoAdapter adapter = new PhotoAdapter(data);
        PhotoLooperView looper = (PhotoLooperView) findViewById(R.id.content);
        looper.setAdapter(adapter);// set a RecyclerView.Adapter


        //call these method before you call setAdapter
        looper.setThreshold(0.6f);//Persent of width.When over the threshold , the drag view will be recycled.
        looper.setScaleGap(0.03f);//Scale rate between each item
        looper.setTransYGAP(40);//TransY betweem each item.
        looper.setShowCount(4);//display view count
    }
}
