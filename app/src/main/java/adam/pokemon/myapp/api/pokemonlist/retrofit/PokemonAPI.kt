package adam.pokemon.myapp.api.pokemonlist.retrofit

import adam.pokemon.myapp.api.pokemonlist.model.Ability
import adam.pokemon.myapp.api.pokemonlist.model.AbilityDetail
import adam.pokemon.myapp.api.pokemonlist.model.ApiResponsePokemonList
import adam.pokemon.myapp.api.pokemonlist.model.PokemonAbility
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface PokemonAPI {
    @GET("api/v2/pokemon")
    suspend fun getPokemon(

    ): Response<ApiResponsePokemonList>

    @GET("api/v2/pokemon/{name}")
    suspend fun getPokemonDetail(
        @Path("name") name: String
    ): Response<PokemonAbility>

    @GET
    suspend fun getAbilityDetail(
        @Url url: String
    ): Response<AbilityDetail>
}