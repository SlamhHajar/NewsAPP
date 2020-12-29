package com.hajarslamah.newsapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import java.util.*

private const val ARG_New_ID = "new_id"
class New_details : Fragment(){
    private lateinit var new:List<NewsData>
    lateinit var title :  TextView
    lateinit var detail :  TextView
    lateinit var date :  TextView
    private lateinit var newsViewModel: NewDetailViewModel

    companion object {
        fun newInstance(newId: Int): New_details {
            val args = Bundle().apply {
                putSerializable(ARG_New_ID, newId)
            }
            return New_details().apply {
                arguments = args
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsViewModel =
            ViewModelProviders.of(this).get(NewDetailViewModel::class.java)
      //  new = NewsData()
             val newId: Int = arguments?.getSerializable(ARG_New_ID) as Int

        newsViewModel.loadNewsDetails(newId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.new_detail, container, false)
       title=view.findViewById(R.id.new_name) as TextView
      detail=view.findViewById(R.id.new_detail) as TextView
          date=view.findViewById(R.id.date_details) as TextView

        return view
    }
    private fun updateUI() {
        title.text= new[id].title
        detail.text=new[id].details
        detail.text=new[id].date.toString()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel.newsListDetail.observe(
          this,
                Observer { newDetails ->
                    newDetails?.let {
                       this.new= newDetails
                        updateUI()
                    }
                })

    }


}

