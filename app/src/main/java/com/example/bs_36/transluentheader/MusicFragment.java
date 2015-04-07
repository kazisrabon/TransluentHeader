package com.example.bs_36.transluentheader;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Toast;


import com.example.bs_36.transluentheader.adapter.SongAdapter;
import com.example.bs_36.transluentheader.view.CardView;
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
 * Created by BS-36 on 3/20/2015.
 */
public class MusicFragment extends Activity {

//    LinearLayout linearLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    List<CardView> cardViews;
    private AccountHeader.Result headerResult;
    private Drawer.Result result = null;

    android.support.v7.widget.CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_music);
        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        cardView = (android.support.v7.widget.CardView)mRecyclerView.findViewById(R.id.card_view);
        cardViews = new ArrayList<CardView>();
        cardViews.add(new CardView("Song 1"));
        cardViews.add(new CardView("Song 2"));
        cardViews.add(new CardView("Song 3"));

        mAdapter = new SongAdapter(cardViews);
        mRecyclerView.setAdapter(mAdapter);

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
                        Toast.makeText(MusicFragment.this, "onDrawerOpened", Toast.LENGTH_SHORT).show();
                        KeyboardUtil.hideKeyboard(MusicFragment.this);
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        Toast.makeText(MusicFragment.this, "onDrawerClosed", Toast.LENGTH_SHORT).show();
                    }
                })
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {

                        if (drawerItem != null && drawerItem instanceof Nameable) {
                            getActionBar().setTitle(((Nameable) drawerItem).getNameRes());

                            if (drawerItem.getIdentifier() == 1) {
//                                startActivity(new Intent(getBaseContext(), MusicFragment.class));
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
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
