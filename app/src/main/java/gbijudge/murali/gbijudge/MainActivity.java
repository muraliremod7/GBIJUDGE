package gbijudge.murali.gbijudge;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import gbijudge.murali.gbijudge.Services.SessionManager;
import gbijudge.murali.gbijudge.fragments.IdeasFragment;
import gbijudge.murali.gbijudge.fragments.JudgesFragment;
import gbijudge.murali.gbijudge.fragments.MentorsFragment;

public class MainActivity extends AppCompatActivity {
    private SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new SessionManager(getApplicationContext());
        if(session.checkLogin())
            finish();
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                Fragment fragment = null;
                Class fragmentClass = null;
                switch (tabId){
                    case R.id.home:
                        fragmentClass = IdeasFragment.class;
                        break;
                    case R.id.mylist:
                        fragmentClass = JudgesFragment.class;
                        break;
                    case R.id.trending:
                        fragmentClass = MentorsFragment.class;
                        break;
                }
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.contentContainer, fragment).commit();
            }
        });
    }
}
