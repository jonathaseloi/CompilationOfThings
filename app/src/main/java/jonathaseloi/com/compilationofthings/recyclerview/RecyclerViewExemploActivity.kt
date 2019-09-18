package jonathaseloi.com.compilationofthings.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jonathaseloi.com.compilationofthings.R
import jonathaseloi.com.compilationofthings.recyclerview.model.Pessoa
import kotlinx.android.synthetic.main.activity_recycler_view_exemplo.*
import java.util.*


class RecyclerViewExemploActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var adapterRv: ExemploAdapter? = null
    private var mutableList: MutableList<Pessoa>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_exemplo)

        initDados()
        initRecyclerView()
    }

    private fun initDados() {
        mutableList = mutableListOf(Pessoa("João Pedro", "Pedreiro", Date()),
            Pessoa("Maria Gonçalvez",  "Enfermeira", Date()),
            Pessoa("Fatima Santos", "Médica", Date()),
            Pessoa("Augusto Silva", "Médico", Date()),
            Pessoa("Felipe Conrrado", "Programador", Date()),
            Pessoa("Ana Almeida", "Professora", Date()),
            Pessoa("Izadora Carvalho", "Modelo", Date()))
    }

    private fun initRecyclerView() {
        recyclerView = rv as RecyclerView?

        adapterRv = ExemploAdapter(this, R.layout.recyclerview_item)
        mutableList?.let { adapterRv!!.addAll(it) }

        recyclerView?.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(context)
            // set the custom adapter to the RecyclerView
            adapter = adapterRv
        }
    }
}
