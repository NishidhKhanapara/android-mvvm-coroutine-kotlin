package com.nishidhpatel.mvvm.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nishidhpatel.mvvm.ApplicationClass

import com.nishidhpatel.mvvm.R
import com.nishidhpatel.mvvm.Utils.Utils
import com.nishidhpatel.mvvm.databinding.FragmentListBinding
import com.nishidhpatel.mvvm.mvvm.list.MuseumListViewModel
import com.nishidhpatel.kotlinstructurecorotines.Adapter.ListAdapter
import com.nishidhpatel.mvvm.fragment.BaseFragment
import com.nishidhpatel.mvvm.models.Museums.Data
import com.nishidhpatel.mvvm.roomdatabase.DatabaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : BaseFragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var activity: AppCompatActivity
    private val storeListViewModel : MuseumListViewModel by viewModel()
    private lateinit var adapter: ListAdapter

    //roomdatabase
    private lateinit var databaseViewModel: DatabaseViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        var view: View = binding.root
        binding?.viewModel=storeListViewModel
        activity = ApplicationClass.mInstance!!.getactivity()!!
        initComponents(view)
        return view
    }

    override fun initComponents(rootView: View) {
        roomDatabaseInit()
        getList()
        setUpUi()
        setUpObserver()

    }

    private fun roomDatabaseInit() {
        databaseViewModel = this.getActivity()?.let {
            ViewModelProviders.of(it).get(DatabaseViewModel::class.java)
        }!!
    }

    private fun setUpObserver() {
        storeListViewModel.showloding.observe(this, Observer {
            if(!it)
                binding.progressBar.visibility=View.GONE
        })
        storeListViewModel.storeData.observe(this, Observer {data->
            for (data in data.data!!) {
                databaseViewModel.insert(data)
            }
            data!!.data?.let { retrieveList(it) }

        })
    }

    private fun setUpUi() {
        binding.rvList.layoutManager= LinearLayoutManager(activity)
        adapter= ListAdapter(arrayListOf())
        binding.rvList.adapter = adapter
    }

    private fun getList() {
        if (Utils.isOnline(activity, true)) {
            storeListViewModel.getList()
        }else{
            binding.progressBar.visibility=View.GONE
            databaseViewModel.allStoreList!!.observe(this, Observer { words ->
                // Update the cached copy of the words in the adapter.
                words?.let {
                    retrieveList(it)
                }
            })
        }

    }
    private fun retrieveList(list: List<Data>) {
        adapter.apply {
            addUsers(list)
            notifyDataSetChanged()

        }
    }

}
