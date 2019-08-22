package jonathaseloi.com.compilationofthings.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable

import java.util.ArrayList

abstract class BaseAdapter<T> @JvmOverloads constructor(
    val context: Context,
    protected val rootType: Int,
    private val withHeader: Boolean = false,
    private val withFooter: Boolean = false
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mData: MutableList<T>

    companion object {
        private const val TYPE_ITEM = 1
    }

    val dataCount: Int
        get() {
            return mData.size
        }

    val all: List<T>
        get() = mData

    init {
        this.mData = ArrayList()
    }

    protected abstract fun getItemView(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return getItemView(inflater, parent)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun getItemViewType(position: Int): Int {
        return TYPE_ITEM
    }

    private fun isPositionHeader(position: Int): Boolean {
        return position == 0
    }

    private fun isPositionFooter(position: Int): Boolean {
        return position == itemCount - 1
    }

    fun getItem(position: Int): T {
        var position = position
        position = getPosition(position)
        return mData[position]
    }

    fun add(data: T) {
        mData.add(data)
        notifyDataSetChanged()
    }

    fun add(position: Int, data: T) {
        var position = position
        position = getPosition(position)
        mData.add(position, data)
        notifyItemInserted(position)
    }

    fun addTop(data: T) {
        mData.add(0, data)
        notifyItemInserted(0)
    }

    open fun addAll(data: List<T>) {
        mData.addAll(data)
        notifyDataSetChanged()
    }

    fun addAll(position: Int, data: List<T>) {
        var position = position
        position = getPosition(position)
        mData.addAll(position, data)
        notifyDataSetChanged()
    }

    operator fun set(data: T, position: Int) {
        mData[position] = data
        notifyItemChanged(position, data)
    }

    fun remove(position: Int) {
        mData.removeAt(getPosition(position))
        notifyItemRemoved(position)
    }

    fun remove(data: T) {
        mData.remove(data)
        notifyDataSetChanged()
    }

    fun update(position: Int, data: T) {
        set(data, position)
        notifyItemChanged(position)
    }

    fun clear() {
        mData.clear()
        notifyDataSetChanged()
    }

    fun replace(position: Int, data: T) {
        remove(position)
        add(position, data)
        notifyItemChanged(position)
    }

    fun getPosition(position: Int): Int {
        return position
    }
}
