package com.example.demo;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.WriteResult;

@Service
public class CRUDService {

    public String createCRUD(CRUD crud) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreOptions.getDefaultInstance().getService();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("credentials").document(crud.getUsername())
                .set(crud);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public CRUD getCRUD(String credential_id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreOptions.getDefaultInstance().getService();
        DocumentReference documentReference = dbFirestore.collection("credentials").document(credential_id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        CRUD crud;
        if (document.exists()) {
            crud = document.toObject(CRUD.class);
            return crud;
        }
        return null;
    }

    // public String updateCRUD(CRUD crud) throws InterruptedException, ExecutionException {
    //     Firestore dbFirestore = FirestoreOptions.getDefaultInstance().getService();
    //     ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection("credentials").document(crud.getUsername())
    //             .set(crud);
    //     return collectionApiFuture.get().getUpdateTime().toString();
    // }

    public String deleteCRUD(String credential_id) {
        Firestore dbFirestore = FirestoreOptions.getDefaultInstance().getService();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("credentials").document(credential_id).delete();
        return "Successfully deleted " + credential_id;
    }
}
