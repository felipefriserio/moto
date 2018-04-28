package br.com.android.moto.ui.listmoto

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.android.moto.model.Moto
import com.example.logonrm.carros.R

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_moto.view.*

class ListMotoAdapter(private val motos: List<Moto>,
                      private val context: Context) : RecyclerView.Adapter<ListMotoAdapter.MeuViewHolder>() {

    override fun getItemCount(): Int {
        return motos.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MeuViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_moto, parent, false)
        return MeuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MeuViewHolder?, position: Int) {
        val moto = motos[position]
        holder?.let {
            it.bindView(moto)
        }
    }


    class MeuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(moto: Moto) {
            itemView.tvMarca.text = moto.marca
            itemView.tvModelo.text = moto.modelo

            if (moto.urlImagem.isNullOrEmpty()) {
                itemView.ivFoto.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.procurando))
            } else {
                Picasso.get()
                        .load(moto.urlImagem)
                        //.noPlaceholder()
                        .placeholder(R.drawable.moto)
                        .error(R.drawable.procurando)
                        .into(itemView.ivFoto);
            }
        }

    }
}