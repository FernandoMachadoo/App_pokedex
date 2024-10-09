package br.com.buscapokemon

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.buscapokemon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel : MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter : MainAdapter
    override fun onCreate (savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding .inflate( layoutInflater)
        setContentView( binding.root)
        mainAdapter = MainAdapter()
        binding.rvSprites .layoutManager = LinearLayoutManager( this,
            LinearLayoutManager .HORIZONTAL, false)
        binding.rvSprites .adapter = mainAdapter
        // Configurando o clique do botão de busca
        binding.btnSearch .setOnClickListener {
            val id = binding.etPokemonId .text.toString(). toIntOrNull()
            if (id != null) {
                viewModel .fetchPokemon( id) { details, sprites ->
                    binding.tvPokemonDetails .text = details
                    mainAdapter .submitList(sprites)
                }
            }
        }
    }
}