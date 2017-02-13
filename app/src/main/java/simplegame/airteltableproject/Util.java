package simplegame.airteltableproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;

/**
 * Created by ankitgarg on 13/02/17.
 */

public class Util {

    public static Bitmap combineImages(Bitmap bitmapBack, Bitmap bitmapFront) {
        Bitmap cs = null;
        int width, height = 0;
        width = bitmapBack.getWidth() >= bitmapFront.getWidth() ? bitmapBack.getWidth() : bitmapFront.getWidth();
        height = bitmapBack.getHeight() >= bitmapFront.getHeight() ? bitmapBack.getHeight() : bitmapFront.getHeight();
        cs = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas comboImage = new Canvas(cs);
        comboImage.drawBitmap(bitmapBack, 0f, 0f, null);
        comboImage.drawBitmap(bitmapFront, (width - bitmapFront.getWidth()) / 2, 0f, null);
        return cs;
    }

    public static Bitmap combineImages(Context context, int backId, int frontId) {
        Bitmap bitmapBack = BitmapFactory.decodeResource(context.getResources(), backId);
        Bitmap bitmapFront = BitmapFactory.decodeResource(context.getResources(), frontId);
        return combineImages(bitmapBack, bitmapFront);
    }

    public static Bitmap setBlendMode(Bitmap bitmap, PorterDuff.Mode mode) {
        Bitmap cs = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(mode));
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        Canvas canvas = new Canvas(cs);
        canvas.drawBitmap(bitmap, 0f, 0f, paint);
        return cs;
    }

    public static Bitmap setBlendMode(Context context, int resId, PorterDuff.Mode mode) {
        return setBlendMode(BitmapFactory.decodeResource(context.getResources(), resId), mode);
    }
}
