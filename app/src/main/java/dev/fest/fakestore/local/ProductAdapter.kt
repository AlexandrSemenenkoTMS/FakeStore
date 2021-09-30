package dev.fest.fakestore.local

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.fest.fakestore.R
import dev.fest.fakestore.databinding.ItemProductBinding
import dev.fest.fakestore.dto.Product

class ProductAdapter(private val action: (Product) -> Unit) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private val productList = mutableListOf<Product>()

    class ViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    fun setProductList(list: List<Product>) {
        productList.clear()
        productList.addAll(list)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemProductBinding>(
            inflater,
            R.layout.item_product,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentProduct = productList[position]
        holder.binding.product = currentProduct

        holder.binding.root.setOnClickListener {
            action.invoke(currentProduct)
        }

        Glide
            .with(holder.binding.root)
            .load(currentProduct.image)
            .into(holder.binding.imageTitle)
    }

    override fun getItemCount() = productList.size
}