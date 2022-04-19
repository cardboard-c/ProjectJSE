package com.example.projectjse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ViewUserProfileActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ToggleButton followButton;
    private FirebaseFirestore db;
    private ArrayList<Post> PostList = new ArrayList<Post>();
    private String username;
        DrawerLayout drawerLayout;

        @Override
        protected void onCreate (Bundle savedInstanceState){
            setPostList();
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_view_user_profile);
            recyclerView = (RecyclerView) findViewById(R.id.profileRecycler);
            followButton = (ToggleButton) findViewById(R.id.followButton);

            drawerLayout = findViewById(R.id.drawer_layout);
            username = getIntent().getStringExtra("UserName");




        }

        public void ClickMenu (View view){
            MainFeedActivity.openDrawer(drawerLayout);
        }

        public void ClickLogo (View view){
            MainFeedActivity.closeDrawer(drawerLayout);
        }

        public void ClickHome (View view){
            MainFeedActivity.redirectActivity(this, MainFeedActivity.class);
        }

        public void ClickMyAccount (View view){
            MainFeedActivity.redirectActivity(this, ProfileActivity.class);
        }

        public void ClickBoards (View view){
            MainFeedActivity.redirectActivity(this, Boards.class);
        }

        public void ClickSavedPosts (View view){
            MainFeedActivity.redirectActivity(this, SavedPosts.class);
        }

        public void ClickSettings (View view){
            MainFeedActivity.redirectActivity(this, Settings.class);
        }

        public void ClickLogout (View view){
            MainFeedActivity.logout(this);
        }

        @Override
        protected void onPause () {
            super.onPause();
            MainFeedActivity.closeDrawer(drawerLayout);
        }

        public void ClickAdd (View view){
            startActivity(new Intent(ViewUserProfileActivity.this, PostActivity.class));

        }

    private void setPostList(){

        db = FirebaseFirestore.getInstance();
        db.collection("posts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(document.get("user").toString() == username) {
                                    PostList.add(new Post(document));
                                    setAdapter();
                                }
                            }
                        }
                    }
                });

        //getUsersByID();
    }

    private void setAdapter(){
        recyclerAdapter adapter = new recyclerAdapter(PostList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }


        private boolean CheckIfFollowing () {
            return true;
        }
}
