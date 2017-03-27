package gbijudge.murali.gbijudge.judges;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import gbijudge.murali.gbijudge.R;


/**
 * Created by Hari Prahlad on 07-05-2016.
 */
public class JudgesListRow extends BaseAdapter {
    TextView username, mobile,comapny,desig,email;
    ImageView profilepic;
    private final Activity activity;
    public List<JudgesCommonClass> peoplelist;
    public JudgesListRow(Activity activity, List<JudgesCommonClass> peoplelist) {
        this.activity = activity;
        this.peoplelist = peoplelist;
    }

    @Override
    public int getCount() {
        return peoplelist.size();
    }

    @Override
    public Object getItem(int location) {
        return peoplelist.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertview= inflater.inflate(R.layout.judgeslistrow, null, true);
        username = (TextView) convertview.findViewById(R.id.ProfileName);
        profilepic = (ImageView)convertview.findViewById(R.id.profile_image);
        comapny = (TextView)convertview.findViewById(R.id.companyname);
         desig = (TextView)convertview.findViewById(R.id.designation);
        mobile = (TextView)convertview.findViewById(R.id.phonenumber);
        email = (TextView)convertview.findViewById(R.id.ProfileEmail);
        JudgesCommonClass peopleCommonClass =(JudgesCommonClass)getItem(position);
        username.setText(peopleCommonClass.getUserName());
        comapny.setText(peopleCommonClass.getCompanyName());
        desig.setText(peopleCommonClass.getDesignation());
        mobile.setText(peopleCommonClass.getMobileNum());
        email.setText(peopleCommonClass.getEmail());
        profilepic.setImageBitmap(peopleCommonClass.getProfilepic());
        return convertview;
    }
}
