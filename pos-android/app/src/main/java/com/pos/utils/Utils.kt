package com.pos.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.net.ConnectivityManager
import android.net.Uri
import android.provider.MediaStore
import android.provider.Settings
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.library.BuildConfig
import com.kaopiz.kprogresshud.KProgressHUD
import com.pos.R
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

object Utils {

    var progressDialog: KProgressHUD? = null

    open fun logPrint(s: String?) {
        if (BuildConfig.DEBUG) println(s)
    }

    fun hasValue(obj: Any?): Boolean {
        if (obj == null) {
            return false
        } else if (obj is EditText) {
            return obj.text.toString().trim { it <= ' ' }.length > 0
        } else if (obj is Collection<*>) {
            return obj.size > 0
        } else if (obj is String) {
            return obj.length > 0
        }
        return true
    }

    fun roundUpValue(value: Double?): Int {
        return Math.round(value!!).toInt()
    }

    fun decimalFormatter(value: Double): String? {
        var finalValue = ""
        val decimalFormat = DecimalFormat("0.00")
        finalValue = decimalFormat.format(value)
        if (finalValue == "0.00") {
            val decimalFormatThree = DecimalFormat("0.000")
            finalValue = decimalFormatThree.format(value)
        }
        if (finalValue == "0.000") {
            val decimalFormatFour = DecimalFormat("0.0000")
            finalValue = decimalFormatFour.format(value)
        }
        if (finalValue == "0.0000") {
            finalValue = "0.00"
        }
        return finalValue
    }

    fun convertDateTime(dateTime: String, oldFormat: String, newFormat: String): String? {
        return try {
            SimpleDateFormat(newFormat, Locale.getDefault())
                .format(SimpleDateFormat(oldFormat).parse(dateTime))
        } catch (e: java.lang.Exception) {
            ""
        }
    }

    fun isValidEmail(text: CharSequence?): Boolean {
        return text != null && text.toString()
            .trim { it <= ' ' }.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(text).matches()

    }

