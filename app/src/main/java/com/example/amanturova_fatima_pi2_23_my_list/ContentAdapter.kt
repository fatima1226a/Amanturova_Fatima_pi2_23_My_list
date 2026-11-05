package com.example.amanturova_fatima_pi2_23_my_list

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.amanturova_fatima_pi2_23_my_list.databinding.ItemBinding
import com.example.amanturova_fatima_pi2_23_my_list.models.ItemModel

class ContentAdapter (var context: Context,
                      var list: ArrayList<ItemModel>)
    : RecyclerView.Adapter<ContentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContentAdapter.ViewHolder {
        val binding = ItemBinding.inflate(
            LayoutInflater.from(context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContentAdapter.ViewHolder, position: Int) {
        holder.bind(list.get(position))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(var binding: ItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ItemModel) {
            binding.newsId.text = item.news_title
            binding.newsText.text = item.simple_text

            val bitmap = BitmapFactory.decodeByteArray(
                item.news_img, 0,
                item.news_img.size)

            binding.newsImage.setImageBitmap(bitmap)
        }

    }

}