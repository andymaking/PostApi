package io.king.postapi

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetroServiceInterface {

    @POST(value = "users")
    @Headers("Accept:application/json","Content-Type:/application/json", "Authorization: Bearer cd6bb1341519d4ade5965fd643a1a8fb6a0aff79cad4856a215f0581dff9e2d9" )
    fun createUser(@Body params: User): Call<UserResponse>
}