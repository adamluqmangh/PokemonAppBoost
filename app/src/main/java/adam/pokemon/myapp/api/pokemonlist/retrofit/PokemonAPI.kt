package adam.pokemon.myapp.api.pokemonlist.retrofit

import adam.pokemon.myapp.api.pokemonlist.model.ApiResponsePokemonList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonAPI {
    @GET("api/v2/pokemon")
    suspend fun getPokemon(

    ): Response<ApiResponsePokemonList>
}