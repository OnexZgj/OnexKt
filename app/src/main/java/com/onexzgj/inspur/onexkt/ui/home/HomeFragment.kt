package com.onexzgj.inspur.onexkt.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.onexzgj.inspur.onexkt.R
import com.onexzgj.inspur.onexkt.model.Article
import com.onexzgj.inspur.onexkt.model.Banner
import com.onexzgj.inspur.onexkt.mvp.BaseMvpFragment
import com.onexzgj.inspur.onexkt.ui.detail.WebViewActivity
import com.onexzgj.inspur.onexkt.utils.*
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.youth.banner.listener.OnBannerListener
import com.youth.banner.loader.ImageLoader


/**
 * des：HomeFragment的编写
 * author：onexzgj
 * time：2020-03-04
 */

class HomeFragment : BaseMvpFragment<HomeContract.View, HomePresenter>(), HomeContract.View {

    private lateinit var mAdapter: HomeRecyclerAdapter
    private lateinit var headerView: View
    private lateinit var banner: com.youth.banner.Banner
    private var rvHome: RecyclerView? = null
    private var smartRefresh: SmartRefreshLayout? = null

    /**
     * 所有的数据
     */
    private var dataList: List<Article> = ArrayList()

    /**
     * 文章显示的页数
     */
    private var mPage: Int = 0


    override fun createPresent(): HomePresenter {
        return HomePresenter()
    }

    override fun initView(rootView: View?, savedInstanceState: Bundle?) {
        rvHome = rootView?.findViewById(R.id.rv_home)
        smartRefresh = rootView?.findViewById(R.id.srl_home)
        smartRefresh?.setEnableRefresh(true)
        val itemDecoration = DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL)

        rvHome?.addItemDecoration(itemDecoration)

        headerView = layoutInflater.inflate(R.layout.layout_home_header, null, false)
        banner = headerView.findViewById(R.id.banner)
//        banner.setBannerAnimation(Transformer.ZoomIn)
        banner.setBannerAnimation(CustomTransformer::class.java)
    }


    class CustomTransformer : ViewPager.PageTransformer {
        val MIN_SCALE: Float = 0.8f

        override fun transformPage(page: View, position: Float) {
            if (position < -1) {
                page.scaleY = MIN_SCALE
            } else if (position <= 1) {
                //
                var scale = Math.max(MIN_SCALE, 1 - Math.abs(position));
                page.scaleY = scale

            } else {
                page.scaleY = MIN_SCALE
            }
        }

    }


    override fun initData() {
        super.initData()
        showLoading()
        rvHome?.layoutManager = LinearLayoutManager(
            mContext, LinearLayoutManager.VERTICAL, false
        )

        mAdapter = HomeRecyclerAdapter()
        mAdapter.addHeaderView(headerView)
        rvHome?.adapter = mAdapter

        mAdapter.onItemClickListener =
            BaseQuickAdapter.OnItemClickListener { adapter: BaseQuickAdapter<Any, BaseViewHolder>, itemView: View, position: Int ->
                val bundle = Bundle()
                val bean = dataList[position]
                bundle.putString(URL, bean.link)
                bundle.putInt(ID, bean.id)
                bundle.putString(AUTHOR, bean.author)
                bundle.putString(LINK, bean.link)
                bundle.putString(TITLE, bean.title)

                val intent = Intent(activity, WebViewActivity::class.java)
                intent.putExtras(bundle)
                activity?.startActivity(intent)

            }

        mPresent.getBanner()
        mPresent.getArticle(mPage)

        smartRefresh?.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                mPresent.getArticle(mPage)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                mPage = 0
                mPresent.getBanner()
                mPresent.getArticle(mPage)
            }
        })

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }


    override fun onBanner(list: List<Banner>?) {
        val urlList = mutableListOf<String>()
        if (list != null) {
            for (banner in list) {
                urlList.add(banner.imagePath)
            }
        }
        banner.setImageLoader(object : ImageLoader() {
            override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
                val roundedCorners = RoundedCorners(20)
                val bitmapTransform = RequestOptions.bitmapTransform(roundedCorners)


                val params: LinearLayout.LayoutParams? =
                    imageView?.height?.let { LinearLayout.LayoutParams(imageView?.width, it) }

                params?.leftMargin = 10
                params?.rightMargin = 10

                imageView?.scaleType = ImageView.ScaleType.FIT_XY
                Glide.with(context!!).load(path).apply(bitmapTransform).into(imageView!!)
            }
        })




        banner.setImages(urlList)
            .isAutoPlay(true)
            .start()

        banner.setOnBannerListener(object : OnBannerListener {
            override fun OnBannerClick(position: Int) {

                Toast.makeText(mContext, list?.get(position)?.url, Toast.LENGTH_SHORT).show()
                if (list != null) {
                    val bundle = Bundle()
                    val banner = list[position]
                    bundle.putString(URL, banner.url)
                    bundle.putInt(ID, banner.id)

                    val intent = Intent(activity!!, WebViewActivity::class.java)
                    intent.putExtras(bundle)
                    activity?.startActivity(intent)
                }
            }
        })
    }

    override fun onArticles(page: Int, list: List<Article>?) {

        smartRefresh?.finishRefresh()
        smartRefresh?.finishLoadMore()
        mPage = page + 1
        if (list != null) {
            dataList = list
        }
        mAdapter.setNewData(dataList)
        dismissLoading()

    }


    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }


}