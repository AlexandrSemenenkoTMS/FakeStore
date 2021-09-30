package dev.fest.fakestore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import dev.fest.fakestore.R
import dev.fest.fakestore.databinding.FragmentProductBinding
import dev.fest.fakestore.dto.Product

class ProductFragment(private val product: Product) : Fragment() {

    private lateinit var binding: FragmentProductBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product, container, false)
        binding.product = product

//        (activity as FakeStoreActivity).supportActionBar?.setDisplayShowHomeEnabled(true)

        Glide
            .with(binding.root)
            .load(product.image)
            .into(binding.imageTitleInfoScreen)

        return binding.root
    }

}