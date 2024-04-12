package com.example.testeve

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.google.android.material.snackbar.Snackbar


open class Adapter
    (var context: Context, private var data: ArrayList<Item>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var isChecked: Boolean = false
    private var itemSelectedList = mutableListOf<Int>()
//    private val viewBinderHelper = ViewBinderHelper()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        viewBinderHelper.setOpenOnlyOne(true)
//        val item = data[position]
//        holder.images.setImageResource(item.icon)
//        holder.title.text = item.title
//        holder.subTitle.text = item.subTitle
//        holder.checkBox.isChecked = item.isChecked
//        holder.checkBox.visibility = if (this.isChecked) View.VISIBLE else View.GONE
//        holder.delete.setOnClickListener {
//            Snackbar.make(holder.itemView, "${item.title} deleted", Snackbar.LENGTH_SHORT).show()
//        }
//
//        holder.checkBox.setOnClickListener {
//            selectItem(position, item)
//        }
//
//        holder.mainLayout.setOnClickListener {
//            if (isChecked) {
//                selectItem(position, item)
//            } else {
//                Snackbar.make(holder.itemView, item.title, Snackbar.LENGTH_SHORT).show()
//            }
//        }
//
//        holder.mainLayout.setOnLongClickListener {
//            selectItem(position, item)
//            false
//        }

//        viewBinderHelper.bind(holder.swipeRevealLayout, item.subTitle)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        var images: ImageView
//        var title: TextView
//        var subTitle: TextView
//        var checkBox: CheckBox
//        var delete: ImageButton
//        var swipeRevealLayout: SwipeRevealLayout
//        var mainLayout: LinearLayout

//        init {
//            images = view.findViewById<View>(R.id.imageIcon) as ImageView
//            title = view.findViewById<View>(R.id.headline) as TextView
//            subTitle = view.findViewById<View>(R.id.subHeadline) as TextView
//            checkBox = view.findViewById<View>(R.id.checkBox) as CheckBox
//            delete = view.findViewById<View>(R.id.delete) as ImageButton
////            swipeRevealLayout = view.findViewById<View>(R.id.swipeRevealLayout) as SwipeRevealLayout
//            mainLayout = view.findViewById<View>(R.id.mainLayout) as LinearLayout
//        }
    }

    fun updateCheck(isChecked: Boolean) {
        this.isChecked = isChecked
        notifyDataSetChanged()
    }

    fun clearCheck() {
        itemSelectedList.clear()
        for (item: Item in data) {
            item.isChecked = false
        }
        Log.d("itemSelectedList", "items : ${itemSelectedList.toMutableList().sort()}")
        notifyDataSetChanged()
    }

    private fun selectItem(position: Int, item: Item) {
        isChecked = true
        if (itemSelectedList.contains(position)){
            itemSelectedList.remove(position)
            item.isChecked = false
        }else {
            itemSelectedList.add(position)
            item.isChecked = true
        }
        notifyDataSetChanged()
    }
}

