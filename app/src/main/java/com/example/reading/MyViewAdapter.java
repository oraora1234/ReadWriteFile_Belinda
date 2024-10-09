package com.example.reading;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class MyViewAdapter extends RecyclerView.Adapter<MyViewAdapter.PersonViewHolder> {

    private Context context;
    private ArrayList<Person> valuesList;

    // Constructor
    public MyViewAdapter(Context _context, ArrayList<Person> _valuesList) {
        this.context = _context;
        this.valuesList = _valuesList;
    }

    // ViewHolder inner class
    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        TextView tvFirstName;
        TextView tvLastName;
        TextView tvPhoneNumber;
        ImageView imgIcon;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);

            // Find views in the person_item.xml layout
            tvFirstName = itemView.findViewById(R.id.FirstName);      // TextView for name
            tvLastName = itemView.findViewById(R.id.LastName);   // TextView for last name
            tvPhoneNumber = itemView.findViewById(R.id.Phone);   // TextView for phone number
            imgIcon = itemView.findViewById(R.id.imgIcon);   // ImageView for gender icon
        }
    }

    // onCreateViewHolder: Inflate the layout and create a new ViewHolder
    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the person_item.xml layout
        View view = LayoutInflater.from(context).inflate(R.layout.person_item, parent, false);
        return new PersonViewHolder(view);
    }

    // onBindViewHolder: Bind the data to the ViewHolder
    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        // Get the data for the current position
        Person person = valuesList.get(position);

        // Bind the name and phone number to the TextViews
        holder.tvFirstName.setText(person.getFirstName());
        holder.tvLastName.setText(person.getLastName());
        holder.tvPhoneNumber.setText(person.getPhone());

        // For the image describing the gender, use this code:
        Gender g = person.getGender();
        holder.imgIcon.setImageResource(GenderUtils.setImage(g));  // Set the gender icon
    }

    // getItemCount: Return the size of the data
    @Override
    public int getItemCount() {
        return valuesList.size();
    }
}


