package com.jackandphantom.contactapp.contactlist

import android.net.Uri
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jackandphantom.contactapp.R
import com.jackandphantom.contactapp.data.model.Contact
import java.io.File
import java.lang.Exception

class ContactDataAdapter(private val list: List<Contact>): RecyclerView.Adapter<ContactDataAdapter.ViewHolder>() {

    interface OnDataSelectListener {
        fun dataSelect(contact: Contact)
    }

    lateinit var onDataSelectListener: OnDataSelectListener

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private var image: ImageView = itemView.findViewById(R.id.contact_image)
        private var name: TextView = itemView.findViewById(R.id.contact_name)
        private var phone: TextView = itemView.findViewById(R.id.contact_phone)

        fun bind(contact: Contact) {
            try {
                image.setImageURI(Uri.parse(contact.image))
                name.text = contact.name
                phone.text = contact.phone
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.contact_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onDataSelectListener.dataSelect(list[position])
        }
    }

    override fun getItemCount(): Int {
      return  list.size
    }

    fun setOnSelectListener(onDataSelectListener: OnDataSelectListener) {
        this.onDataSelectListener = onDataSelectListener
    }
}