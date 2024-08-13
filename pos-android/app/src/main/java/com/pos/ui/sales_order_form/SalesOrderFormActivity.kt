package com.pos.ui.sales_order_form

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.pos.BR
import com.pos.R
import com.pos.base.BaseActivity
import com.pos.data.local.AppPreferencesHelper
import com.pos.databinding.ActivitySalesOrderFormBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


@AndroidEntryPoint
class SalesOrderFormActivity :
    BaseActivity<ActivitySalesOrderFormBinding, SalesOrderFormViewModel>(),
    SalesOrderFormNavigator, View.OnClickListener {

    @Inject
    lateinit var helper: AppPreferencesHelper

    private var viewModel: SalesOrderFormViewModel? = null

    lateinit var binding: ActivitySalesOrderFormBinding

    private var slabTypeList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewDataBinding()
        viewModel?.setNavigator(this)
        initializeDropDown()
        binding.etPostingDate.setOnClickListener {
            datePickerFormat()
        }
        binding.etDocumentDate.setOnClickListener(this)
    }

    private fun datePickerFormat() {
        DatePickerDialog(
            this, datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    val myCalendar = Calendar.getInstance()

    val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
        myCalendar.set(Calendar.YEAR, year)
        myCalendar.set(Calendar.MONTH, month)
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        updateCalendarFormat(myCalendar)
    }

    private fun updateCalendarFormat(myCalendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        binding.etPostingDate.setText(sdf.format(myCalendar.time))
        binding.etDocumentDate.setText(sdf.format(myCalendar.time))
        binding.etDeliveryDate.setText(sdf.format(myCalendar.time))
    }

    private fun initializeDropDown() {
        val slabList = ArrayAdapter(this, R.layout.list_item_slab, slabTypeList)
        binding.etSeries.setAdapter(slabList)
        binding.etShipTo.setAdapter(slabList)
        binding.etContactPerson.setAdapter(slabList)
        binding.etBusinessPartner.setAdapter(slabList)
        binding.etCurrency.setAdapter(slabList)
        binding.etSalesEmployee.setAdapter(slabList)
        binding.etBillTo.setAdapter(slabList)
        binding.etShippingType.setAdapter(slabList)
        binding.etPaymentTerms.setAdapter(slabList)
        binding.etPaymentMethods.setAdapter(slabList)
        binding.etBpProject.setAdapter(slabList)
        slabTypeList.add("POS")
        slabTypeList.add("Android")
        slabTypeList.add(" POS Development")
    }

    override fun getViewModel(): SalesOrderFormViewModel {
        viewModel = ViewModelProvider(this).get(SalesOrderFormViewModel::class.java)
        return viewModel as SalesOrderFormViewModel
    }

    override fun getLayoutId(): Int = R.layout.activity_sales_order_form

    override fun getBindingVariable(): Int = BR._all

    override fun onCancelClick() {
        super.onBackPressed()
    }

    override fun onCreateOrder() {
        TODO("Not yet implemented")
    }

    override fun onClick(p0: View?) {
        datePickerFormat()
    }

}