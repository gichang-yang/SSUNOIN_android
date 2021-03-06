package com.notisnow.anonimous.ssunoin.UI.Main.NoticeByMajor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.notisnow.anonimous.ssunoin.R;
import com.notisnow.anonimous.ssunoin.StaticField.Data;
import com.notisnow.anonimous.ssunoin.UI.Notice.NoticeActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class NoticeByMajorFragment extends Fragment implements NoticeByMajorContract.View{
    NoticeByMajorContract.View view=this;
    NoticeByMajorContract.Presenter presenter;

    public NoticeByMajorFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("fragment response","nbmf");
        view=this;
     presenter=new NoticeByMajorPresenter(this);
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_notice_by_major, container, false);
        RecyclerView recyclerView=(RecyclerView)v.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(new Adapter());

        return v;
    }

    @Override
    public void setPresenter() {
        this.presenter=new NoticeByMajorPresenter(this);
    }


    class Holder extends RecyclerView.ViewHolder{
        TextView majorName;
        TextView engMajorName;
        int position;
        public Holder(final View itemView) {
            super(itemView);
            majorName=(TextView)itemView.findViewById(R.id.title);
            engMajorName=(TextView)itemView.findViewById(R.id.engtitle);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent =new Intent(getActivity().getApplicationContext(),NoticeActivity.class);
                    intent.putExtra("majorId",position);

                    startActivity(intent);
                }
            });
        }
        public void setPosition(int position){
            this.position=position;
        }
    }

    class Adapter extends RecyclerView.Adapter<Holder> implements ByMajorAdapterContract.View{

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_major_card,parent,false);

            Holder viewHolder=new Holder(v);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            holder.majorName.setText(Data.getDepartmentOf().get(position).getName());
            holder.engMajorName.setText(Data.getDepartmentOf().get(position).getEngName());
            holder.setPosition(position);
        }


        @Override
        public int getItemCount() {
            return Data.getDepartmentOf().size();
        }
    }


}
