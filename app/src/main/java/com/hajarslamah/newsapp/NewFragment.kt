package com.hajarslamah.newsapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewFragment : Fragment() {
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
   private  class NewsHolder(items: View )
        : RecyclerView.ViewHolder(items) ,View.OnClickListener {
       val imgView=items.findViewById(R.id.img) as TextView
       val titleView=items.findViewById(R.id.title_news) as TextView
       val dateView=items.findViewById(R.id.date) as TextView
       init {
           items.setOnClickListener(this)
       }
       fun bindNew(new:NewsData){
           titleView.text=new.title
           dateView.text=new.date.toString()
       }

       override fun onClick(p0: View?) {
           TODO("Not yet implemented")
       }
   }
    private class NewsAdapter(private val newItems: List<NewsData>)
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