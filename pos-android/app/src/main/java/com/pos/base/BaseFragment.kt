package com.pos.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.pos.R
import com.pos.utils.Utils

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel<*>> : Fragment() {

    lateinit var mActivity: BaseActivity<*, *>
    lateinit var mViewDataBinding: T
    lateinit var mViewModel: V
    lateinit var mRootView: View

    abstract fun getViewModel(): V
    abstract fun getBindingVariable(): Int

    @LayoutRes
    abstract fun getLayoutId(): Int

    open fun getViewDataBinding(): T {
        return mViewDataBinding
    }

    open fun getBaseActivity(): Context {
        return mActivity
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            this.mActivity = context
            context.onFragmentAttached()
        }
    }

    interface Callback {
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String?)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return mViewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding.lifecycleOwner = this
        mViewDataBinding.executePendingBindings()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = getViewModel()
        setHasOptionsMenu(false)
    }

    open fun checkInternet(): Boolean {
        return checkInternet(true)
    }

    open fun showProgress() {
        Utils.showProgress(activity)
    }

    open fun hideProgress() {
        Utils.hideProgress()
    }

    open fun checkInternet(isShowProgress: Boolean): Boolean {
        return if (Utils.isOnline(activity)) {
            if (isShowProgress) showProgress()
            true
        } else {
            if (isShowProgress) showMessageAlert(resources.getString(R.string.msg_no_internet))
            false
        }
    }

    open fun showMessageAlert(message: String?) {
        Utils.showMessageAlert(getBaseActivity()!!, message, null)
    }

    open fun showMessageAlert(title: String?, message: String?) {
        Utils.showMessageAlert(getBaseActivity()!!, title, message, null)
    }

    open fun showMessageAlert(
        title: String?,
        message: String?,
        onDialogClick: Utils.OnDialogClick?
    ) {
        Utils.showMessageAlert(getBaseActivity()!!, title, message, onDialogClick)
    }

    open fun showMessageAlert(message: String?, onDialogClick: Utils.OnDialogClick?) {
        Utils.showMessageAlert(getBaseActivity()!!, message, onDialogClick)
    }

    open fun showMessageWithNoAlert(
        message: String?,
        onDialogWithNoClick: Utils.OnDialogWithNoClick?
    ) {
        Utils.showMessageWithNoAlert(getBaseActivity()!!, message, onDialogWithNoClick)
    }

    open fun showMessageWithNoAlert(
        title: String?,
        message: String?,
        yesText: String?,
        onDialogWithNoClick: Utils.OnDialogWithNoClick?
    ) {
        Utils.showMessageWithNoAlert(
            getBaseActivity()!!,
            title,
            message,
            yesText,
            onDialogWithNoClick
        )
    }

    override fun onDetach() {
        super.onDetach()
//        mActivity = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}