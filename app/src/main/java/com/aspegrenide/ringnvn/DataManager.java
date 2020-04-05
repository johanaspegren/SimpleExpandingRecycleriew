package com.aspegrenide.ringnvn;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.Date;


public class DataManager {

    DatabaseReference mDatabase;
    ArrayList<Contact> contacts = new ArrayList<Contact>();

    public void writeContactsToFirebase(ArrayList<Contact> mContacts) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        for (Contact c : mContacts) {
            mDatabase.child("contacts").child(c.getPhoneNr()).setValue(c);
        }
    }

    public void writeCallListToFirebase(ArrayList<CallDetails> mCalls) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        for (CallDetails cd : mCalls) {
            String ts = Long.toString(cd.getTimeStampStart().getTime());
            mDatabase.child("calls").child(ts).setValue(cd);
        }
    }

    public ArrayList<Contact> populateContactList() {
        ArrayList<Contact> contactList = new ArrayList<>();

        String isb = "Är mycket intresserad av golfhistoria och har en samling av antika träklubbor";
        Contact contact =
                new Contact("Johan", "0736509716", "Golf",
                        R.drawable.golf, "", 1972,
                        "Stattena", isb);
        contactList.add(contact);

        isb = "Var nära att sänka segelbåten under en storm på Vättern";
        contact = new Contact("Ulric", "0702945759",
                "segling", R.drawable.yacht,"",
                1972, "Hässleholm", isb);
        contactList.add(contact);

        isb = "Har skakat hand med Barack Obama när hon jobbade på Nasdaq en sommar";
        contact = new Contact("Maria", "0730785267",
                "Blommor", R.drawable.flower, "", 1931, "Bredgatan", isb);
        contactList.add(contact);

        isb = "Har två barn, flickor båda två. Mycket magi, enhörningar och prinsessor.";
        contact = new Contact("Fabian", "0730736449","Naturen", R.drawable.forest, "", 1931, "LSS", isb);
        contactList.add(contact);

        isb = "Är släkt med Sveriges mest berömda konstnär.... Carl Larsson";
        contact = new Contact("Robert", "0733668185","Mat och Dryck", R.drawable.dining, "", 1931, "NA", isb);
        contactList.add(contact);


        isb = "ipsum lorem ...";
        contact = new Contact("Lars-Inge", "07365097165","Fågelskådning", R.drawable.bird, "1 timme sedan", 1931, "Dyrebäck", isb);
        contactList.add(contact);

        isb = "ipsum lorem ...";
        contact = new Contact("Stina", "07365097166","Odla", R.drawable.farm, "nyss", 1931, "Kometen", isb);
        contactList.add(contact);

        isb = "ipsum lorem ...";
        contact = new Contact("Louise", "07365097167","Naturen", R.drawable.forest, "nyss", 1931, "NA", isb);
        contactList.add(contact);

        writeContactsToFirebase(contactList);
        return contactList;
    }
}