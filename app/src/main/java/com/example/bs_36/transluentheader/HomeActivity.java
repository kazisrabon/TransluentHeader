package com.example.bs_36.transluentheader;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.bs_36.transluentheader.adapter.SongAdapter;
import com.example.bs_36.transluentheader.view.CardView;
import com.example.bs_36.transluentheader.view.NotifyingScrollView;
import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.mikepenz.materialdrawer.util.KeyboardUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BS-36 on 4/6/2015.
 */
public class HomeActivity extends Activity {

    private AccountHeader.Result headerResult;
    private Drawer.Result result = null;
    Animation animBlink, animFadeIn, animFadeOut, animRotate, animSequential;
    ImageView imageStar1, imageStar2, imageStar3, imageStar4, imageStar5, imageStar6, imageStar7, imageStar8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        animBlink = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);
        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_out);
        animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.rotate);
        animSequential = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.sequential);
        imageStar6 = (ImageView)findViewById(R.id.imageView6);
        imageStar6.startAnimation(animFadeOut);
        imageStar2 = (ImageView)findViewById(R.id.imageView2);
        imageStar2.startAnimation(animFadeIn);
        imageStar3 = (ImageView)findViewById(R.id.imageView3);
        imageStar3.startAnimation(animRotate);
        imageStar4 = (ImageView)findViewById(R.id.imageView4);
        imageStar4.startAnimation(animRotate);
        imageStar5 = (ImageView)findViewById(R.id.imageView5);
        imageStar5.startAnimation(animBlink);
        imageStar1 = (ImageView)findViewById(R.id.imageView);
        imageStar1.startAnimation(animSequential);
        imageStar7 = (ImageView)findViewById(R.id.imageView7);
        imageStar7.startAnimation(animFadeOut);
        imageStar8 = (ImageView)findViewById(R.id.imageView8);
        imageStar8.startAnimation(animBlink);

        animFadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageStar2.startAnimation(animFadeOut);
                imageStar7.startAnimation(animFadeOut);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animFadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageStar6.startAnimation(animFadeIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animSequential.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageStar1.startAnimation(animSequential);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        headerResult = new AccountHeader()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName("Name").withEmail("mail@gmail.com").withIcon(getResources().getDrawable(R.drawable.profile4))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        result = new Drawer()
                .withActivity(this)
                .withHeader(R.layout.header)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_home).withIcon(FontAwesome.Icon.faw_home).withIdentifier(1),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_free_play).withIcon(FontAwesome.Icon.faw_gamepad).withIdentifier(2),
                        new SectionDrawerItem().withName(R.string.drawer_item_section_header),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_settings).withIcon(FontAwesome.Icon.faw_cog).withIdentifier(3)
                )
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        Toast.makeText(HomeActivity.this, "onDrawerOpened", Toast.LENGTH_SHORT).show();
                        KeyboardUtil.hideKeyboard(HomeActivity.this);
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        Toast.makeText(HomeActivity.this, "onDrawerClosed", Toast.LENGTH_SHORT).show();
                    }
                })
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {

                        if (drawerItem != null && drawerItem instanceof Nameable) {
                            getActionBar().setTitle(((Nameable) drawerItem).getNameRes());

                            if (drawerItem.getIdentifier() == 1) {
                                startActivity(new Intent(getBaseContext(), MusicFragment.class));
//                                Toast.makeText(getBaseContext(), "Nothing New", Toast.LENGTH_SHORT).show();
                            } else if (drawerItem.getIdentifier() == 2) {
                                startActivity(new Intent(getBaseContext(), GraphFragment.class));
                            } else if (drawerItem.getIdentifier() == 3) {
                                startActivity(new Intent(getBaseContext(), BarChartActivity.class));
                            }
                        }
                    }
                })
                .withFireOnInitialOnClick(true)
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(0)
                .build();

//        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);

    }
}
