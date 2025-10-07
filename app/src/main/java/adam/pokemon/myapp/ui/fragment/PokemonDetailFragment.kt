package adam.pokemon.myapp.ui.fragment

import adam.pokemon.myapp.R
import adam.pokemon.myapp.api.pokemonlist.model.AbilityDetail
import adam.pokemon.myapp.api.pokemonlist.model.PokemonAbility
import adam.pokemon.myapp.utils.Response
import adam.pokemon.myapp.viewmodel.PokemonViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class PokemonDetailFragment: Fragment() {

    private lateinit var pokemonViewModel: PokemonViewModel
    private lateinit var pokemonImage: ImageView
    private lateinit var pokemonName: TextView
    private lateinit var pokemonAbilities: TextView
    private lateinit var backButton: ImageButton


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonViewModel = ViewModelProvider(requireActivity())[PokemonViewModel::class.java]
        initViews(view)
        initPokemonDetailsViewModel()
        initAbilityDetail()
    }

    fun initViews(view: View){
        pokemonImage = view.findViewById(R.id.pokemonImage)
        pokemonName = view.findViewById(R.id.pokemonName)
        pokemonAbilities = view.findViewById(R.id.pokemonAbilities)
        backButton = view.findViewById(R.id.backButton)
        backButtonOnClick()

    }

    fun initPokemonDetailsViewModel(){
        val name = arguments?.getString("pokemon_name") ?: return
        pokemonViewModel.loadPokemonDetail(name)
        pokemonViewModel.pokemonDetail.observe(viewLifecycleOwner) { response ->
            when(response) {
                is Response.Success -> {
                    response.data?.let { data ->
                        showPokemonDetails(name, data)
                        data.abilities.forEach { ability ->
                            pokemonViewModel.loadAbilityDetail(ability.ability.url)
                        }
                    }
                }
                is Response.Loading -> {
                    showLoadingState()
                }
                is Response.Error -> {
                    showErrorState(response.error)
                }
            }
        }

    }

    private fun initAbilityDetail() {
        pokemonViewModel.abilityDescriptions.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Response.Success -> {
                    showAbilityDetails(response.data!!)
                }
                is Response.Error -> {
                }
                is Response.Loading -> {

                }
            }
        }
    }


    private fun showLoadingState() {
        pokemonAbilities.text = getString(R.string.loading_pokemon_details)
        pokemonName.text = ""
        pokemonImage.setImageDrawable(null)
    }

    private fun showErrorState(error: Any?) {
        pokemonAbilities.text = "Error loading details: ${error ?: "Unknown error"}"
    }

    private fun showPokemonDetails(name: String, data: PokemonAbility) {
        val capitalizedName = name.replaceFirstChar { it.uppercase() }
        pokemonName.text = "Pokémon Name: $capitalizedName"

        val imageUrl = data.sprites.front_default
        Glide.with(requireContext())
            .load(imageUrl)
            .into(pokemonImage)
        pokemonAbilities.text = ""
    }

    private fun showAbilityDetails(detail: AbilityDetail){
        val abilityEffect = detail.effect_entries
            .firstOrNull { it.language.name == "en" }
            ?.effect ?: "No description available."

        val abilityName = detail.name.replaceFirstChar { it.uppercase() }
        pokemonAbilities.append("\n\n$abilityName:\n$abilityEffect")
    }

    fun backButtonOnClick(){
        backButton.setOnClickListener{
            parentFragmentManager.popBackStack()

        }
    }
}