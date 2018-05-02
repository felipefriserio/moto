package br.com.android.moto.api

import br.com.android.moto.model.Moto
import retrofit2.Call
import retrofit2.http.*

interface MotoAPI{

    @GET("/moto")
    fun buscarTodos() : Call< List<Moto> >

    @POST("/moto")
    fun salvar(@Body moto: Moto) : Call<Void>

    @HTTP(method = "DELETE", path = "/moto", hasBody = true)
    fun excluir(@Body moto : Moto) : Call<Void>

}


