package gbijudge.murali.gbijudge.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
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

import gbijudge.murali.gbijudge.R;
import gbijudge.murali.gbijudge.Services.AlertDialogManager;
import gbijudge.murali.gbijudge.ideas.IdeasCommonClass;
import gbijudge.murali.gbijudge.ideas.IdeasListRow;
import gbijudge.murali.gbijudge.ideas.SingleIdeaActivity;


public class IdeasFragment extends Fragment {
    private ListView listView;
    private ArrayList<IdeasCommonClass> arrayList = new ArrayList<IdeasCommonClass>();
    private IdeasCommonClass ideasCommonClass;
    private IdeasListRow ideasListRow;
    AlertDialogManager alert;
    private ProgressDialog pDialog;
    String ideaTitle,ideaId,eventName,status;
    public IdeasFragment() {
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
        View view = inflater.inflate(R.layout.fragment_ideas, container, false);
        Toolbar toolbar = (Toolbar)view.findViewById(R.id.ideastoolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        toolbar.setTitle("Ideas");
        ideasCommonClass = new IdeasCommonClass();
        ideasListRow = new IdeasListRow(getActivity(),arrayList);
        alert = new AlertDialogManager();
        listView = (ListView)view.findViewById(R.id.ideaslist);
        ideasListRow.notifyDataSetChanged();
        peoples();
        return view;
    }

    private void peoples() {
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Please Wait");
        pDialog.setCancelable(false);
        pDialog.show();
        Ion.with(getContext())
                .load("http://sample-env.ibqeg2uyqh.us-east-1.elasticbeanstalk.com/allideas")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if(e!=null){

                        }else{
                            try {
                                JSONArray jSONObject = new JSONArray(result);
                                for(int i =0;i<jSONObject.length();i++){
                                    JSONObject j = jSONObject.getJSONObject(i);
                                    IdeasCommonClass ideasCommonClass = new IdeasCommonClass();

                                    if(j.has("ideatitle")){
                                        ideasCommonClass.setIdeaTitle(j.getString("ideatitle"));
                                    }
                                    if(j.has("_id")){
                                        ideasCommonClass.setId(j.getString("_id"));
                                    }
                                    if(j.has("eventid")){
                                        ideasCommonClass.setEventName(j.getString("eventid").toString());
                                    }
                                    if(j.has("status")){
                                        ideasCommonClass.setStatus(j.getString("status").toString());
                                    }
                                    arrayList.add(ideasCommonClass);
                                }
                                if(pDialog.isShowing())
                                    pDialog.dismiss();
                                listView.setAdapter(ideasListRow);

                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                        }
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                ideaTitle = ((TextView)view.findViewById(R.id.ideaname)).getText().toString();
                                ideaId = ((TextView)view.findViewById(R.id.ideaid)).getText().toString();
                                eventName = ((TextView)view.findViewById(R.id.eventname)).getText().toString();
                                status = ((TextView)view.findViewById(R.id.status)).getText().toString();

                                Intent singlpeople = new Intent(getActivity(), SingleIdeaActivity.class);
                                Bundle peoplessbundle = new Bundle();

                                peoplessbundle.putString("ideatitle", ideaTitle);
                                peoplessbundle.putString("ideaid", ideaId);
                                peoplessbundle.putString("eventname", eventName);
                                peoplessbundle.putString("status",status);
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
        inflater.inflate(R.menu.ideas, menu);
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
