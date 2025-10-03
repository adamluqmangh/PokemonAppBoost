package adam.pokemon.myapp.api.pokemonlist.model

data class ApiResponsePokemonList(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PokemonList>
)