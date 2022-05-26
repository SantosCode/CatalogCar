package br.com.fiap.catalogcar.data.remote

import br.com.fiap.catalogcar.data.remote.dto.AuthDto
import br.com.fiap.catalogcar.data.remote.dto.CarDto
import retrofit2.Response
import retrofit2.http.*

interface CarApi {

    @POST("/login")
    suspend fun login(@Header("Authorization") auth: String): Response<AuthDto>

    @GET("/cars")
    suspend fun getCars(@Header("Authorization") auth: String): List<CarDto>

    @GET("/cars/{id}")
    suspend fun getCar(@Header("Authorization") auth: String, @Path("id") id: Long): CarDto

    @POST("/cars")
    suspend fun addCar(@Header("Authorization") auth: String, @Body car: CarDto)

    @DELETE("/cars/{id}")
    suspend fun delCar(@Header("Authorization") auth: String, @Path("id") id: Long)
}