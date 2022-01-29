package com.example.android.votingiva;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class HomeActivity extends AppCompatActivity {
    // variable
    MeowBottomNavigation bottomNavigation;
    //receiving data from LoginActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //email is received from the previous intent
        String email=getIntent().getStringExtra("email");//here

        Bundle bundle=new Bundle();
        bundle.putString("email",email);
        Bundle bundle2=new Bundle();
        bundle2.putString("email",email);
        // Assign
        bottomNavigation = findViewById(R.id.bottom_navigation);
        // menu
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_notification));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_info));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                // Initialize fragments
                Fragment fragment = null;
                switch(item.getId()){
                    case 1:
                        fragment = new NotificationFragment();
                        break;
                    case 2:
                        fragment = new HomeFragment();
                        fragment.setArguments(bundle2);
                        break;
                    case 3:
                        fragment = new AboutFragment();
                        fragment.setArguments(bundle);
                        break;
                }
                loadFragment(fragment);
            }
        });
        bottomNavigation.setCount(1,null); // count
        bottomNavigation.show(2,true); // home fragment initially
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
//                Toast.makeText(getApplicationContext()
//                ,"You clicked" + item.getId()
//                ,Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
//                Toast.makeText(getApplicationContext()
//                        ,"You Reselected" + item.getId(),
//                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();
    }
}