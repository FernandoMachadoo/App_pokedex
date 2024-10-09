package br.com.buscapokemon.data

import br.com.buscapokemon.data.api.PokemonAPI

class PokemonRepository(private val api: PokemonAPI) {
    suspend fun getPokemonData(id: Int): Pair<String, List<String>> {
        val response = api.getPokemon(id)
        val details = "Name: ${response.name}, Weight:${response.weight}, Height: ${response.height}"
        val sprites = listOfNotNull(
            response.sprites.front_default,
            response.sprites.back_default,
            response.sprites.front_shiny,
            response.sprites.back_shiny
        )
        return Pair(details, sprites)
    }
}