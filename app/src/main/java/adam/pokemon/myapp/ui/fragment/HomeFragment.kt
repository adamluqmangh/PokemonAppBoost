package adam.pokemon.myapp.ui.fragment

import adam.pokemon.myapp.R
import adam.pokemon.myapp.api.pokemonlist.model.PokemonList
import adam.pokemon.myapp.ui.adapter.PokemonAdapter
import adam.pokemon.myapp.utils.Response
import adam.pokemon.myapp.viewmodel.PokemonViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment: Fragment(), PokemonAdapter.OnItemClickListener {

    private lateinit var pokemonViewModel: PokemonViewModel
    private var pokemonRecyclerView: RecyclerView? =  null
    private lateinit var pokemonAdapter: PokemonAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonViewModel = ViewModelProvider(requireActivity())[PokemonViewModel::class.java]
        init()
        initViews(view)
        setupPokemonRecyclerView()
    }

    private fun initViews(view: View){
        pokemonRecyclerView = view.findViewById(R.id.pokemonRecyclerView)

    }

    fun init(){
        pokemonViewModel.pokemonData.observe(viewLifecycleOwner){
            when(it){
                is Response.Success -> {
                    Timber.tag("successful").e(it.data.toString())
                    pokemonAdapter = PokemonAdapter(it.data!!.results, this)
                }
                is Response.Loading -> {
                    Timber.tag("loading").e(it.data.toString())

                }
                is Response.Error -> {
                    Timber.tag("successful").e(it.data.toString())


                }
            }

        }
    }

    fun setupPokemonRecyclerView(){
        pokemonRecyclerView!!.layoutManager = GridLayoutManager(context, 3, RecyclerView.HORIZONTAL, false)
    }


    override fun onItemClick(item: PokemonList, position: Int) {
        TODO("Not yet implemented")
    }


}