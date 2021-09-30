package dev.fest.fakestore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.fest.fakestore.databinding.ActivityFakestoreBinding
import dev.fest.fakestore.dto.Product

class FakeStoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFakestoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFakestoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListProductsFragment()
    }

    private fun setListProductsFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.container.id, ListProductFragment { showProductInfo(it) })
            .commit()
    }

    private fun showProductInfo(product: Product) {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.container.id, ProductFragment(product))
            .addToBackStack(null)
            .commit()
    }

}