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
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
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
    private lateinit var pokemonListText: TextView
    private var ascending = true // toggle for sort order
    private lateinit var pokemonSortButton: ImageButton



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
        pokemonSortButton.setOnClickListener{
            toggleSort()
        }
    }

    private fun initViews(view: View){
        pokemonRecyclerView = view.findViewById(R.id.pokemonRecyclerView)
        pokemonListText = view.findViewById(R.id.pokemonListText)
        pokemonSortButton = view.findViewById(R.id.pokemonSortButton)

    }

    fun init(){
        pokemonViewModel.pokemonData.observe(viewLifecycleOwner){
            when(it){
                is Response.Success -> {
                    val sortedList = if (ascending) {
                        it.data!!.results.sortedBy { it.name.lowercase() }
                    } else {
                        it.data!!.results.sortedByDescending { it.name.lowercase() }
                    }
                    pokemonAdapter = PokemonAdapter(sortedList, this, pokemonViewModel)
                    val pokemonAdapters = PokemonAdapter(sortedList, this, pokemonViewModel)
                    pokemonRecyclerView!!.adapter = pokemonAdapters
                }
                is Response.Loading -> {

                }
                is Response.Error -> {

                }
            }

        }
    }

    fun setupPokemonRecyclerView(){
        pokemonRecyclerView!!.layoutManager = LinearLayoutManager(context,  RecyclerView.VERTICAL, false)
        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.recycler_divider_white)
        if (drawable != null) {
            divider.setDrawable(drawable)
        }
        pokemonRecyclerView!!.addItemDecoration(divider)
    }


    override fun onItemClick(item: PokemonList, position: Int) {
        TODO("Not yet implemented")
    }

    fun toggleSort() {
        ascending = !ascending
        init() // reapply sort with new order
    }


}