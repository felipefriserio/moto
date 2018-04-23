package br.com.android.moto.api

import br.com.android.moto.model.Moto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MotoAPI{

    @GET("/moto")
    fun buscarTodos() : Call< List<Moto> >

    @POST("/moto")
    fun salvar(@Body moto: Moto) : Call<Void>

}


