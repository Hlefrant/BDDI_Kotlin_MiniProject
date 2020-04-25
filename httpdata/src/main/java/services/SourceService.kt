package services

import models.Source
import retrofit2.Call
import retrofit2.http.GET

interface SourceService {
    @GET("/v2/sources")
    fun list(): Call<Source>

}