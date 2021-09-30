package dev.fest.fakestore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import dev.fest.fakestore.R
import dev.fest.fakestore.databinding.FragmentListProductBinding
import dev.fest.fakestore.dto.Product
import dev.fest.fakestore.local.ProductAdapter
import dev.fest.fakestore.viewmodel.MainViewModel

class ListProductFragment(private val showProductInfo: (Product) -> Unit) : Fragment() {

    private lateinit var binding: FragmentListProductBinding
    private lateinit var viewModel: MainViewModel
    private val productsAdapter by lazy { ProductAdapter(showProductInfo) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_list_product, container, false)
        viewModel =
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
                .create(MainViewModel::class.java)
        binding.viewModel = viewModel
//        binding.lifecycleOwner = this
        setListAdapter()
        setProductsListener()
        observeData()
        return binding.root
    }

    private fun setProductsListener() {
        viewModel.productListLiveData.observe(viewLifecycleOwner, Observer {
            productsAdapter.setProductList(it)
        })
    }

    private fun setListAdapter() {
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = productsAdapter
        }
    }

    private fun observeData() {
        viewModel.productListLiveData.observe(requireActivity(), {
            productsAdapter.setProductList(it)
        })
        viewModel.errorCodeLiveData.observe(requireActivity(), {
            Snackbar.make(binding.root, "Error $it", Snackbar.LENGTH_LONG).show()
        })
    }
}