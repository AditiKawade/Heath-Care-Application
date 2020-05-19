package com.ensias.healthcareapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ensias.healthcareapp.adapter.ConsultationAdapter;
import com.ensias.healthcareapp.model.Fiche;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ConsultationFragmentPage extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference FicheRef;
    private ConsultationAdapter adapter;
    View result;


    public ConsultationFragmentPage() {
        // Required empty public constructor
    }

    public static ConsultationFragmentPage newInstance() {
        ConsultationFragmentPage fragment = new ConsultationFragmentPage();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        result = inflater.inflate(R.layout.fragment_consultation_page, container, false);
        setUpRecyclerView();
        return result;
    }

    private void setUpRecyclerView() {

        FicheRef = db.collection("Patient").document(FirebaseAuth.getInstance().getCurrentUser().getEmail().toString()).collection("MyMedicalFolder");
        Query query = FicheRef.whereEqualTo("type", "Consultation").orderBy("dateCreated", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<Fiche> options = new FirestoreRecyclerOptions.Builder<Fiche>()
                .setQuery(query, Fiche.class)
                .build();

        adapter = new ConsultationAdapter(options);

        RecyclerView recyclerView = result.findViewById(R.id.conslutationRecycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}