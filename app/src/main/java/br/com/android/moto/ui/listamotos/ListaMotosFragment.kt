package br.com.android.moto.ui.listamotos

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.android.moto.api.MotoAPI
import br.com.android.moto.api.RetrofitClient
import br.com.android.moto.model.Moto
import com.example.logonrm.carros.R

import kotlinx.android.synthetic.main.erro.*
import kotlinx.android.synthetic.main.fragment_lista_motos.*
import kotlinx.android.synthetic.main.loading.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class ListaMotosFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_lista_motos, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        carregarDados()
    }

    fun carregarDados() {
        var api = RetrofitClient.getInstance().create(MotoAPI::class.java)

        loading.visibility = View.VISIBLE

        api.buscarTodos().enqueue(object : Callback<List<Moto>> {
            override fun onResponse(call: Call<List<Moto>>?, response: Response<List<Moto>>?) {

                if (response?.isSuccessful() == true) {
                    setupLista(response?.body())
                } else {
                    containerErro.visibility = View.VISIBLE
                    tvMensagemErro.text = response?.errorBody()?.charStream()?.readText()
                }

                loading.visibility = View.GONE
            }

            override fun onFailure(call: Call<List<Moto>>?, t: Throwable?) {
                Log.i("TAG", t?.message)
                containerErro.visibility = View.VISIBLE
                tvMensagemErro.text = t?.message
                loading.visibility = View.GONE
            }

        })
    }

    fun setupLista(motos: List<Moto>?) {
        motos.let {
            rvMotos.adapter = ListaMotosAdapter(motos!!, context)
            val layoutManager = LinearLayoutManager(context)
            rvMotos.layoutManager = layoutManager
        }

    }
}