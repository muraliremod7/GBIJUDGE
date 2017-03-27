package gbijudge.murali.gbijudge.ideas;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONObject;

import java.util.ArrayList;

import gbijudge.murali.gbijudge.R;


/**
 * Created by Hari Prahlad on 07-05-2016.
 */
public class IdeasListRow extends ArrayAdapter<IdeasCommonClass> {
    TextView ideaTitle,ideaId,eventName,status;
    public final Activity activity;
    public ArrayList<IdeasCommonClass> ideasCommonClasses;
    IdeasCommonClass commonClass;
    public static String registerationId;
    public static SharedPreferences regid;

    public IdeasListRow(Activity activity, ArrayList<IdeasCommonClass> peoplelist) {
        super(activity,0,peoplelist);
        this.activity = activity;
        this.ideasCommonClasses = peoplelist;
    }

    @Override
    public int getCount() {
        return ideasCommonClasses.size();
    }

    @Override
    public IdeasCommonClass getItem(int location) {
        return ideasCommonClasses.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertview, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertview= inflater.inflate(R.layout.ideaslistrow, null, true);
        ideaTitle = (TextView) convertview.findViewById(R.id.ideaname);
        ideaId = (TextView)convertview.findViewById(R.id.ideaid);
         eventName = (TextView)convertview.findViewById(R.id.eventname);
        status = (TextView)convertview.findViewById(R.id.status);

        commonClass =(IdeasCommonClass)getItem(position);

        ideaTitle.setText(commonClass.getIdeaTitle());
        ideaId.setText(commonClass.getId());
        eventName.setText(commonClass.getEventName());
        status.setText(commonClass.getStatus());
        final ImageView imageView = (ImageView)convertview.findViewById(R.id.overflow);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = ((IdeasCommonClass)getItem(position)).getIdeaTitle().toString();
                final String eventname = ((IdeasCommonClass)getItem(position)).getEventName().toString();
                regid = PreferenceManager.getDefaultSharedPreferences(activity);
                registerationId = regid.getString("adminid","0");

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
                alertDialog.setTitle("Add Scores To Idea");

                Context context = v.getContext();
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);
                final EditText marks = new EditText(context);
                marks.setHint("Enter Marks");
                layout.addView(marks);

                final EditText descriptionBox = new EditText(context);
                descriptionBox.setHint("Enter Comments");
                layout.addView(descriptionBox);

                alertDialog.setView(layout);
                alertDialog.setNegativeButton("Update",
                        new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, int which) {
                                Ion.with(activity)
                                        .load("POST","http://sample-env.ibqeg2uyqh.us-east-1.elasticbeanstalk.com/addscores")
                                        .setBodyParameter("judgeid",registerationId)
                                        .setBodyParameter("eventid",eventname)
                                        .setBodyParameter("ideaid",id)
                                        .setBodyParameter("marks",marks.getText().toString())
                                        .setBodyParameter("comments",descriptionBox.getText().toString())
                                        .asString()
                                        .setCallback(new FutureCallback<String>() {
                                            @Override
                                            public void onCompleted(Exception e, String result) {
                                                if(e!=null){

                                                }else {
                                                    try{
                                                        JSONObject object = new JSONObject(result);
                                                        int status = object.getInt("status");
                                                        if(status==1){
                                                            Toast.makeText(activity,"Scores Added Successfully", Toast.LENGTH_LONG).show();
                                                            dialog.cancel();
                                                            imageView.setVisibility(View.GONE);
                                                        }
                                                        else {

                                                        }

                                                    }catch (Exception ex){

                                                    }
                                                }
                                            }
                                        });
                            }
                        });
                alertDialog.show();
            }
        });

        return convertview;
    }
}
