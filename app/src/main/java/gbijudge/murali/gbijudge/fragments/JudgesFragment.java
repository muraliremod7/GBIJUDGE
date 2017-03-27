package gbijudge.murali.gbijudge.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import gbijudge.murali.gbijudge.R;
import gbijudge.murali.gbijudge.Services.AlertDialogManager;
import gbijudge.murali.gbijudge.judges.JudgesCommonClass;
import gbijudge.murali.gbijudge.judges.JudgesListRow;
import gbijudge.murali.gbijudge.judges.SinglePeopleActivity;

public class JudgesFragment extends Fragment {
    private ListView listView;
    private List<JudgesCommonClass> arrayList = new ArrayList<JudgesCommonClass>();
    JudgesCommonClass judgesCommonClass;
    JudgesListRow peoplesListRow;
    AlertDialogManager alert;
    private ProgressDialog pDialog;
    String Profilename, companyname, designation,PhoneNum,Email;
    public JudgesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_judges, container, false);
        Toolbar toolbar = (Toolbar)view.findViewById(R.id.judgestoolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        toolbar.setTitle("Judges");
        judgesCommonClass =new JudgesCommonClass();
        // Inflate the layout for this fragment
        peoples();
        peoplesListRow = new JudgesListRow(getActivity(),arrayList);
        peoplesListRow.notifyDataSetChanged();
        alert = new AlertDialogManager();
        listView = (ListView)view.findViewById(R.id.judgeslist);
        return view;
    }
    public void timerDelayRemoveDialog(long time, final ProgressDialog d){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                d.dismiss();
            }
        }, time);

    }
    private void peoples() {
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Please Wait");
        pDialog.setCancelable(false);
        pDialog.show();
        timerDelayRemoveDialog(10*1000,pDialog);
        Ion.with(getContext())
                .load("http://sample-env.ibqeg2uyqh.us-east-1.elasticbeanstalk.com/getjudges")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if(e!=null){

                        }else{
                            try {
                                JSONObject jSONObject = new JSONObject(result);

                                JSONArray array = jSONObject.getJSONArray("judges");
                                for(int i =0;i<array.length();i++){
                                    JSONObject j = array.getJSONObject(i);
                                    JudgesCommonClass peopleCommonClass = new JudgesCommonClass();

                                    if(j.has("judgeusername")){
                                        peopleCommonClass.setUserName(j.getString("judgeusername"));
                                    }
                                    if(j.has("judgemobile")){
                                        peopleCommonClass.setMobileNum(j.getString("judgemobile").toString());
                                    }
                                    if(j.has("img")){
                                        // any other views you want associated with a profile.
                                        // You're finding the view based from the inflated layout, not the activity layout
                                        String imgname = j.getString("img");
                                        String url = "http://sample-env.ibqeg2uyqh.us-east-1.elasticbeanstalk.com/getimage?filename="+imgname;
                                        try {
                                            Bitmap image = Ion.with(getActivity())
                                                    .load(url)
                                                    .withBitmap()
                                                    .asBitmap()
                                                    .get(10, TimeUnit.SECONDS);
                                            peopleCommonClass.setProfilepic(image);
                                        } catch (InterruptedException e1) {
                                            e1.printStackTrace();
                                        } catch (ExecutionException e1) {
                                            e1.printStackTrace();
                                        } catch (TimeoutException e1) {
                                            e1.printStackTrace();
                                        }
                                    }
                                    if(j.has("judgeemail")){
                                        peopleCommonClass.setEmail(j.getString("judgeemail"));
                                    }
                                    if(j.has("judgecompanyname")){
                                        peopleCommonClass.setCompanyName(j.getString("judgecompanyname"));
                                    }
                                    if(j.has("judgedesignation")){
                                        peopleCommonClass.setDesignation(j.getString("judgedesignation"));
                                    }
                                    arrayList.add(peopleCommonClass);
                                }
                                if(pDialog.isShowing())
                                    pDialog.dismiss();
                                listView.setAdapter(peoplesListRow);

                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                        }
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Profilename = ((TextView)view.findViewById(R.id.ProfileName)).getText().toString();
                                companyname = ((TextView)view.findViewById(R.id.companyname)).getText().toString();
                                designation = ((TextView)view.findViewById(R.id.designation)).getText().toString();
                                PhoneNum = ((TextView)view.findViewById(R.id.phonenumber)).getText().toString();
                                Email = ((TextView)view.findViewById(R.id.ProfileEmail)).getText().toString();
                                Intent singlpeople = new Intent(getActivity(), SinglePeopleActivity.class);
                                Bundle peoplessbundle = new Bundle();
                                peoplessbundle.putString("ProfileName", Profilename);
                                peoplessbundle.putString("companyname", companyname);
                                peoplessbundle.putString("designation", designation);
                                peoplessbundle.putString("mobile",PhoneNum);
                                peoplessbundle.putString("Email",Email);
                                singlpeople.putExtras(peoplessbundle);
                                if(singlpeople!=null){
                                    startActivity(singlpeople);
                                }
                            }
                        });
                    }

                });
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.judges, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
