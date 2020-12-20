package com.hajarslamah.newsapp
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.ViewModelProviders
//import java.util.*
//
//private const val ARG_New_ID = "new_id"
//class New_details : Fragment(){
//    private lateinit var new:NewsData
//    private lateinit var newsViewModel: NewsViewModel
//
//    companion object {
//        fun newInstance(newId: Int): New_details {
//            val args = Bundle().apply {
//                putSerializable(ARG_New_ID, newId)
//            }
//            return New_details().apply {
//                arguments = args
//            }
//        }
//    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        newsViewModel =
//            ViewModelProviders.of(this).get(NewsViewModel::class.java)
//        new = NewsData()
//        val crimeId: UUID = arguments?.getSerializable(ARG_CRIME_ID) as UUID
//        newsViewModel.(crimeId)
//        //  Log.d(TAG, "args bundle crime ID: $crimeId")
//        // Eventually, load crime from database    }
//    }
//}
