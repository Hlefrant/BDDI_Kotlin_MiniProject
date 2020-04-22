package sources

import models.Source
import retrofit2.Call
import retrofit2.http.GET

interface SourceService {
    @GET("/v2/everything")
    fun list(): Call<Source>

    //@Query("q") q: String?
}