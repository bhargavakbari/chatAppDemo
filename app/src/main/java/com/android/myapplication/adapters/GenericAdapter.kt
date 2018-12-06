package com.android.myapplication.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.myapplication.listener.RecyclerViewClickListner
import com.android.myapplication.viewholders.BaseViewHolder

class GenericAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    val TAG = "GenericAdapter"
    var clickListener: RecyclerViewClickListner? = null
    var listSize: Int = 0
    var items: List<Any>? = null
    var context: Context? = null
    var layoutId: Int = 1
    var className: String = ""
    var item: Any? = null
    var baseViewHolder: BaseViewHolder? = null

    constructor(items: List<Any>, context: Context, layoutId: Int, className: String, clickListener: RecyclerViewClickListner? = null) {
        this.clickListener = clickListener
        this.listSize = items.size
        this.items = items
        this.context = context
        this.layoutId = layoutId
        this.className = className
    }


    constructor(items: Any, context: Context, layoutId: Int, className: String, clickListener: RecyclerViewClickListner? = null, listSize: Int) {
        this.clickListener = clickListener
        this.listSize = listSize
        this.item = items
        this.context = context
        this.layoutId = layoutId
        this.className = className
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //Crashlytics.log(layoutId.toString() + " _ " + className);
        val view = LayoutInflater.from(context).inflate(layoutId, parent, false)
        val c = Class.forName(className).getConstructor(View::class.java, Context::class.java)
        baseViewHolder = c.newInstance(view, context) as BaseViewHolder
        baseViewHolder?.initView()
        if (items != null) baseViewHolder?.initDataForRecyclerView(items!!)
        baseViewHolder?.itemClickListenerCallBack(clickListener)
        return baseViewHolder!!
    }

    override fun getItemCount(): Int {
        return listSize
    }

    fun refreshRecyclerView(items: List<Any>) {
        this.listSize = items.size
        this.items = items
        baseViewHolder?.initDataForRecyclerView(items)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val baseViewHolder = holder as BaseViewHolder
        baseViewHolder.currentItemPosition(position)
        baseViewHolder.currentItemPositionAndData(items!!, position)
    }

}