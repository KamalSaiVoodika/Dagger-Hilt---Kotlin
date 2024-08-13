package com.pos.ui.contacts

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.concept.stickyheader.extensions.setDivider
import com.pos.R
import com.pos.databinding.ContactsBinding
import com.pos.databinding.ViewListItemBinding
import com.pos.ui.add_customer.AddCustomerActivity
import com.pos.ui.contacts.Utils.JsonUtils
import com.pos.ui.contacts.Utils.StickyHeaderDecoration
import com.pos.ui.contacts.data.Names

class ContactsActivity : AppCompatActivity() {

    private lateinit var binding: ContactsBinding
    private val bookAdapter = BookAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val names: List<Names> = JsonUtils.getItems(this)
        val groupedBooks: Map<Char, List<Names>> =
            names.groupBy { name -> name.name.first().toUpperCase() }
        bookAdapter.bookData = groupedBooks.toSortedMap()

        binding.bookList.setDivider(R.drawable.list_divider)
        binding.bookList.addItemDecoration(
            StickyHeaderDecoration(bookAdapter, binding.root)
        )
        binding.bookList.layoutManager = LinearLayoutManager(this)
        binding.bookList.adapter = bookAdapter

        binding.tvAddCustomer.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, AddCustomerActivity::class.java))
        })
    }

    class BookAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private var bookHeaders: List<Char> = listOf()

        var bookData: Map<Char, List<Names>> = emptyMap()
            set(value) {
                field = value
                bookHeaders = bookData.keys.toList()
                notifyDataSetChanged()
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
            val viewBinding: ViewListItemBinding =
                ViewListItemBinding.inflate(layoutInflater, parent, false)
            return BookViewHolder(viewBinding)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (position >= 0 && position < bookHeaders.size) {
                (holder as BookViewHolder).bind(bookHeaders[position])
            }
        }

        override fun getItemCount() = bookHeaders.size

        fun getHeaderForCurrentPosition(position: Int) = if (position in bookHeaders.indices) {
            bookHeaders[position]
        } else {
            ""
        }

        inner class BookViewHolder(private val viewBinding: ViewListItemBinding) :
            RecyclerView.ViewHolder(viewBinding.root) {

            fun bind(header: Char) {
                viewBinding.tvHeader.text = "$header"
                bookData[header]?.let { books ->
                    viewBinding.bookDetailsView.books = books
                }
            }
        }
    }
}