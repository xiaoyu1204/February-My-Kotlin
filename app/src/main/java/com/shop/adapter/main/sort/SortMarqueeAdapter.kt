package com.shop.adapter.main.sort

import android.content.Context
import android.widget.TextView
import com.shop.R
import com.xj.marqueeview.base.CommonAdapter
import com.xj.marqueeview.base.ViewHolder

class SortMarqueeAdapter(context: Context, datas:List<String>)
    :CommonAdapter<String?>(context, R.layout.sort_marquee_item,datas){

    override fun convert(viewHolder: ViewHolder?, item: String?, position: Int) {
        val view:TextView = viewHolder!!.getView(R.id.tv_simple_text)
        view.text = item
    }

}