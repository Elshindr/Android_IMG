package com.example.coach.vue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.coach.R;
import com.example.coach.controleur.Controle;
import com.example.coach.modele.Profil;
import com.example.coach.outils.MesOutils;

import java.util.ArrayList;

public class HistoListAdapter extends BaseAdapter {

    private ArrayList<Profil> lesProfils;
    private LayoutInflater inflater;
    private Context context;

    /**
     * Constructeur
     * @param context
     * @param lesProfils
     */
    public HistoListAdapter(Context context, ArrayList<Profil> lesProfils){
        this.context = context;
        this.lesProfils = lesProfils;
        this.inflater = LayoutInflater.from(context);
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return lesProfils.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return lesProfils.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewProperties viewProperties;
        if(convertView == null){
            viewProperties = new ViewProperties();
            convertView = inflater.inflate(R.layout.layout_liste_histo, null);
            viewProperties.txtListDate = (TextView)convertView.findViewById(R.id.txtListDate);
            viewProperties.txtListIMG = (TextView)convertView.findViewById(R.id.txtListIMG);
            viewProperties.btnListSuppr = (ImageButton) convertView.findViewById(R.id.btnListSuppr);
            convertView.setTag(viewProperties) ;
        }else{
            viewProperties = (ViewProperties)convertView.getTag();
        }
        viewProperties.txtListDate.setText(MesOutils.convertDateToString(lesProfils.get(position).getDateMesure()));
        viewProperties.txtListIMG.setText(MesOutils.format2Decimal(lesProfils.get(position).getImg()));
        viewProperties.btnListSuppr.setTag(position);
        viewProperties.btnListSuppr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int indice = (int)v.getTag();
                Controle controle = Controle.getInstance(null);
                controle.delProfil(lesProfils.get(indice));
                notifyDataSetChanged() ;
            }
        });
        viewProperties.txtListDate.setTag(position);
        viewProperties.txtListDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int indice = (int)v.getTag();
                ((HistoActivity)context).afficheProfil(lesProfils.get(indice));
            }
        });
        viewProperties.txtListIMG.setTag(position);
        viewProperties.txtListIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int indice = (int)v.getTag();
                ((HistoActivity)context).afficheProfil(lesProfils.get(indice));
            }
        });

        return convertView;
    }

    /**
     * Objets graphiques
     */
    private class ViewProperties{
        TextView txtListDate;
        TextView txtListIMG;
        ImageButton btnListSuppr;
    }
}
