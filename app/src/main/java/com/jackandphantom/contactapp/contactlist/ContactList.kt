package com.jackandphantom.contactapp.contactlist

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.datepicker.OnSelectionChangedListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jackandphantom.contactapp.ContactApplication
import com.jackandphantom.contactapp.R
import com.jackandphantom.contactapp.data.model.Contact
import javax.inject.Inject

class ContactList : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<ContactListViewModel> { viewModelFactory }

    private lateinit var createButton: FloatingActionButton
    private lateinit var contactRecycler: RecyclerView
    private lateinit var favRecycler: RecyclerView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as ContactApplication).appComponent
            .contactListComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.contact_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createButton = view.findViewById(R.id.add_contact_button)
        contactRecycler = view.findViewById(R.id.contact_recycler)
        favRecycler = view.findViewById(R.id.fav_recycler)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setListener()
        viewModel.getAllList()
        viewModel.getAllFavList()
    }

    private fun setListener() {
        viewModel.dataList.observe(viewLifecycleOwner, {
            val adapter = ContactDataAdapter(it)
            contactRecycler.layoutManager = LinearLayoutManager(requireContext())
            contactRecycler.adapter = adapter
            adapter.setOnSelectListener(object : ContactDataAdapter.OnDataSelectListener {
                override fun dataSelect(contact: Contact) {
                    val id = contact.id
                    val bundle = bundleOf("id" to id)
                    findNavController().navigate(R.id.action_contactList_to_contactDetailsFragment, bundle)
                }
            })
        })

        createButton.setOnClickListener {
            findNavController().navigate(R.id.action_contactList_to_contactManipulateFragment)
        }

        viewModel.dataFavList.observe(viewLifecycleOwner, {

            val adapter = ContactDataAdapter(it)
            favRecycler.layoutManager = LinearLayoutManager(requireContext())
            favRecycler.adapter = adapter
            adapter.setOnSelectListener(object : ContactDataAdapter.OnDataSelectListener {
                override fun dataSelect(contact: Contact) {
                    val id = contact.id
                    val bundle = bundleOf("id" to id)
                    findNavController().navigate(R.id.action_contactList_to_contactDetailsFragment, bundle)
                }
            })

        })
    }

}