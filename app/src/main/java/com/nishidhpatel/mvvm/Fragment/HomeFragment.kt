package com.nishidhpatel.mvvm.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import com.nishidhpatel.mvvm.ApplicationClass

import com.nishidhpatel.mvvm.R
import com.nishidhpatel.mvvm.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout
import com.nishidhpatel.mvvm.fragment.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var activity: AppCompatActivity

    private lateinit var fragmentManger: androidx.fragment.app.FragmentManager


    private lateinit var storeListFragment: Fragment
    private lateinit var profileFragment: Fragment
    private lateinit var activeFragment: Fragment

    private val tabIcons = intArrayOf(
        R.drawable.menu_list_selector,
        R.drawable.menu_person_selector
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        var view: View = binding.root
        activity = ApplicationClass.mInstance!!.getactivity()!!
        initComponents(view)
        return view

    }

    override fun initComponents(rootView: View) {
        setTabText()
        storeListFragment = ListFragment()
        profileFragment = ProfileFragment()
        activeFragment = storeListFragment

        fragmentManger = getActivity()!!.supportFragmentManager


        fragmentManger.beginTransaction().add(R.id.fameLayout, storeListFragment, "1").commit()
        fragmentManger.beginTransaction().add(R.id.fameLayout, profileFragment, "2")
            .hide(profileFragment).commit()

        binding.fragmentMainTlHome.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {

            override fun onTabSelected(postion: TabLayout.Tab?) {

                if (postion!!.position == 0) {
                    var fargmentTransction: FragmentTransaction = fragmentManger.beginTransaction()
                    fargmentTransction.setCustomAnimations(
                        R.animator.slide_fragment_horizontal_right_in,
                        R.animator.slide_fragment_horizontal_left_out,
                        R.animator.slide_fragment_horizontal_left_in,
                        R.animator.slide_fragment_horizontal_right_out
                    )

                    fargmentTransction.hide(activeFragment).show(storeListFragment).commit()
                    activeFragment = storeListFragment
                } else if (postion.position == 1) {
                    var fargmentTransction: FragmentTransaction = fragmentManger.beginTransaction()
                    fargmentTransction.setCustomAnimations(
                        R.animator.slide_fragment_horizontal_right_in,
                        R.animator.slide_fragment_horizontal_left_out,
                        R.animator.slide_fragment_horizontal_left_in,
                        R.animator.slide_fragment_horizontal_right_out
                    )

                    fargmentTransction.hide(activeFragment).show(profileFragment).commit()
                    activeFragment = profileFragment

                }

            }
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

        })



    }

    private fun setTabText() {

        binding.fragmentMainTlHome.addTab(
            binding.fragmentMainTlHome.newTab().setText("List").setIcon(
                tabIcons[0]
            )
        )
        binding.fragmentMainTlHome.addTab(
            binding.fragmentMainTlHome.newTab().setText("Profile").setIcon(
                tabIcons[1]
            )
        )
    }


}
