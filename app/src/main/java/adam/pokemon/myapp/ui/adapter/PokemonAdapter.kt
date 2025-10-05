package adam.pokemon.myapp.ui.adapter

import adam.pokemon.myapp.R
import adam.pokemon.myapp.api.pokemonlist.model.PokemonList
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class PokemonAdapter(
    val pokemonData: List<PokemonList>,
    val pokemonClickListener: OnItemClickListener
): RecyclerView.Adapter<PokemonAdapter.PokemonAdapterViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: PokemonList, position: Int)
    }

    inner class PokemonAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val pokemonName: TextView = itemView.findViewById(R.id.pokemon_name)

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

    }
}