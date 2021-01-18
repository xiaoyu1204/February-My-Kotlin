package com.shop.ui.main.fragment.detail

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.basemvvm.BR
import com.example.basemvvm.model.bean.main.*
import com.example.basemvvm.model.myitem.IItemClick
import com.example.basemvvm.utils.MyMmkv
import com.example.basemvvm.utils.SpUtils
import com.example.basemvvm.utils.ToastUtils
import com.github.chrisbanes.photoview.PhotoView
import com.shop.R
import com.shop.adapter.main.detail.*
import com.shop.base.BaseActivity
import com.shop.databinding.ActivityDetailInfoBinding
import com.shop.viewmodel.main.detail.DetailInfoViewModel
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.activity_detail_info.*
import java.util.ArrayList
import java.util.regex.Pattern

class DetailInfoActivity : BaseActivity<DetailInfoViewModel, ActivityDetailInfoBinding>(
    R.layout.activity_detail_info,
    DetailInfoViewModel::class.java
) {

    private val h5 = """<html>
            <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
            <head>
                <style>
                    p{
                        margin:0px;
                    }
                    img{
                        width:100%;
                        height:auto;
                    }
                </style>
            </head>
            <body>
                word
            </body>
        </html>"""

    val listUrl = ArrayList<String>()
    var id: Int? = null
    var countPos: Int? = null
    private var popupWindow: PopupWindow? = null
    var currentPos: Int? = null

    override fun initView() {
        id = intent.getIntExtra("id", 0)
    }

    override fun initVM() {
        if (id!! > 0) {
            mViewModel.getDetailInfo(id!!)
            mViewModel.getDetailInfoBottom(id!!)
        } else {
            ToastUtils.s(this, getString(R.string.tips_error_goodid))
        }
    }

    override fun initData() {
        mViewModel.detailinfo.observe(this, Observer {
            mDataBinding.setVariable(BR.detailinfo, it)
            if (!TextUtils.isEmpty(it.toString())) {
                //banner
                initBanner(it.gallery)
                //评论
                initCommect(it.comment)
                //常见问题数据
                initIssue(it.issue)
                //h5 商品详情       （h5 和 点击大图  rlv选择一个）
//                initGoodDetail(it.info.goods_desc)
                initBigImage(it)
                //点击大图  rlv
                //商品参数
                initParameter(it.attribute)
            } else {
                Log.e("TAG", "detailinfo: " + "无数据")
            }
        })
        mViewModel.detailinfobottom.observe(this, Observer {
            if (it != null && it.size > 0) {
                //底部列表数据
                initBottomInfo(it)
            } else {
                Log.e("TAG", "detailinfobottom: " + "无数据")
            }
        })
    }

    //底部列表数据
    private fun initBottomInfo(goods: List<Goods>?) {
        mRlv_category_all.layoutManager = GridLayoutManager(this, 2)
        var goodsArr = SparseArray<Int>()
        goodsArr.put(R.layout.detail_goods_item, BR.detailgoodsitem)
        mDataBinding!!.mRlvCategoryAll!!.adapter =
            DetailGoodsAdapter(this, goods!!, goodsArr, GoodsClick())
    }

    //商品参数
    private fun initParameter(attribute: List<Attribute>) {
        mRlv_category_parameter.layoutManager = LinearLayoutManager(this)
        var attributeArr = SparseArray<Int>()
        attributeArr.put(R.layout.detail_attibute_item, BR.attibuteitem)
        mDataBinding!!.mRlvCategoryParameter!!.adapter =
            DetailAttributeAdapter(this, attribute, attributeArr, ParameterClick())
    }

    //TODO 点击大图  rlv
    private fun initBigImage(goodsDesc: DetailInfoData) {
        var goods_desc = goodsDesc.info.goods_desc
        val img = "<img[\\s\\S]*?>"
        val pattern = Pattern.compile(img)
        val matcher = pattern.matcher(goods_desc)

        while (matcher.find()) {
            val word = matcher.group()
            val start = word.indexOf("\"") + 1
            val end = word.indexOf(".jpg")
            if (end > 0) { //如果是jpg格式的就截取jpg
                var url = word.substring(start, end)
                if (url != null) {
                    url = "$url.jpg"
                    listUrl.add(url)
                } else {
                    return
                }
            } else {
                val end1 = word.indexOf(".png") //如果是png格式的就截取png
                var url = word.substring(start, end1)
                if (url != null) {
                    url = "$url.png"
                    listUrl.add(url)
                } else {
                    return
                }
            }
        }

        val layoutManager3 = LinearLayoutManager(this)
        mDataBinding.mRlvDetailInfoBigimage.layoutManager = layoutManager3
        //封装xml布局界面的id和界面中需要绑定的数据对应的id
        var array = SparseArray<Int>()
        array.put(R.layout.layout_detail_bigimage_item, BR.vmDetail_bigimage)
        mDataBinding!!.mRlvDetailInfoBigimage.adapter =
            DetailImageAdapter(this, listUrl, array, BigImageClick())
    }

    //商品详情 h5
    private fun initGoodDetail(goodsDesc: String) {
        val context = h5.replace("word", goodsDesc)
//        mDataBinding.webViewCategory!!.loadDataWithBaseURL("about:blank",context,"text/html", "utf-8", null)
    }

    //常见问题
    private fun initIssue(issue: List<Issue>) {
        mRlv_category_issue.layoutManager = LinearLayoutManager(this)
        var issuseArr = SparseArray<Int>()
        issuseArr.put(R.layout.detail_issue_item, BR.issue)
        mDataBinding!!.mRlvCategoryIssue!!.adapter =
            DetailIssueAdapter(this, issue, issuseArr, IssuceClick())
    }

    //评论
    private fun initCommect(comment: Comment) {
        if (comment.data.add_time != null && comment.data.avatar != null && comment.data.content != null && comment.data.nickname != null && comment.data.pic_list != null) {
            //进行显示评论
            home_detail_info_comment_con1!!.visibility = View.VISIBLE
            //显示商品文字
            home_detail_info_comment_con2!!.visibility = View.VISIBLE
        }
    }

    //banner
    private fun initBanner(gallery: List<Gallery>) {
        home_detail_info_banner_category!!.setImages(gallery)
            .setImageLoader(object : ImageLoader() {
                override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
                    val pic = path as Gallery
                    Glide.with(context!!).load(pic.img_url).into(imageView!!)
                }
            }).start()
    }

    //商品参数监听
    inner class ParameterClick : IItemClick<Attribute> {
        override fun itemClick(data: Attribute) {
            Log.e("TAG", "ParameterClick: " + data.name)
        }
    }

    //大图监听
    inner class BigImageClick : IItemClick<String> {
        override fun itemClick(data: String) {
            //currentPos = MyMmkv.getInt("detail_image")
            currentPos = SpUtils.instance!!.getInt("detail_image")
            //Log.e("TAG", "BigImageClick:下标 " + currentPos)
            //countPos = currentPos
            //弹出pop
            initPop()
        }
    }

    //常见问题监听
    inner class IssuceClick : IItemClick<Issue> {
        override fun itemClick(data: Issue) {
            Log.e("TAG", "IssuceClick: " + data.question)
        }
    }

    //底部数据监听
    inner class GoodsClick : IItemClick<Goods> {
        override fun itemClick(data: Goods) {
            Log.e("TAG", "DetailGoodsClick: " + data.name)
        }
    }

    //TODO 大图点击内部类
    private fun initPop() {
        //PopWindow条目布局
        val popupView: View =
            LayoutInflater.from(this@DetailInfoActivity)
                .inflate(R.layout.layout_detail_bigimage_pop, null)
        //设置popu
        popupWindow =
            PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        //找到视图
        popupWindow!!.contentView = popupView
        popupWindow!!.isClippingEnabled = false

        //控制点击pw范围以外的空间关闭pw  设置Pw以外的空间可以点击
        popupWindow!!.setOutsideTouchable(true)
        //设置背景  告知pw的范围
        popupWindow!!.setBackgroundDrawable(null)
        popupWindow!!.isFocusable = true

        val mVp: ViewPager = popupView.findViewById(R.id.mVp_big_image)
        val count: TextView = popupView.findViewById(R.id.tv_big_image_count)
        val tv_return: TextView = popupView.findViewById(R.id.tv_big_image_return)

        //返回上一页面
        tv_return.setOnClickListener {
            popupWindow!!.dismiss()//关闭弹窗
        }
        //当ViewPager滑动时
        mVp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) { //停止时
                currentPos = position
                Log.e("TAG", "onPageSelected: "+position )
                updatePage(count)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
        //ViewPager切换
        mVp.currentItem = currentPos!!
        //适配器
        initList(mVp, count)

        //关闭阴影
        val attributes = window.attributes
        attributes.alpha = 0.5f
        window.attributes = attributes
        //开启阴影
        popupWindow!!.setOnDismissListener {
            val attributes = window.attributes
            attributes.alpha = 1f
            window.attributes = attributes
        }
        //在按钮的下方弹出  无偏移 第一种方式
        popupWindow!!.showAsDropDown(mDataBinding.mClDetail) //开启弹窗
    }

    //TODO 创建适配器
    private fun initList(mVp: ViewPager, count: TextView) {
        mVp!!.adapter = object : PagerAdapter() {

            override fun isViewFromObject(view: View, `object`: Any): Boolean {
                return view === `object`
            }

            override fun getCount(): Int {
                return listUrl.size
            }

            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                val photoView = PhotoView(this@DetailInfoActivity)
                //val photoView = ImageView(this@DetailInfoActivity)

                Glide.with(this@DetailInfoActivity).load(listUrl.get(position)).apply(RequestOptions.bitmapTransform(RoundedCorners(20))).into(photoView)

                container.addView(photoView) //添加进入视图
                return photoView
            }

            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                container.removeView(`object` as View)
            }
        }

        mVp.setCurrentItem(currentPos!!) //通过下标来改变集合里面的ViewPager的页面
    }

    //TODO 更换下标
    private fun updatePage(count: TextView) {
        val page: String = (currentPos!! + 1).toString()
        count.setText(page)
    }

    override fun initVariable() {

    }

}