    fun isValidPassword(password: String?): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$"
        pattern = Pattern.compile(PASSWORD_PATTERN)
        matcher = pattern.matcher(password)
        return matcher.matches()
    }

    fun showMessageAlert(
        context: Context,
        message: String?,
        onDialogClick: OnDialogClick?
    ) {
        showMessageAlert(
            context,
            context.resources.getString(R.string.app_name),
            message,
            onDialogClick
        )
    }

    fun showMessageAlert(
        context: Context?,
        title: String?,
        message: String?,
        onDialogClick: OnDialogClick?
    ) {
        try {
            val messageAlert = AlertDialog.Builder(
                context!!, R.style.CustomDialog
            )
            val v: View = LayoutInflater.from(context).inflate(R.layout.dialog_validation, null)
            messageAlert.setView(v)
            val tvTitle = v.findViewById<TextView>(R.id.tvTitle)
            tvTitle.text = title
            val tvMessage = v.findViewById<TextView>(R.id.tvMessage)
            tvMessage.text = message
            val tvOk = v.findViewById<TextView>(R.id.tvOk)
            val dialog: Dialog = messageAlert.create()
            dialog.setCancelable(false)
            tvOk.setOnClickListener {
                dialog.dismiss()
                if (onDialogClick != null) onDialogClick.onClick()
            }
            dialog.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showMessageWithNoAlert(
        context: Context,
        message: String?,
        onDialogWithNoClick: OnDialogWithNoClick?
    ) {
        showMessageWithNoAlert(
            context, context.resources.getString(R.string.app_name),
            message, context.resources.getString(R.string.alert_yes),
            onDialogWithNoClick
        )
    }

    fun showMessageWithNoAlert(
        context: Context?,
        title: String?,
        message: String?,
        yesTitle: String?,
        onDialogWithNoClick: OnDialogWithNoClick?
    ) {
        try {
            val messageAlert =
                AlertDialog.Builder(context!!, R.style.CustomDialog)
            val v =
                LayoutInflater.from(context).inflate(R.layout.dialog_validation, null)
            messageAlert.setView(v)
            val tvTitle = v.findViewById<TextView>(R.id.tvTitle)
            tvTitle.text = title
            val tvMessage = v.findViewById<TextView>(R.id.tvMessage)
            tvMessage.text = message
            val tvOk = v.findViewById<TextView>(R.id.tvOk)
            tvOk.text = yesTitle
            val tvCancel = v.findViewById<TextView>(R.id.tvCancel)
            tvCancel.visibility = View.VISIBLE
            val dialog: Dialog = messageAlert.create()
            dialog.setCancelable(false)
            tvOk.setOnClickListener {
                dialog.dismiss()
                if (onDialogWithNoClick != null) onDialogWithNoClick.onYesClick()
            }
            tvCancel.setOnClickListener {
                dialog.dismiss()
                if (onDialogWithNoClick != null) onDialogWithNoClick.onNoClick()
            }
            dialog.show()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    interface OnDialogClick {
        fun onClick()
    }

    interface OnDialogWithNoClick {
        fun onYesClick()
        fun onNoClick()
    }

    fun isExpressionCorrect(string: String?, regexExpression: String?): Boolean {
        val pattern = Pattern.compile(regexExpression!!, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(string!!)
        return matcher.matches()
    }

    fun redirectSetting(activity: AppCompatActivity) {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", activity.packageName, null)
        intent.data = uri
        activity.startActivityForResult(intent, Config.CODE_REQUEST_PERMISSION_SETTING)
    }

    fun imageOptionDialog(
        mcontext: Activity,
        listener: AlertMenuListener,
        requestCodeCamera: Int,
        requestCodeGallery: Int
    ) {
        val builder = AlertDialog.Builder(mcontext)
        builder.setCancelable(false)
        builder.setTitle("Select your option:")
        builder.setItems(R.array.options,
            DialogInterface.OnClickListener { dialog, which ->
                listener.onPositiveButtonClicked(
                    which,
                    requestCodeCamera,
                    requestCodeGallery
                )
            })
        builder.setNegativeButton(R.string.cancel,
            DialogInterface.OnClickListener { dialog, which ->
                //the user clicked on Cancel
            })
        val alert = builder.create()
        alert.show()
        //  builder.show();
        val nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE)
        nbutton.setTextColor(mcontext.resources.getColor(R.color.colorBlue))
    }

    interface AlertMenuListener {
        fun onPositiveButtonClicked(index: Int, requestCodeCamera: Int, requestCodeGallery: Int)
    }

    fun getCachePath(context: Context): String? {
        return context.externalCacheDir!!.path
    }

    fun isValidMobile(phone: String): Boolean {
        return if (phone.length != 9) {
            false
        } else Patterns.PHONE.matcher(phone).matches()
    }

    fun getRealPathFromURI(uri: Uri?, context: Activity): String? {
        val projection =
            arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor = context.managedQuery(uri, projection, null, null, null)
        val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        return cursor.getString(column_index)
    }

    fun isOnline(context: Context?): Boolean {
        return if (context != null) {
            //Instance of Connectivity Manager to perform Internet Check
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            //Fetching the information available related to the active Network type
            val activeNetwork = cm.activeNetworkInfo

            // Returns false if active network is null or if not connected to internet, else returns true
            activeNetwork != null && activeNetwork.isConnectedOrConnecting
        } else {
            false
        }
    }

    fun showList(
        context: Context?,
        list: Array<String>,
        title: String?,
        onClickListener: DialogInterface.OnClickListener?
    ) {
        val messageAlert =
            AlertDialog.Builder(context!!)
        messageAlert.setTitle(title)
        messageAlert.setCancelable(true)
        messageAlert.setItems(list, onClickListener)
        messageAlert.create().show()
        messageAlert.setNegativeButton(
            R.string.cancel
        ) { dialogInterface, _ -> dialogInterface.dismiss() }
    }

    fun showProgress(context: Context?) {
        progressDialog = KProgressHUD.create(context)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setLabel("Please wait")
            .setCancellable(false)
            .setAnimationSpeed(2)
            .setDimAmount(0.5f)
        progressDialog!!.show()
    }

    fun hideProgress() {
        if (progressDialog != null) {
            if (progressDialog?.isShowing()!!) progressDialog!!.dismiss()
            progressDialog = null
        }
    }

}