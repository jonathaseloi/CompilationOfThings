package jonathaseloi.com.compilationofthings.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import jonathaseloi.com.compilationofthings.recyclerview.model.Pessoa
import android.widget.TextView
import jonathaseloi.com.compilationofthings.R
import java.text.SimpleDateFormat

open class ExemploAdapter(context: Context, rootType: Int) : BaseAdapter<Pessoa>(context, rootType, true, true) {

    private var mDataSearchList: MutableList<Pessoa> = ArrayList()

    fun addList(data: List<Pessoa>) {
        super.addAll(data)
        mDataSearchList = data as MutableList<Pessoa>
    }

    override fun getItemView(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder {
        return ItemViewHolder(inflater.inflate(rootType, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ItemViewHolder

        val pessoa = getItem(position)

        var format = SimpleDateFormat("dd/MM/yyy")

        viewHolder.tvNome!!.text = pessoa.nomeCompleto
        viewHolder.tvText!!.text = "Profissão: ${pessoa.profissao}\nData de Nascimento: ${format.format(pessoa.dataNascimento)}\nEstado Civil: Casado(a)"

    }

    inner class ItemViewHolder constructor(v: View) :
        RecyclerView.ViewHolder(v) {

        var tvNome: TextView? = v.findViewById(R.id.tvNome)
        var tvText: TextView? = v.findViewById(R.id.tvText)
    }

    //Offline search
    fun search(constraint: String?) {
        clear()
        if (constraint.isNullOrBlank()) {
            addList(mDataSearchList)
        } else {
            val searchResults = mDataSearchList.filter { it.nomeCompleto.contains(constraint, true) }
            addAll(searchResults)
        }
    }

    fun searchFilter(nome: String?, profissao: String?) {
        clear()
        if (nome.isNullOrBlank() && profissao.isNullOrBlank()) {
            addList(mDataSearchList)
        } else {
            var searchResults = mDataSearchList

            if (!nome.isNullOrBlank()) {
                searchResults = searchResults.filter {
                    it.nomeCompleto.contains(nome, true)
                } as ArrayList<Pessoa>
            }

            if (!profissao.isNullOrBlank()) {
                searchResults = searchResults.filter {
                    it.profissao.contains(profissao, true)
                } as ArrayList<Pessoa>
            }
            addAll(searchResults)
        }
    }
}
