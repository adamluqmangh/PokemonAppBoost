package adam.pokemon.myapp.api.pokemonlist.repository

import adam.pokemon.myapp.api.pokemonlist.model.Ability
import adam.pokemon.myapp.api.pokemonlist.model.ApiResponsePokemonList
import adam.pokemon.myapp.api.pokemonlist.model.PokemonAbility
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

    private val _pokemonDetail = MutableLiveData<Response<PokemonAbility>>()
    val pokemonDetail: LiveData<Response<PokemonAbility>>
        get() = _pokemonDetail

    suspend fun getPokemon() = withContext(Dispatchers.IO){
        _pokemonData.postValue(Response.Loading())
        try{
            val result = pokemonAPI.getPokemon()
            if(result.body() !== null ){
                _pokemonData.postValue(Response.Success(result.body()))
            }else{
                _pokemonData.postValue(Response.Error(result.errorBody().toString()))
            }
        }catch(e: Exception) {
            _pokemonData.postValue(Response.Error(e.message.toString()))

        }
    }


    suspend fun getPokemonDetail(name: String) = withContext(Dispatchers.IO) {
        _pokemonDetail.postValue(Response.Loading())
        try {
            val result = pokemonAPI.getPokemonDetail(name)
            if (result.isSuccessful && result.body() !== null) {
                _pokemonDetail.postValue(Response.Success(result.body()))
            } else {
                _pokemonDetail.postValue(Response.Error(result.errorBody().toString()))
            }
        } catch (e: Exception) {
            _pokemonDetail.postValue(Response.Error(e.message.toString()))
        }
    }
}