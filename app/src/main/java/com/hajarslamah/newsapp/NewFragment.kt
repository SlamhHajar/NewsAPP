package com.hajarslamah.newsapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class NewFragment : Fragment() {
    interface Callbacks {
        fun onNewSelected(newId: Int)
    }
    private var callbacks: Callbacks? = null
    //////////////////////////////////////////@onAttach////////////
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?    }
    /////////////////////////////////////////////onDetach/////////////////////
    override fun onDetach() {
        super.onDetach()
        callbacks = null    }
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newsViewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsViewModel =
            ViewModelProviders.of(this).get(NewsViewModel::class.java)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel.newsLiveData.observe(
            viewLifecycleOwner,
            Observer {
                Log.d("receive", "Have erth items from ViewModel $it")
                newRecyclerView.adapter = NewsAdapter(it)
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_new, container, false)
       newRecyclerView = view.findViewById(R.id.news_recycler_view)
        newRecyclerView.layoutManager = LinearLayoutManager(context)
        return view 
    }
    private  inner  class NewsHolder(items: View )
        : RecyclerView.ViewHolder(items) ,View.OnClickListener {
private lateinit var new:NewsData
       val imgView=items.findViewById(R.id.img) as ImageView
       val titleView=items.findViewById(R.id.title_news) as TextView
       val dateView=items.findViewById(R.id.date) as TextView
       // val detialesView=items.findViewById(R.id.detail) as TextView
       init {
           items.setOnClickListener(this)
       }
       fun bindNew(new:NewsData){
           titleView.text=new.title
           dateView.text=new.date.toString()
         //  detialesView.text=new.details
       }

       override fun onClick(p0: View?) {
        //  Log.d("tag1","the id: ${new.ID} and title ${new.title} is clicked")

           callbacks?.onNewSelected(new.ID)
       }
   }
    private inner class NewsAdapter(private val newItems: List<NewsData>)
        : RecyclerView.Adapter<NewsHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
            val v=  LayoutInflater.from(parent.context).inflate(
                R.layout.list_news,
                parent,
                false
            )
            return NewsHolder(v)
        }

        override fun onBindViewHolder(holder: NewsHolder, position: Int) {
           val newItem=newItems[position]
            holder.bindNew(newItem)
        }

        override fun getItemCount(): Int {
         return  newItems.size
        }
    }
    companion object {

        @JvmStatic
        fun newInstance() =
            NewFragment()
    }
}