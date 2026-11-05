package com.example.amanturova_fatima_pi2_23_my_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import com.example.amanturova_fatima_pi2_23_my_list.databinding.FragmentHomeBinding
import com.example.amanturova_fatima_pi2_23_my_list.models.ItemModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var my_adapter: ContentAdapter

    private var list_positive: ArrayList<ItemModel> = arrayListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View { _binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list_positive = ArrayList(viewModel.getAllItems())
        my_adapter = ContentAdapter(requireContext(), list_positive)
        binding.recyclerView.adapter = my_adapter
        setUpOnBackPressed()
    }

    override fun onResume() {
        super.onResume()
        list_positive.clear()
        list_positive.addAll(viewModel.getAllItems())
        if (::my_adapter.isInitialized) {
            my_adapter.notifyDataSetChanged()
        }
    }

    fun setUpOnBackPressed() {
        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    if (isEnabled) {
                        requireActivity().finish()
                    }
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}