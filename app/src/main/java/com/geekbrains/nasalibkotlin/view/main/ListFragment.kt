package com.geekbrains.nasalibkotlin.view.main

import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.geekbrains.nasalibkotlin.R
import com.geekbrains.nasalibkotlin.databinding.FragmentListBinding
import com.geekbrains.nasalibkotlin.model.entity.NasaResponse
import com.geekbrains.nasalibkotlin.presenter.ListPresenter
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class ListFragment : MvpAppCompatFragment(), ListView {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private var columns = 2
    private val stdQuery = "hubble"
    private var listRVA: ListRVA? = null
    @InjectPresenter
    lateinit var listPresenter : ListPresenter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        columns = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 3 else 2
        initToolbar()
        initRecycler()
        listPresenter.requestFromServer(stdQuery)
    }

    private fun initRecycler() {
        val gridLayoutManager = GridLayoutManager(context, columns)
        binding.mainRV.layoutManager = gridLayoutManager
        listRVA = ListRVA()
        binding.mainRV.adapter = listRVA
    }

    override fun updateRecyclerView(nasaResponse: NasaResponse?) {
        if (nasaResponse?.collection?.items != null) {
            nasaResponse.collection.items.let { emptyResultMessage(it.isEmpty()) }
            nasaResponse.collection.items.let { listRVA!!.setMedia(it) }
            listRVA!!.notifyDataSetChanged()
        }
    }

    private fun initToolbar(){
        (activity as AppCompatActivity).setSupportActionBar(binding.mainToolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar_main, menu)

        val searchViewItem: MenuItem? = menu.findItem(R.id.menu_search)
        val searchView: SearchView = searchViewItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                listPresenter.requestFromServer(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        val closeButton = searchView.findViewById<ImageView>(R.id.search_close_btn)
        val et = searchView.findViewById<EditText>(R.id.search_src_text)
        closeButton.setOnClickListener { _ ->
            val query = et.text.toString()
            if ("" == query) {
                searchView.onActionViewCollapsed()
                searchViewItem.collapseActionView()
            } else {
                et.setText("")
            }
        }

        val aboutItem = menu.findItem(R.id.about)
        aboutItem.setOnMenuItemClickListener {
            findNavController().navigate(R.id.action_listFragment_to_aboutFragment)
            false
        }

        val settingsItem = menu.findItem(R.id.menu_settings)
        settingsItem.setOnMenuItemClickListener { _ ->
            Toast.makeText(context, R.string.not_work_button, Toast.LENGTH_SHORT).show()
            false
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun emptyResultMessage(empty: Boolean) {
        if (empty) {
            binding.emptyResult.visibility = View.VISIBLE
            binding.mainRV.visibility = View.GONE
        } else {
            binding.emptyResult.visibility = View.GONE
            binding.mainRV.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}