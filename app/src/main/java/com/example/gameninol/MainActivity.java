package com.example.gameninol;

import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView[] theimagesList;
    private Integer[] theImageButtons = {
            R.drawable.gangnoel,
            R.drawable.happynoel,
            R.drawable.sleepynoel,
            R.drawable.gangnoel,
            R.drawable.happynoel,
            R.drawable.sleepynoel,
    };

    private int nums = 0;
    private int indexOne, indexTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set ImageViews
        theimagesList = new ImageView[6];
        theimagesList[0] = findViewById(R.id.image1);
        theimagesList[1] = findViewById(R.id.image2);
        theimagesList[2] = findViewById(R.id.image3);
        theimagesList[3] = findViewById(R.id.image4);
        theimagesList[4] = findViewById(R.id.image5);
        theimagesList[5] = findViewById(R.id.image6);

        //Shuffle Set Image
        List<Integer> imgRsLst = Arrays.asList(theImageButtons);
        Collections.shuffle(imgRsLst);
        imgRsLst.toArray(theImageButtons);

    }

    public void onImageClick(View view) {
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            int index = getIndexFromView(imageView);

            if (imageView.getTag() == null) {
                if (nums == 0) {
                    // First click
                    imageView.setImageResource(theImageButtons[index]);
                    imageView.setTag(theImageButtons[index]);
                    indexOne = index;
                    nums++;
                } else if (nums == 1) {
                    // Second click
                    imageView.setImageResource(theImageButtons[index]);
                    imageView.setTag(theImageButtons[index]);
                    indexTwo = index;

                    if (theImageButtons[indexOne].equals(theImageButtons[indexTwo])) {
                        // Match found
                        Toast.makeText(this, "Match found!", Toast.LENGTH_SHORT).show();
                        nums = 0;
                    } else {
                        // No match
                        Toast.makeText(this, "No match!", Toast.LENGTH_SHORT).show();

                        // Delay flipping the cards back
                        imageView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ImageView firstImageView = theimagesList[indexOne];
                                ImageView secondImageView = theimagesList[indexTwo];
                                firstImageView.setImageResource(R.drawable.ic_launcher_foreground);
                                firstImageView.setTag(null);
                                secondImageView.setImageResource(R.drawable.ic_launcher_foreground);
                                secondImageView.setTag(null);
                                nums = 0;
                            }
                        }, 1000);
                    }
                }
            }
        }
    }

    private int getIndexFromView(ImageView imageView) {
        for (int i = 0; i < theimagesList.length; i++) {
            if (theimagesList[i] == imageView) {
                return i;
            }
        }
        return -1;
    }
}