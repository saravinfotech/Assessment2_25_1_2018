package uitestapp.com.uitestapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Main Activity used to load the fragments
 */
public class MainActivity extends AppCompatActivity {

    FragmentManager objFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null)
            attachScreen1Fragment();
    }

    /**
     * Base screen when the activity is initialized
     */
    private void attachScreen1Fragment() {
        Screen1 screen1 = new Screen1();
        objFragmentManager = getSupportFragmentManager();
        objFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, screen1)
                .commit();
    }

    /**
     * Common method for navigating between the fragments
     * @param value Value to be passed between fragments
     * @param fragment Fragment to which the user is to be navigated
     * @param flag Flag to determine if the fragment should be added to back stack.
     */
    public void customFragmentTransaction(int value, Fragment fragment, boolean flag) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.FRAGMENT_BUNDLE, value);
        fragment.setArguments(bundle);
        if (flag)
            objFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .addToBackStack(null)
                    .commit();
        else
            objFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .commit();
    }
}
