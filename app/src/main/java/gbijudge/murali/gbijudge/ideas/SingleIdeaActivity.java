package gbijudge.murali.gbijudge.ideas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import gbijudge.murali.gbijudge.R;

public class SingleIdeaActivity extends AppCompatActivity {
    private Toolbar toolbar;
    public Bundle getBundle;
    private TextView ideaTitle,eventName;
    private SharedPreferences.Editor editor;
    private TextView que1,que2,que3,que4,que5,que6,que7,que8,que9,que10,que11,que12,que13,que14,que15,que16,que17,que18,que19,que20,que21,que22,que23,que24,que25;
    ArrayList<String> listdata = new ArrayList<String>();
    String object1;
    public static String registerationId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_idea_activity);
        toolbar = (Toolbar)findViewById(R.id.singleideatoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ideaTitle = (TextView)findViewById(R.id.ideaTitle);
        eventName = (TextView)findViewById(R.id.eventName);
        que1 = (TextView)findViewById(R.id.qu1);
        que2 = (TextView)findViewById(R.id.qu2);
        que3 = (TextView)findViewById(R.id.qu3);
        que4 = (TextView)findViewById(R.id.qu4);
        que5 = (TextView)findViewById(R.id.qu5);
        que6 = (TextView)findViewById(R.id.qu6);
        que7 = (TextView)findViewById(R.id.qu7);
        que8 = (TextView)findViewById(R.id.qu8);
        que9 = (TextView)findViewById(R.id.qu9);
        que10 = (TextView)findViewById(R.id.qu10);
        que11 = (TextView)findViewById(R.id.qu11);
        que12 = (TextView)findViewById(R.id.qu12);
        que13 = (TextView)findViewById(R.id.qu13);
        que14 = (TextView)findViewById(R.id.qu14);
        que15 = (TextView)findViewById(R.id.qu15);
        que16 = (TextView)findViewById(R.id.qu16);
        que17 = (TextView)findViewById(R.id.qu17);
        que18 = (TextView)findViewById(R.id.qu18);
        que19 = (TextView)findViewById(R.id.qu19);
        que20 = (TextView)findViewById(R.id.qu20);
        que21 = (TextView)findViewById(R.id.qu21);
        que22 = (TextView)findViewById(R.id.qu22);
        que23 = (TextView)findViewById(R.id.qu23);
        que24 = (TextView)findViewById(R.id.qu24);
        que25 = (TextView)findViewById(R.id.qu25);
        getBundle = this.getIntent().getExtras();
        String ideaTItle = getBundle.getString("ideatitle");
        String eventNAme = getBundle.getString("eventname");
        String id = getBundle.getString("ideaid");
        ideaTitle.setText(ideaTItle);
        eventName.setText(eventNAme);
        getIdea(id);
    }

    private void getIdea(String id) {
        Ion.with(getApplicationContext())
                .load("http://sample-env.ibqeg2uyqh.us-east-1.elasticbeanstalk.com/getideabyideaid?ideaid="+id)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if(e!=null){

                        }else{
                            try{
                                JSONObject jsonObject = new JSONObject(result);
                                JSONArray jsonArray = jsonObject.getJSONArray("idea");
                                for(int i=0;i<=jsonArray.length();i++){
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    if(object.has("ideaqu1")){
                                        que1.setText(object.getString("ideaqu1"));
                                    }
                                    if(object.has("ideaqu2")){
                                        que2.setText(object.getString("ideaqu2"));
                                    }
                                    if(object.has("ideaqu3")){
                                        que3.setText(object.getString("ideaqu3"));
                                    }
                                    if(object.has("ideaqu4")){
                                        que4.setText(object.getString("ideaqu4"));
                                    }
                                    if(object.has("ideaqu5")){
                                        que5.setText(object.getString("ideaqu5"));
                                    }
                                    if(object.has("ideaqu6")){
                                        que6.setText(object.getString("ideaqu6"));
                                    }
                                    if(object.has("ideaqu7")){
                                        que7.setText(object.getString("ideaqu7"));
                                    }
                                    if(object.has("ideaqu8")){
                                        que8.setText(object.getString("ideaqu8"));
                                    }
                                    if(object.has("ideaqu9")){
                                        que9.setText(object.getString("ideaqu9"));
                                    }
                                    if(object.has("ideaqu10")){
                                        que10.setText(object.getString("ideaqu10"));
                                    }
                                    if(object.has("ideaqu11")){
                                        que11.setText(object.getString("ideaqu11"));
                                    }
                                    if(object.has("ideaqu12")){
                                        que12.setText(object.getString("ideaqu12"));
                                    }
                                    if(object.has("ideaqu13")){
                                        que13.setText(object.getString("ideaqu13"));
                                    }
                                    if(object.has("ideaqu14")){
                                        que14.setText(object.getString("ideaqu14"));
                                    }
                                    if(object.has("ideaqu15")){
                                        que15.setText(object.getString("ideaqu15"));
                                    }
                                    if(object.has("ideaqu16")){
                                        que16.setText(object.getString("ideaqu16"));
                                    }
                                    if(object.has("ideaqu17")){
                                        que17.setText(object.getString("ideaqu17"));
                                    }
                                    if(object.has("ideaqu18")){
                                        que18.setText(object.getString("ideaqu18"));
                                    }
                                    if(object.has("ideaqu19")){
                                        que19.setText(object.getString("ideaqu19"));
                                    }
                                    if(object.has("ideaqu20")){
                                        que20.setText(object.getString("ideaqu20"));
                                    }
                                    if(object.has("ideaqu21")){
                                        que21.setText(object.getString("ideaqu21"));
                                    }
                                    if(object.has("ideaqu22")){
                                        que22.setText(object.getString("ideaqu22"));
                                    }
                                    if(object.has("ideaqu23")){
                                        que23.setText(object.getString("ideaqu23"));
                                    }
                                    if(object.has("ideaqu24")){
                                        que24.setText(object.getString("ideaqu24"));
                                    }
                                    if(object.has("ideaqu25")){
                                        que25.setText(object.getString("ideaqu25"));
                                    }
                                    registerationId = object.getString("_id");
                                }
                                SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                editor = settings.edit();
                                editor.putString("ideaid", registerationId);
                                editor.commit();
                            }catch (Exception Ex){

                            }
                        }
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
