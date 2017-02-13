package simplegame.airteltableproject;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView mContainer, mCard1, mCard2;
    MyImageView mOptionImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContainer = (ImageView) findViewById(R.id.main_option);
        mCard1 = (ImageView) findViewById(R.id.card_first);
        mCard2 = (ImageView) findViewById(R.id.card_secound);
        mOptionImage = (MyImageView) findViewById(R.id.option);
        mContainer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_option:
                animateCards();
                break;
            case R.id.card_first:
                firstCardClick();
                break;
            case R.id.card_secound:
                secondCardClick();
                break;
        }
    }

    private void animateCards() {
        animateFirstCard();
        animateSecondCard();
    }

    private void animateFirstCard() {
        Animator animator1 = AnimatorInflater.loadAnimator(this,R.animator.first_card_up_anim);
        animator1.addListener(new AnimatorListenerImplement() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mCard1.bringToFront();
                mContainer.setClickable(false);
                mContainer.setOnClickListener(null);
                mCard1.setClickable(true);
                mCard1.setOnClickListener(MainActivity.this);
            }
        });
        Animator animator2 = AnimatorInflater.loadAnimator(this,R.animator.first_card_down_anim);
        animator1.setTarget(mCard1);
        animator2.setTarget(mCard1);
        AnimatorSet set = new AnimatorSet();
        set.playSequentially(animator1,animator2);
        set.start();
    }

    private void animateSecondCard() {
        Animator animator1 = AnimatorInflater.loadAnimator(this,R.animator.second_card_down_anim);
        animator1.addListener(new AnimatorListenerImplement() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mCard2.bringToFront();
                mCard2.setClickable(true);
                mCard2.setOnClickListener(MainActivity.this);
                mContainer.setClickable(false);
                mContainer.setOnClickListener(null);
            }
        });
        Animator animator2 = AnimatorInflater.loadAnimator(this,R.animator.second_card_up_anim);
        animator1.setTarget(mCard2);
        animator2.setTarget(mCard2);
        AnimatorSet set = new AnimatorSet();
        set.playSequentially(animator1,animator2);
        set.start();
    }

    private void firstCardClick() {
        Animator primaryCard = AnimatorInflater.loadAnimator(this,R.animator.card_selection_anim);
        primaryCard.setTarget(mCard1);
        primaryCard.setDuration(1500);
        Animator categoryCard = AnimatorInflater.loadAnimator(this,R.animator.card_selection_anim);
        categoryCard.setTarget(mContainer);
        categoryCard.setDuration(1000);
        Animator secondarycard = AnimatorInflater.loadAnimator(this,R.animator.card_selection_anim);
        secondarycard.setTarget(mCard2);
        secondarycard.setDuration(1300);
        ObjectAnimator primaryTranslationX = ObjectAnimator.ofFloat(mCard1,"translationX",-10);
        ObjectAnimator primaryTranslationY = ObjectAnimator.ofFloat(mCard1,"translationY",10);
        ObjectAnimator secondaryTranslationX = ObjectAnimator.ofFloat(mCard2,"translationX",10);
        ObjectAnimator secondaryTranslationY = ObjectAnimator.ofFloat(mCard2,"translationY",-10);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(categoryCard,secondarycard,primaryTranslationX,primaryTranslationY,secondaryTranslationX,secondaryTranslationY,primaryCard);
        set.start();
        set.addListener(new AnimatorListenerImplement() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mCard1.setVisibility(GONE);
                mCard2.setVisibility(GONE);
                mContainer.setVisibility(GONE);
                loadFirstCardOptions();
            }
        });
    }

    private void secondCardClick() {

    }

    private void loadFirstCardOptions() {
        mOptionImage.setAlpha(1.0f);
        mOptionImage.setVisibility(View.VISIBLE);
        mOptionImage.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.bg));
        mOptionImage.setSecondaryImage(BitmapFactory.decodeResource(getResources(),R.drawable.option));

        Animator animator = AnimatorInflater.loadAnimator(this,R.animator.load_options_anim);
        animator.setTarget(mOptionImage);
        animator.start();
    }

}
