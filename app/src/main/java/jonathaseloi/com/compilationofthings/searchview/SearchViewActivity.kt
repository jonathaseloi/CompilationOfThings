package jonathaseloi.com.compilationofthings.searchview

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jonathaseloi.com.compilationofthings.R
import jonathaseloi.com.compilationofthings.recyclerview.ExemploAdapter
import jonathaseloi.com.compilationofthings.recyclerview.model.Pessoa
import kotlinx.android.synthetic.main.activity_recycler_view_exemplo.*
import java.util.*

class SearchViewActivity : AppCompatActivity() {

    //other search bar
//    https://www.memory-lovers.blog/entry/2018/09/04/164634

    //
//    https://developer.android.com/guide/topics/search/adding-custom-suggestions.html?hl=pt-br

    private var recyclerView: RecyclerView? = null
    private var adapterRv: ExemploAdapter? = null
    private var mutableList: MutableList<Pessoa>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchbar)

        initDados()
        initRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.action_search).actionView as SearchView).apply {

            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setIconifiedByDefault(true)

            //Search By Pessoa.nome
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    adapterRv?.search(query)
                    return false
                }

                override fun onQueryTextChange(query: String): Boolean {
                    adapterRv?.search(query)
                    return false
                }
            })
        }
        return true
    }

    private fun initDados() {
        mutableList = mutableListOf(
            Pessoa("João Pedro", "Pedreiro", Date()),
            Pessoa("Maria Gonçalvez",  "Enfermeira", Date()),
            Pessoa("Fatima Santos", "Médica", Date()),
            Pessoa("Augusto Silva", "Médico", Date()),
            Pessoa("Felipe Conrrado", "Programador", Date()),
            Pessoa("Ana Almeida", "Professora", Date()),
            Pessoa("Izadora Carvalho", "Modelo", Date())
        )
    }

    private fun initRecyclerView() {
        recyclerView = rv as RecyclerView?

        adapterRv = ExemploAdapter(this, R.layout.recyclerview_item)
        mutableList?.let { adapterRv!!.addList(it) }

        recyclerView?.apply {

            layoutManager = LinearLayoutManager(context)
            adapter = adapterRv
        }
    }
}