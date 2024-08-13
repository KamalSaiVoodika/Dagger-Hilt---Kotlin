package com.pos.base

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.pos.R
import com.pos.utils.Config
import com.pos.utils.Utils
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import permissions.dispatcher.*
import java.io.File

@RuntimePermissions
abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<*>> : AppCompatActivity(),
    BaseFragment.Callback, Utils.AlertMenuListener {

    lateinit var mViewDataBinding: T
    var mViewModel: V? = null

    abstract fun getViewModel(): V

    @LayoutRes
    abstract fun getLayoutId(): Int

    open fun getViewDataBinding(): T {
        return mViewDataBinding
    }

    abstract fun getBindingVariable(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performViewBainding()
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }

    open fun showMessageAlert(message: String?) {
        Utils.showMessageAlert(this, message, null)
    }

    open fun showMessageAlert(title: String?, message: String?) {
        Utils.showMessageAlert(this, title, message, null)
    }

    open fun showMessageAlert(
        title: String?,
        message: String?,
        onDialogClick: Utils.OnDialogClick?
    ) {
        Utils.showMessageAlert(this, title, message, onDialogClick)
    }

    open fun showMessageAlert(message: String?, onDialogClick: Utils.OnDialogClick?) {
        Utils.showMessageAlert(this, message, onDialogClick)
    }

    open fun showMessageWithNoAlert(
        message: String?,
        onDialogWithNoClick: Utils.OnDialogWithNoClick?
    ) {
        Utils.showMessageWithNoAlert(this, message, onDialogWithNoClick)
    }

    open fun showMessageWithNoAlert(
        title: String?,
        message: String?,
        yesText: String?,
        onDialogWithNoClick: Utils.OnDialogWithNoClick?
    ) {
        Utils.showMessageWithNoAlert(this, title, message, yesText, onDialogWithNoClick)
    }

    open fun hideKeyboard() {
        val imm =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun performViewBainding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mViewDataBinding.lifecycleOwner = this
        mViewModel = if (mViewModel == null) getViewModel() else mViewModel
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding.executePendingBindings()
    }

    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String?) {

    }

    @OnShowRationale(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    fun OnShowRationale(request: PermissionRequest) {
        AlertDialog.Builder(this, R.style.AlertDialogTheme)
            .setMessage(R.string.permission_msg)
            .setPositiveButton(R.string.button_allow) { dialog, button -> request.proceed() }
            .setNegativeButton(R.string.button_deny) { dialog, button -> request.cancel() }
            .show()
    }


    @OnPermissionDenied(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    fun OnPermissionDenied() {
    }

    @OnNeverAskAgain(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    fun OnNeverAsk() {
        Settingdialog()
    }

    fun Settingdialog() {
        AlertDialog.Builder(this, R.style.AlertDialogTheme)
            .setMessage(R.string.permission_msg)
            .setCancelable(true)
            .setPositiveButton(R.string.action_settings) { dialog, button ->
                Utils.redirectSetting(
                    this@BaseActivity
                )
            }
            .show()
    }

    override fun onPositiveButtonClicked(
        index: Int,
        requestCodeCamera: Int,
        requestCodeGallery: Int
    ) {
        if (index == 0) {
            openCamera(requestCodeCamera)
        } else if (index == 1) {
            galleryClick(requestCodeGallery)
        }
    }

    open fun OpenCamera(requestCodeCamera: Int, requestCodeGallery: Int) {

        cameraPermissionWithPermissionCheck(
            requestCodeCamera,
            requestCodeGallery
        )

    }

    fun openCamera(requestCode: Int) {
        try {
            val selectedFile: File = File(Utils.getCachePath(this), "pictures")
            if (selectedFile.exists()) {
                selectedFile.delete()
            }
            selectedFile.mkdirs()
            val file = File.createTempFile("temp", ".jpg", selectedFile)
            Config.CAMERA_PATH = file.absolutePath
            val apkURI = FileProvider.getUriForFile(
                applicationContext,
                applicationContext.packageName.toString() + ".provider",
                file
            )
            val intentPicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intentPicture.putExtra(MediaStore.EXTRA_OUTPUT, apkURI)
            intentPicture.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivityForResult(intentPicture, requestCode)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @NeedsPermission(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    fun cameraPermission(Camera: Int, gallery: Int) {
        Utils.imageOptionDialog(this, this, Camera, gallery)
    }

    fun galleryClick(requestCode: Int) {
        val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(i, requestCode)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    @OnNeverAskAgain(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    open fun OnNeverAskLocation() {
        Settingdialog()
    }

    @OnPermissionDenied(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    open fun OnPermissionDeniedLocation() {
        AlertDialog.Builder(this, R.style.AlertDialogTheme)
            .setMessage(R.string.permission_location_must)
            .setCancelable(false)
            .setPositiveButton(
                R.string.button_allow
            ) { dialog, which ->
                dialog.dismiss()
                LocationPermissionWithPermissionCheck()
            }
            .show()
    }

    open fun CheckLocationPermission() {
        LocationPermissionWithPermissionCheck()
    }

    fun checkLocationPermission(): Boolean {
        val permissionCheck = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        if (permissionCheck == -1) {
            CheckLocationPermission()
            return false
        }
        return true
    }

    fun showKeyboard(edtText: EditText) {
        edtText.requestFocus()
        edtText.postDelayed(Runnable {
            val keyboard =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            keyboard.showSoftInput(edtText, 0)
        }
            , 200)
    }

    open fun showProgress() {
        Utils.showProgress(this)
    }

    open fun hideProgress() {
        Utils.hideProgress()
    }

    open fun checkInternet(): Boolean {
        return checkInternet(true)
    }

    open fun checkInternet(isShowProgress: Boolean): Boolean {
        return if (Utils.isOnline(this)) {
            if (isShowProgress) showProgress()
            true
        } else {
            if (isShowProgress) showMessageAlert(resources.getString(R.string.msg_no_internet))
            false
        }
    }

    @NeedsPermission(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    open fun LocationPermission() {
    }

    @OnShowRationale(
        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
    )
    fun OnShowRationaleLocation(request: PermissionRequest) {
        AlertDialog.Builder(this, R.style.AlertDialogTheme)
            .setMessage(R.string.permission_msg)
            .setPositiveButton(R.string.button_allow) { _, _ -> request.proceed() }
            .setNegativeButton(R.string.button_deny) { _, _ -> request.cancel() }
            .show()
    }

}

