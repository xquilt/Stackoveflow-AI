package com.polendina.soai.data.network

import com.polendina.soai.utils.parseFKey
import java.util.*

class So {

    private var fkey = ""
    private var email = ""
    private var password = ""

    fun authenticateUser(email: String, password: String) {
        retrofit.authenticateUser(email = email, password = password).execute().run {
            // TODO: I should mitigate multiple scenarios, like so is unavailable, or user credentials are invalid, etc
            fkey = body()?.string()?.parseFKey() ?: ""
            this@So.email = email
            this@So.password = password
        }
        println("Authenticating user!!!")
    }

    fun askQuestion(question: String) {
        if (fkey.isEmpty()) authenticateUser(email = email, password = password)
        retrofit.askQuestion(
            fkey = fkey,
            content = question,
            correlationId = UUID.randomUUID().toString(),
        ).execute().let {
            println(it.body())
        }
    }

}