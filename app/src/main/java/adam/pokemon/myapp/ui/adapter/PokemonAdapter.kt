package adam.pokemon.myapp.ui.adapter

import adam.pokemon.myapp.R
import adam.pokemon.myapp.api.pokemonlist.model.PokemonList
import adam.pokemon.myapp.viewmodel.PokemonViewModel
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class PokemonAdapter(
    val pokemonData: List<PokemonList>,
    val pokemonClickListener: OnItemClickListener,
    private val viewModel: PokemonViewModel

): RecyclerView.Adapter<PokemonAdapter.PokemonAdapterViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: PokemonList, position: Int)
    }

    inner class PokemonAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val pokemonName: TextView = itemView.findViewById(R.id.pokemon_name)
        val pokemonFavoriteButton: ImageView = itemView.findViewById(R.id.favorite_button)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_list_rv_card, parent, false)
        return PokemonAdapterViewHolder(view)
    }


    override fun getItemCount(): Int = pokemonData.size


    override fun onBindViewHolder(holder: PokemonAdapterViewHolder, position: Int) {
        val currentPokemon = pokemonData[position]
        val currentPokemonName = "${position + 1}." + currentPokemon.name

        holder.pokemonName.text = currentPokemonName

        val updateFavoriteIcon = if (viewModel.isFavourite(currentPokemon)) {
            R.drawable.favorite_icon
        } else {
            R.drawable.favourite_icon_outline
        }
        holder.pokemonFavoriteButton.setImageResource(updateFavoriteIcon)

        holder.pokemonFavoriteButton.setOnClickListener {
            viewModel.toggleFavourite(currentPokemon)
            notifyItemChanged(position)
        }

    }
}