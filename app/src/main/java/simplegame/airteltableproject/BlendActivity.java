package simplegame.airteltableproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class BlendActivity extends AppCompatActivity implements View.OnClickListener {


    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blend);

        imageView = (ImageView) findViewById(R.id.iv_blend);

        imageView.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        Bitmap back = Util.setBlendMode(this,R.drawable.bg,PorterDuff.Mode.SCREEN);
        Bitmap front = BitmapFactory.decodeResource(getResources(), R.drawable.front);
        Bitmap folder = Util.combineImages(back, front);

        Bitmap folderBlend = Util.setBlendMode(folder, PorterDuff.Mode.MULTIPLY);
        imageView.setImageBitmap(folder);

    }
}
