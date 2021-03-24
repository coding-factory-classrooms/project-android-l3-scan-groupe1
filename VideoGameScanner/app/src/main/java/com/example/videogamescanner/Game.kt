package com.example.videogamescanner

data class Game(val name: String,
                val cover: String,
                val gtin: String,
                val release: String,
                val type: String,
                val plateform: String)

fun createGame(): Game {
    return Game(name="Spider-Man Miles Morales", "", "", "12/11/2020", "Jeu d'action-aventure","PS5 / PS4")
}