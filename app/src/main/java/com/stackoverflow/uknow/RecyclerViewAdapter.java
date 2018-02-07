package com.stackoverflow.uknow;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
;
import com.bumptech.glide.Glide;
import com.stackoverflow.uknow.Classes.InterviewResult;

import java.util.List;



public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyHolder> {

    Context context;
    List<InterviewResult> interviewResults;

    public RecyclerViewAdapter( Context context, List<InterviewResult> interviewResults) {
        this.context = context;
        this.interviewResults = interviewResults;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.card_view,parent,false);
        MyHolder myHolder = new MyHolder(view);


        return myHolder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {

        final InterviewResult shared_interviewResult = interviewResults.get(interviewResults.size() - position - 1);



        //Loading image from Glide library.
        if (shared_interviewResult.getImageUrl() != null){
            Glide.with(context).load(shared_interviewResult.getImageUrl()).into(holder.image);
        }

        //setting message textView
        holder.name.setText(shared_interviewResult.getName());

        Double income_x = ((int)(shared_interviewResult.getIncome()*100))/100.0;
        holder.income.setText(income_x + " Lakhs");

        Double percentage = ((int)(shared_interviewResult.getPercentage_marks()*100))/100.0;
        holder.percentage.setText(percentage + " %");

    }

    @Override
    public int getItemCount() {

        int arr = 0;

        try{
            if(interviewResults.size()==0){

                arr = 0;

            }
            else{

                arr=interviewResults.size();
            }



        }catch (Exception e){



        }

        return arr;

    }

    public void clear() {
        this.interviewResults = null;
    }

    public void addAll(List<InterviewResult> userList) {
        this.interviewResults = userList;
    }

    class MyHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView name, income, percentage;


        public MyHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.card_image);
            name = (TextView) itemView.findViewById(R.id.card_name);
            income = (TextView) itemView.findViewById(R.id.card_income);
            percentage = (TextView) itemView.findViewById(R.id.card_percentage);

            image.requestLayout();
            name.requestLayout();



        }
    }

}
