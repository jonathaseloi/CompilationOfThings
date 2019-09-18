package jonathaseloi.com.compilationofthings.searchviewwithfilter

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import jonathaseloi.com.compilationofthings.R
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jonathaseloi.com.compilationofthings.recyclerview.ExemploAdapter
import jonathaseloi.com.compilationofthings.recyclerview.model.Pessoa
import kotlinx.android.synthetic.main.activity_searchview_with_filter.*
import java.util.*

class SearchViewWithFilterActivity : AppCompatActivity() {

    private var filterHide = true
    private var recyclerView: RecyclerView? = null
    private var adapterRv: ExemploAdapter? = null
    private var mutableList: MutableList<Pessoa>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchview_with_filter)

        initDados()
        initFilter()
        initRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_filter, menu)

        var searchItem = menu.findItem(R.id.action_search)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (searchItem.actionView as SearchView).apply {

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

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        super.onPrepareOptionsMenu(menu)
        try {
            val searchItem = menu.findItem(R.id.action_search)
            val filterItem = menu.findItem(R.id.action_filter)
            val searchView = searchItem.actionView as SearchView

            searchView.setOnSearchClickListener {
                filterItem.isVisible = filterHide
                filterHide = !filterHide
            }

            searchView.setOnCloseListener {
                filterItem.isVisible = filterHide
                filterHide = !filterHide

                hideFilter()
                false
            }

        } catch (e: Exception) {

        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_filter -> {
            showFilter()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun showFilter() {
        vFilter.visibility = if (vFilter.visibility == GONE) VISIBLE else GONE
    }

    private fun hideFilter() {
        vFilter.visibility = GONE
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

    private fun initFilter() {
        btnBuscar.setOnClickListener {
            val nome = etNome.text.toString()
            val profissao = etProfissao.text.toString()

            adapterRv?.searchFilter(nome, profissao)

            hideKeyboard(etNome)
        }
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

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
