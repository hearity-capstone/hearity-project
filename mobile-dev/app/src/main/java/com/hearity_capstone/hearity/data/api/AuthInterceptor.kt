package com.hearity_capstone.hearity.data.api

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val tokenProvider: TokenProvider) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val token = runBlocking {
            tokenProvider.getToken()
        }

        if (token.isNullOrEmpty()) {
            // Return without token
            return chain.proceed(chain.request())
            // Or return error respond:
            // return Response.Builder()
            //     .code(401) // Unauthorized
            //     .message("Unauthorized: No token")
            //     .build()
        }

        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        return chain.proceed(request)
    }
}