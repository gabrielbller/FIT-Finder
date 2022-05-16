package com.quintus.labs.datingapp.Matched;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.quintus.labs.datingapp.R;
import com.quintus.labs.datingapp.Utils.TopNavigationViewHelper;
import com.quintus.labs.datingapp.Utils.User;

import java.util.ArrayList;
import java.util.List;



public class Matched_Activity extends AppCompatActivity {

    private static final String TAG = "Matched_Activity";
    private static final int ACTIVITY_NUM = 2;
    List<Users> matchList = new ArrayList<>();
    List<User> copyList = new ArrayList<>();
    private Context mContext = Matched_Activity.this;
    private String userId, userSex, lookforSex;
    private double latitude = 37.349642;
    private double longtitude = -121.938987;
    private EditText search;
    private List<Users> usersList = new ArrayList<>();
    private RecyclerView recyclerView, mRecyclerView;
    private ActiveUserAdapter adapter;
    private MatchUserAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matched);

        setupTopNavigationView();
        searchFunc();


        recyclerView = findViewById(R.id.active_recycler_view);
        mRecyclerView = findViewById(R.id.matche_recycler_view);

        adapter = new ActiveUserAdapter(usersList, getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        prepareActiveData();

        mAdapter = new MatchUserAdapter(matchList, getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager1);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        prepareMatchData();


    }

    private void prepareActiveData() {
        Users users = new Users("1", "Luiza Fernanda", 21, "https://cdn.pixabay.com/photo/2017/01/23/19/40/woman-2003647_960_720.jpg", "Simples e adora caminhar", "Atriz", 200);
        usersList.add(users);
        users = new Users("2", "Claudia Parreira", 20, "https://cdn.pixabay.com/photo/2017/12/22/14/42/girl-3033718_960_720.jpg", "Muito paz e amor", "Bailarina", 800);
        usersList.add(users);
        users = new Users("3", "Ana Janis", 22, "https://cdn.pixabay.com/photo/2015/03/03/18/58/woman-657753_960_720.jpg", "Adoro leg days", "Cantar", 400);
        usersList.add(users);
        users = new Users("7", "Pietra Schonnel", 19, "https://cdn.pixabay.com/photo/2016/11/29/03/35/girl-1867092_960_720.jpg", "Em busca do fitcher perfeito", "Artes", 5000);
        usersList.add(users);

        adapter.notifyDataSetChanged();
    }

    private void prepareMatchData() {
        Users users = new Users("1", "Luiza Fernanda", 21, "https://cdn.pixabay.com/photo/2017/01/23/19/40/woman-2003647_960_720.jpg", "Simples e adora caminha", "Atriz", 200);
        matchList.add(users);
        users = new Users("2", "Claudia Parreira", 20, "https://cdn.pixabay.com/photo/2017/12/22/14/42/girl-3033718_960_720.jpg", "Muito paz e amor", "Bailarina", 800);
        matchList.add(users);
        users = new Users("3", "Ana Janis", 22, "https://cdn.pixabay.com/photo/2015/03/03/18/58/woman-657753_960_720.jpg", "Adoro leg days", "Cantar", 400);
        matchList.add(users);
        users = new Users("4", "Pietra Schonnel", 19, "https://cdn.pixabay.com/photo/2016/11/29/03/35/girl-1867092_960_720.jpg", "Em busca do fitcher perfeito", "Nadar", 1308);
        matchList.add(users);
        users = new Users("5", "Bruna Schmitt", 20, "https://cdn.pixabay.com/photo/2016/03/23/08/34/woman-1274361_960_720.jpg", "Na minha ", "Academia", 1200);
        matchList.add(users);
        users = new Users("6", "Amanda Ferraz", 21, "https://cdn.pixabay.com/photo/2017/03/17/04/07/woman-2150881_960_720.jpg", "AMOO treinar superiores", "Academia", 700);
        matchList.add(users);
        users = new Users("7", "Luana Dietch", 19, "https://cdn.pixabay.com/photo/2018/01/29/17/01/woman-3116587_960_720.jpg", "Quero alguem que me motive", "Ficar em casa", 5000);
        matchList.add(users);

        mAdapter.notifyDataSetChanged();
    }

    private void searchFunc() {
       /* search = findViewById(R.id.searchBar);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchText();
            }

            @Override
            public void afterTextChanged(Editable s) {
                searchText();
            }
        });*/
    }

    /* private void searchText() {
         String text = search.getText().toString().toLowerCase(Locale.getDefault());
         if (text.length() != 0) {
             if (matchList.size() != 0) {
                 matchList.clear();
                 for (User user : copyList) {
                     if (user.getUsername().toLowerCase(Locale.getDefault()).contains(text)) {
                         matchList.add(user);
                     }
                 }
             }
         } else {
             matchList.clear();
             matchList.addAll(copyList);
         }

         mAdapter.notifyDataSetChanged();
     }

     private boolean checkDup(User user) {
         if (matchList.size() != 0) {
             for (User u : matchList) {
                 if (u.getUsername() == user.getUsername()) {
                     return true;
                 }
             }
         }

         return false;
     }

     private void checkClickedItem(int position) {

         User user = matchList.get(position);
         //calculate distance
         Intent intent = new Intent(this, ProfileCheckinMatched.class);
         intent.putExtra("classUser", user);

         startActivity(intent);
     }
 */
    private void setupTopNavigationView() {
        Log.d(TAG, "setupTopNavigationView: setting up TopNavigationView");
        BottomNavigationViewEx tvEx = findViewById(R.id.topNavViewBar);
        TopNavigationViewHelper.setupTopNavigationView(tvEx);
        TopNavigationViewHelper.enableNavigation(mContext, tvEx);
        Menu menu = tvEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }


}
