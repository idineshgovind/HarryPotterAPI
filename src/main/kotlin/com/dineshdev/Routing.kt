package com.dineshdev

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.nio.file.Paths


fun Application.configureRouting() {
    routing {
        val db = loadDatabase("/Users/dinesh-22000/Desktop/Server/hp/src/main/kotlin/com/dineshdev/db.json")


        get("/") {
            call.respondText("""{"message": "Welcome to the KMP JSON Server"}""")
        }

        get("api/spells") {
            call.respond(db.spells)
        }

        get("api/info") {
            call.respond(db.info)
        }

        get("api/books") {
            call.respond(db.books)
        }

        get("api/characters") {
            call.respond(db.characters)
        }
    }
}

// Define data models to represent JSON structure
@Serializable
data class Spell(
    val id: Int,
    val spell: String,
    val use: String
)

@Serializable
data class Info(
    val id: Int,
    val type: String,
    val content: String
)

@Serializable
data class Book(
    val id: Int,
    val title: String,
    val releaseDay: String,
    val author: String,
    val description: String
)

@Serializable
data class Character(
    val id: Int,
    val character: String,
    val nickname: String,
    val hogwartsStudent: Boolean,
    val hogwartsHouse: String,
    val interpretedBy: String,
    val child: List<String>,
    val image: String
)

@Serializable
data class Database(
    val spells: List<Spell>,
    val info: List<Info>,
    val books: List<Book>,
    val characters: List<Character>
)

// Function to load and parse the database
fun loadDatabase(fileName: String): Database {
    val fileContent = File(fileName).readText()
    return Json.decodeFromString(Database.serializer(), fileContent)
}

