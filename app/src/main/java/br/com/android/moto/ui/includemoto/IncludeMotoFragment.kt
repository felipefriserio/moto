package br.com.android.moto.ui.includemoto

import android.support.v4.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.android.moto.api.MotoAPI
import br.com.android.moto.api.RetrofitClient
import retrofit2.Callback
import br.com.android.moto.model.Moto
import com.example.logonrm.carros.R
import kotlinx.android.synthetic.main.fragment_nova_moto.*
import retrofit2.Call
import retrofit2.Response

class IncludeMotoFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_nova_moto, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                            Log.e("Carro", t?.message)
                        }

                        override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                            if (response?.isSuccessful == true){
                                Toast.makeText(context, "Sucesso", Toast.LENGTH_SHORT).show()
                                limparCampos()
                            }else{
                                Toast.makeText(context, "Errou coloque faustao", Toast.LENGTH_SHORT).show()
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