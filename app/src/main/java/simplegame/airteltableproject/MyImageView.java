package simplegame.airteltableproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by B0097489 on 2/10/17.
 */

public class MyImageView extends ImageView {

    private Bitmap mBitmap;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void bringToFront() {
        super.bringToFront();
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        mBitmap = bm;
        super.setImageBitmap(bm);
    }
        int count =0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN){
            PorterDuff.Mode mode = null;
            switch (++count){
                case 1:
                    mode = PorterDuff.Mode.CLEAR;
                    break;
                case 2:
                    mode = PorterDuff.Mode.DST;
                    break;
                case 3:
                    mode = PorterDuff.Mode.SRC;
                    break;
                case 4:
                    mode = PorterDuff.Mode.ADD;
                    break;
                case 5:
                    mode = PorterDuff.Mode.MULTIPLY;
                    break;
                case 6:
                    mode = PorterDuff.Mode.DST_ATOP;
                    break;
                case 7:
                    mode = PorterDuff.Mode.DST_OVER;
                    break;
                case 8:
                    mode = PorterDuff.Mode.DST_IN;
                    break;
                case 9:
                    mode = PorterDuff.Mode.DST_OUT;
                    break;
                case 10:
                    mode = PorterDuff.Mode.SRC_ATOP;
                    break;
                case 11:
                    mode = PorterDuff.Mode.SRC_OUT;
                    break;
                case 12:
                    mode = PorterDuff.Mode.SRC_OVER;
                    break;
                case 13:
                    mode = PorterDuff.Mode.SRC_IN;
                    break;
                case 14:
                    mode = PorterDuff.Mode.DARKEN;
                    break;
                case 15:
                    mode = PorterDuff.Mode.LIGHTEN;
                    break;
                case 16:
                    mode = PorterDuff.Mode.SCREEN;
                    break;
                default:
                    count =0;
                    mode= PorterDuff.Mode.MULTIPLY;

            }
            paint.setXfermode(new PorterDuffXfermode(mode));
            paint.setAlpha(100);
        }
        invalidate();
        return true;
    }

    public void setSecondaryImage(Bitmap bitmap) {
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBitmap == null) {
            return;
        }
        canvas.drawBitmap(mBitmap,getMatrix(),paint);
//        canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/2,paint);
    }
}
