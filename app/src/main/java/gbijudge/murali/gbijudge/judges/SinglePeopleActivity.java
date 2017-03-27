package gbijudge.murali.gbijudge.judges;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import gbijudge.murali.gbijudge.R;


public class SinglePeopleActivity extends AppCompatActivity {
    private Toolbar toolbar;
    TextView judgename, companyname, mobile, designation, judgeemail;
    public Bundle getBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_people);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        judgename = (TextView)findViewById(R.id.judgename);
        companyname = (TextView)findViewById(R.id.companyname);
        designation = (TextView)findViewById(R.id.designation);
        mobile = (TextView)findViewById(R.id.Phonenumber);
        judgeemail = (TextView)findViewById(R.id.profileEmail);

        getBundle = this.getIntent().getExtras();
        String Leadname = getBundle.getString("ProfileName");
        String ideaName = getBundle.getString("companyname");
        String ideaDesc = getBundle.getString("designation");
        String Phone = getBundle.getString("mobile");
        String Email = getBundle.getString("Email");
        judgename.setText(Leadname);
        companyname.setText(ideaName);
        designation.setText(ideaDesc);
        mobile.setText(Phone);
        judgeemail.setText(Email);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
