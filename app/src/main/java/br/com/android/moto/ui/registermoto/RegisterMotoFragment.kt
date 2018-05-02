package br.com.android.moto.ui.registermoto

import android.support.v4.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.android.moto.R
import br.com.android.moto.api.MotoAPI
import br.com.android.moto.api.RetrofitClient
import retrofit2.Callback
import br.com.android.moto.model.Moto
import br.com.android.moto.ui.listmoto.ListMotoFragment
import br.com.android.moto.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_register_moto.*
import retrofit2.Call
import retrofit2.Response

class RegisterMotoFragment : Fragment() {

    var moto: Moto = Moto("","","",0,"")

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_register_moto, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!moto.placa.isNullOrEmpty()) {
            inputMarca.editText?.setText(moto.marca)
            inputModelo.editText?.setText(moto.modelo)
            inputAno.editText?.setText(moto.ano.toString())
            inputUrl.editText?.setText(moto.urlImagem)
            inputPlaca.editText?.setText(moto.placa)
        }


        btSalvar.setOnClickListener{
            val api = RetrofitClient
                    .getInstance()
                    .create(MotoAPI :: class.java)

             val moto = Moto(
                    inputPlaca.editText?.text.toString(),
                    inputMarca.editText?.text.toString(),
                    inputModelo.editText?.text.toString(),
                    inputAno.editText?.text.toString().toInt(),
                    inputUrl.editText?.text.toString()
            )

            api.salvar(moto)
                    .enqueue(object : Callback<Void>{
                        override fun onFailure(call: Call<Void>?, t: Throwable?) {
                            Log.e("Moto", t?.message)
                        }

                        override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                            if (response?.isSuccessful == true){
                                Toast.makeText(context, R.string.success, Toast.LENGTH_SHORT).show()
                                var act = context as MainActivity
                                act.changeFragment(ListMotoFragment())
                                limparCampos()
                            }else{
                                Toast.makeText(context, R.string.error, Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
        }



        btDeletar.setOnClickListener {
            val api = RetrofitClient
                    .getInstance()
                    .create(MotoAPI :: class.java)

            val moto = Moto(
                    inputPlaca.editText?.text.toString(),
                    inputMarca.editText?.text.toString(),
                    inputModelo.editText?.text.toString(),
                    inputAno.editText?.text.toString().toInt(),
                    inputUrl.editText?.text.toString()
            )

            api.excluir(moto)
                    .enqueue(object : Callback<Void>{
                        override fun onFailure(call: Call<Void>?, t: Throwable?) {
                            Log.e("ERRO!!!!", t?.message)
                        }

                        override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                            if (response?.isSuccessful == true){
                                Toast.makeText(context, R.string.success, Toast.LENGTH_SHORT).show()
                                var act = context as MainActivity
                                act.changeFragment(ListMotoFragment())
                                limparCampos()
                            }else{
                                Toast.makeText(context, R.string.error, Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
        }


    }

    private fun limparCampos(){
        inputMarca.editText?.setText("")
        inputModelo.editText?.setText("")
        inputAno.editText?.setText("")
        inputPlaca.editText?.setText("")
        inputUrl.editText?.setText("")
    }

}