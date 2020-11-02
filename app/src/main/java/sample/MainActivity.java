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
        for (int i = 0; i < 12; i++) {
            data.add("");
        }

        PhotoAdapter adapter = new PhotoAdapter(data);
        PhotoLooperView looper = (PhotoLooperView) findViewById(R.id.content);
        looper.setAdapter(adapter);// set a RecyclerView.Adapter
    }
}
