package dev.ayham.com.materialsearchtoolbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

/**
 * Created by dev on 6/24/17.
 */

public class UIUtils {


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void circleReveal(Activity activity, int viewID, int posFromRight, final boolean isShow) {
        final View myView = activity.findViewById(viewID);

        int width = myView.getWidth();

        width -= posFromRight;

        int cx = width;
        int cy = myView.getHeight() / 2;

        Animator anim;
        if (isShow)
            anim = android.view.ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, width);
        else
            anim = android.view.ViewAnimationUtils.createCircularReveal(myView, cx, cy, width, 0);

        anim.setDuration(220);

        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (!isShow) {
                    super.onAnimationEnd(animation);
                    myView.setVisibility(View.INVISIBLE);
                }
            }
        });

        // make the view visible and start the animation
        if (isShow)
            myView.setVisibility(View.VISIBLE);

        // start the animation
        anim.start();
    }

}
