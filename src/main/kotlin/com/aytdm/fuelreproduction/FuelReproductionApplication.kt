package com.aytdm.fuelreproduction

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.result.Result
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.MediaType

@SpringBootApplication
class FuelReproductionApplication : CommandLineRunner {
  override fun run(vararg args: String?) {
    val (_, response, result) = Fuel.get(URL).responseString()

    when (result) {
      is Result.Failure -> {
        val ex = result.getException()
        println(ex)
      }
      is Result.Success -> {
        println("-------------------")
        println("Body.asString(\"application/json\"): ${response.body().asString(MediaType.APPLICATION_JSON_VALUE)}")
        println("Body.asString(\"application/json;charset=UTF-8\"): ${response.body().asString(MediaType.APPLICATION_JSON_UTF8_VALUE)}")
        println("-------------------")
      }
    }
  }

  companion object {
    private const val URL = "https://fuel-reproduction-1n8ja4ht.netlify.app/.netlify/functions/greet"
  }
}

fun main(args: Array<String>) {
  runApplication<FuelReproductionApplication>(*args)
}
