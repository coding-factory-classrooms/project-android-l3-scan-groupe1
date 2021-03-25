package com.example.videogamescanner

data class Game(val name: String,
                val coverUrl: String,
                val release: String,
                val rate: String,
                val publisher: String)

fun createGame(): Game {
    return Game(name="Spider-Man Miles Morales", "", "12/11/2020", "","")
}