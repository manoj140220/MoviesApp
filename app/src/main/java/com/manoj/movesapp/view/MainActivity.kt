package com.manoj.movesapp.view

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.manoj.movesapp.R
import com.manoj.movesapp.model.response.ResponseMain
import com.manoj.movesapp.view.adapter.MoviesListAdapter
import com.manoj.movesapp.viewmodel.MoviesApiViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var moviesApiViewModel: MoviesApiViewModel
    var searchText : EditText?= null
    var moviesListAdapter : MoviesListAdapter? = null
    var responseMain : ResponseMain? = null
    var moviesRecyclerView : RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moviesRecyclerView = findViewById(R.id.movies_list)
        moviesRecyclerView!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        moviesApiViewModel = ViewModelProvider(this).get(MoviesApiViewModel::class.java)

        searchText = findViewById(R.id.search_edit_text)
        searchText!!.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                /**
                 * DO Nothing */
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                /**
                 * DO Nothing */
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(searchText!!.text.length > 3){
                    searchMovies(searchText!!.text.toString())
                }
            }

        })

        /**
         * Default Search
         * */
        searchMovies("friends");

        check_connection.setOnClickListener {
            if(isNetworkConnected()){
                /**
                 * Display Search View
                 * */
                no_internet_screen.visibility = View.GONE
                shimmer_view_container.visibility = View.VISIBLE
                moviesRecyclerView!!.visibility = View.VISIBLE
                no_data_text.visibility = View.VISIBLE

                if(searchText!!.text.isNotEmpty()){
                    /**
                     * Load Data from search
                     * */
                    searchMovies(searchText!!.text.toString());
                }else{
                    /**
                     * Default Search
                     * */
                    searchMovies("friends");
                }
            }
        }
    }

    private fun searchMovies(searchTerm: String) {
        if(!isNetworkConnected()){
            /**
             * Display No Internet Screen
             * */
            no_internet_screen.visibility = View.VISIBLE
            shimmer_view_container.visibility = View.GONE
            moviesRecyclerView!!.visibility = View.GONE
            no_data_text.visibility = View.GONE
            return
        }

        moviesRecyclerView!!.visibility = View.GONE
        shimmer_view_container.startShimmerAnimation()
        shimmer_view_container.visibility = View.VISIBLE

        moviesApiViewModel.getMovies(
            searchTerm,
            getString(R.string.api_key)).observe(
            this, Observer {
                    responseData ->
                shimmer_view_container.stopShimmerAnimation()
                shimmer_view_container.visibility = View.GONE
                moviesRecyclerView!!.visibility = View.VISIBLE

                if(responseData?.search != null){
                    no_data_text.visibility = View.GONE
                    /**
                     * Display Data Here In Adapter.
                     * */
                    responseMain = null
                    responseMain = responseData
                    moviesListAdapter = MoviesListAdapter(responseMain, this)
                    moviesRecyclerView!!.adapter = moviesListAdapter
                }else{
                    displayErrorScreen()
                }

            })

        moviesApiViewModel.getErrorData().observe(this,
            Observer { errorData ->
                if(!errorData.equals("Canceled")){
                    Toast.makeText(this@MainActivity, errorData, Toast.LENGTH_SHORT).show()
                }
        })
    }

    private fun displayErrorScreen() {
        /**
         * Remove Main View and display No data View.
         * */
        no_data_text.visibility = View.VISIBLE
        moviesRecyclerView!!.visibility = View.GONE
        shimmer_view_container.stopShimmerAnimation()
        shimmer_view_container.visibility = View.GONE
    }

    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }
}