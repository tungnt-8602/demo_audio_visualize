package com.example.testeve

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Random


class MainActivity : AppCompatActivity() {
//    private var swipeRefreshLayout: SwipeRefreshLayout? = null
//    private var recyclerView: RecyclerView? = null
//    private var close: ImageView? = null
//    private var adapter: Adapter? = null
//
//    private var data: ArrayList<Item> = ArrayList(mutableListOf(
//        Item("Facebook", "Meta", R.drawable.ic_link, false),
//        Item("Twitter", "Elon Musk", R.drawable.ic_linear, false),
//        Item("Instagram", "Facebook", R.drawable.ic_news, false),
//        Item("LinkedIn", "LinkedIn", R.drawable.ic_more, false),
//        Item("Youtube", "Youtube", R.drawable.ic_next, false),
//        Item("Whatsapp", "Whatsapp", R.drawable.ic_pass, false),
//    ))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        // Getting reference of swipeRefreshLayout and recyclerView
////        swipeRefreshLayout = findViewById<View>(R.id.swipeRefreshLayout) as SwipeRefreshLayout
//        recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
//        close = findViewById<View>(R.id.close) as ImageView
//
//        // Setting the layout as Linear for vertical orientation to have swipe behavior
//        val linearLayoutManager = LinearLayoutManager(applicationContext)
//        recyclerView!!.layoutManager = linearLayoutManager
//
//        // Sending reference and data to Adapter
//        adapter = Adapter(this@MainActivity, data)
//
//        // Setting Adapter to RecyclerView
//        recyclerView!!.adapter = adapter
//
//        // SetOnRefreshListener on SwipeRefreshLayout
////        swipeRefreshLayout!!.setOnRefreshListener {
////            swipeRefreshLayout!!.isRefreshing = false
////            rearrangeItems()
////        }
//
//        close!!.setOnClickListener {
//            adapter!!.updateCheck(false)
//            adapter!!.clearCheck()
//        }
    }

//    private fun rearrangeItems() {
//        // Shuffling the data of ArrayList using system time
//        data.shuffle(Random(System.currentTimeMillis()))
//        adapter = Adapter(this@MainActivity, data)
//        recyclerView!!.adapter = adapter
//    }
}