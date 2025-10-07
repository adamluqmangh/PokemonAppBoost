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
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavoriteFragment: Fragment(), PokemonAdapter.OnItemClickListener {


    private var favoriteRecyclerView: RecyclerView? = null
    private lateinit var pokemonAdapter: PokemonAdapter
    private lateinit var pokemonViewModel: PokemonViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonViewModel = ViewModelProvider(requireActivity())[PokemonViewModel::class.java]
        initViews(view)
        initFavorite()
        setupRecyclerView()
    }

    private fun initViews(view: View){
        favoriteRecyclerView = view.findViewById(R.id.favouriteRecyclerView)

    }

    fun initFavorite(){
        pokemonViewModel.favourites.observe(viewLifecycleOwner) { favorites ->
            pokemonAdapter = PokemonAdapter(favorites, this, pokemonViewModel)
            val pokemonAdapters = PokemonAdapter(favorites, this, pokemonViewModel)
            favoriteRecyclerView!!.adapter = pokemonAdapters
        }


    }

    fun setupRecyclerView(){
        favoriteRecyclerView?.layoutManager = LinearLayoutManager(context,  RecyclerView.VERTICAL, false)
        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.recycler_divider_white)
        if (drawable != null) {
            divider.setDrawable(drawable)
        }
        favoriteRecyclerView!!.addItemDecoration(divider)
    }

    override fun onItemClick(item: PokemonList, position: Int) {

    }
}

