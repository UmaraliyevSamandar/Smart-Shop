package com.example.smartwop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.smartwop.databinding.ActivityCategoriesProdcts2Binding
import com.example.smartwop.fragment.ProductDetailFragment
import com.example.smartwop.model.CategoryModel
import com.example.smartwop.model.MainViewModel
import com.example.smartwop.view.ProductAdapter

class CategoriesProdctsActivity2 : AppCompatActivity(), ProductAdapter.OnItemClickListener {
    lateinit var binding: ActivityCategoriesProdcts2Binding
    lateinit var item: CategoryModel
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesProdcts2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        item = intent.getSerializableExtra("pBC") as CategoryModel
        binding.recyclerPBG.layoutManager = GridLayoutManager(this,2)
        viewModel.productData.observe(this, Observer {
            binding.recyclerPBG.adapter = ProductAdapter(it,this)
        })
        binding.back.setOnClickListener {
            finish()
        }
        binding.tvTitle.text = item.title
        loadData()
    }
    private fun loadData(){
        viewModel.getProductsByCategory(item.id)
    }

    override fun onItemClick(position: Int) {
        val fragment = ProductDetailFragment.newInstance()
        fragment.show(supportFragmentManager,fragment.tag)
    }
}