package com.example.smartwop.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.smartwop.CategoriesProdctsActivity2
import com.example.smartwop.databinding.CategoryItemLayoutBinding
import com.example.smartwop.model.CategoryModel
import com.example.smartwop.utils.Constans

//interface CategoryAdapterCallback{
//    fun onClick(item: CategoryModel)
//}
class CategoryAdapter(val items: List<CategoryModel>): RecyclerView.Adapter<CategoryAdapter.ItemHolder>() {
    inner class ItemHolder(val binding: CategoryItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            CategoryItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]
        holder.itemView.setOnClickListener {
//            items.forEach {
//                it.checked = false
//            }
//
//            item.checked = true
            val intent = Intent(holder.itemView.context, CategoriesProdctsActivity2::class.java)
            intent.putExtra("pBC", item)
            holder.itemView.context.startActivity(intent)
//            notifyDataSetChanged()
        }

        holder.binding.tvCategory.text = item.title
        Glide.with(holder.itemView.context).load(Constans.HOST_IMAGE + item.icon)
            .into(holder.binding.imgCategory)
//        if (item.checked){
//            holder.itemView.cardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.colorPrimary))
//            holder.binding.tvCategory.setTextColor(Color.WHITE)
//        }else{
//            holder.itemView.cardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.grey))
//            holder.binding.tvCategory.setTextColor(Color.BLACK)
//        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}