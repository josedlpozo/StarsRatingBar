package josedlpozo.com.ratingbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StarsRatingBar ratingBar = (StarsRatingBar) findViewById(R.id.my_rating_bar);

        StarsRatingBar ratingBar2 = (StarsRatingBar) findViewById(R.id.my_rating_bar2);

        StarsRatingBar ratingBar3 = (StarsRatingBar) findViewById(R.id.my_rating_bar3);

        StarsRatingBar.StarsRatingBarListener listener = new StarsRatingBar.StarsRatingBarListener() {
            @Override
            public void rateChanged(float score) {
                Log.d("RATE", ""+score);
            }
        };
        
        ratingBar.setStarsRatingBarListener(listener);
        ratingBar2.setStarsRatingBarListener(listener);
        ratingBar3.setStarsRatingBarListener(listener);

        ratingBar2.setIconStarEmpty(R.drawable.icon_star_purple_empty);
        ratingBar2.setIconStarFull(R.drawable.icon_star_purple_full);
        ratingBar2.setIconStarHalf(R.drawable.icon_star_purple_half);
        ratingBar2.refresh();

        ratingBar.setIndicator(false);
        ratingBar.setStepByHalf(true);

        ratingBar2.setIndicator(true);
        ratingBar2.setStepByHalf(true);
        ratingBar2.setCurrentRate(1.5f);
        ratingBar2.setNumStars(25);
        ratingBar2.refresh();

        ratingBar3.setIndicator(false);
        ratingBar3.setStepByHalf(false);
        ratingBar3.setIconStarEmpty(R.drawable.icon_star_yellow_empty);
        ratingBar3.setIconStarFull(R.drawable.icon_star_yellow_full);
        ratingBar3.setIconStarHalf(R.drawable.icon_star_yellow_half);
        ratingBar3.refresh();

    }
}
