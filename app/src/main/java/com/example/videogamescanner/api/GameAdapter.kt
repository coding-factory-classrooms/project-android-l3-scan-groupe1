package com.example.videogamescanner.api

import com.squareup.moshi.ToJson

class GameAdapter {
    @ToJson
    fun toJson(game: Game): String? {
        return game.title.toString()
    }

}