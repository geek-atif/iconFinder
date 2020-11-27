package com.squareboat.iconfinder.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.squareboat.iconfinder.R
import com.squareboat.iconfinder.adapters.IconAdapter
import com.squareboat.iconfinder.constants.ConstantsApp
import com.squareboat.iconfinder.factory.IconFinderModelFactory
import com.squareboat.iconfinder.model.Icon
import com.squareboat.iconfinder.utils.UIEventManager
import com.squareboat.iconfinder.utils.Utils.Companion.toast
import com.squareboat.iconfinder.viewmodel.IconFinderViewModel
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Atif Qamar on 26-11-2020.
 */

class MainActivity : AppCompatActivity(), UIEventManager {
    private lateinit var iconFinderViewModel: IconFinderViewModel
    private lateinit var adapter: IconAdapter
    private lateinit var layoutManager: GridLayoutManager
    private val listItems = mutableListOf<Icon>()
    var defaultNumberOfIcons = ConstantsApp.DEFAULT_NUMBER_OF_ICONS
    var defaultIcon = ConstantsApp.DEFAULT_ICON

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadUI()
        addSearchBar()
        getIconFromServer()
        addOnScrollListener()
    }

    private fun loadUI() {
        val viewModelFactory = IconFinderModelFactory(this)
        iconFinderViewModel =
            ViewModelProvider(this, viewModelFactory).get(IconFinderViewModel::class.java)
        adapter = IconAdapter(listOf())
        recyclerview.setHasFixedSize(true)
        layoutManager = GridLayoutManager(this, 2)
        recyclerview.layoutManager = this.layoutManager
        recyclerview.adapter = this.adapter
    }

    private fun addSearchBar() {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchSV.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchSV.setOnQueryTextFocusChangeListener { view, b ->
        }

        searchSV.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isNullOrEmpty()) return false
                loadIconOnSearch(query)
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (query.isNullOrEmpty()) return false
                loadIconOnSearch(query)
                return true
            }
        })
    }

    private fun loadIconOnSearch(query: String) {
        removeAndReload()
        this@MainActivity.defaultIcon = query
        iconFinderViewModel.getIcons(query, defaultNumberOfIcons)
    }

    private fun getIconFromServer() {
        iconFinderViewModel.getIcons(defaultIcon, defaultNumberOfIcons)
        iconFinderViewModel.iconFinderResponse.observe(this, Observer {
            listItems.addAll(it.icons)
            adapter.submitList(listItems)
        })
    }

    private fun addOnScrollListener() {
        nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener
        { v: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if (v.getChildAt(v.childCount - 1) != null) {
                if (scrollY >= v.getChildAt(v.childCount - 1)
                        .measuredHeight - v.measuredHeight &&
                    scrollY > oldScrollY
                ) {
                    defaultNumberOfIcons += 20
                    iconFinderViewModel.getIcons(
                        this@MainActivity.defaultIcon,
                        defaultNumberOfIcons
                    )
                }
            }
        })
    }

    private fun removeAndReload() {
        listItems.clear()
        adapter.submitList(listOf())
    }

    override fun showToast(message: String) {
        toast(message, this)
    }

    override fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }
}
