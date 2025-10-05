package adam.pokemon.myapp.api.pokemonlist.repository

import adam.pokemon.myapp.api.pokemonlist.model.ApiResponsePokemonList
import adam.pokemon.myapp.api.pokemonlist.retrofit.PokemonAPI
import adam.pokemon.myapp.utils.Response
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class PokemonRepository  @Inject constructor(
    private val pokemonAPI: PokemonAPI
){
    private val _pokemonData = MutableLiveData<Response<ApiResponsePokemonList>>()
    val pokemonData: LiveData<Response<ApiResponsePokemonList>>
        get() = _pokemonData

    suspend fun getPokemon() = withContext(Dispatchers.IO){
        _pokemonData.postValue(Response.Loading())
        try{
            val result = pokemonAPI.getPokemon()
            if(result.body() !== null ){
                _pokemonData.postValue(Response.Success(result.body()))
                Timber.tag("debug 3").e(result.body().toString())

            }else{
                _pokemonData.postValue(Response.Error(result.errorBody().toString()))
            }
        }catch(e: Exception) {
            _pokemonData.postValue(Response.Error(e.message.toString()))

        }
    }
}