package com.jackandphantom.contactapp.contactdetails

import android.content.Context
import android.media.Image
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.jackandphantom.contactapp.ContactApplication
import com.jackandphantom.contactapp.R
import com.jackandphantom.contactapp.contactlist.ContactListViewModel
import com.jackandphantom.contactapp.data.model.Contact
import java.lang.Exception
import javax.inject.Inject

class ContactDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<ContactDetailsViewModel> { viewModelFactory }

    private lateinit var contactImage: ImageView
    private lateinit var contactName: TextView
    private lateinit var contactPhone: TextView
    private lateinit var favButton: Button
    private lateinit var editButton: ImageView
    private lateinit var deleteButton: ImageView

    private var contactId: Int = 0
    private lateinit var contact: Contact

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.contact_details_fragment, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as ContactApplication).appComponent
                .contactDetailsComponent().create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactImage = view.findViewById(R.id.imageView)
        contactName = view.findViewById(R.id.contact_name)
        contactPhone = view.findViewById(R.id.textView)
        favButton = view.findViewById(R.id.button)
        editButton = view.findViewById(R.id.edit_button)
        deleteButton = view.findViewById(R.id.delete_button)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        contactId = arguments?.getInt("id")!!
        setListener()
        viewModel.fetchContact(contactId)
    }

   private fun setListener() {

       viewModel.contactData.observe(viewLifecycleOwner, {
           this.contact = it
           contactName.text = it.name
           contactPhone.text = it.phone
           try {
               contactImage.setImageURI(Uri.parse(it.image))
           }catch (e: Exception) {
               e.printStackTrace()
           }
       })

       viewModel.deleteData.observe(viewLifecycleOwner, {
           Toast.makeText(requireContext(), "Data has been deleted successfully", Toast.LENGTH_SHORT).show()
           findNavController().navigateUp()
       })

       viewModel.addToFavData.observe(viewLifecycleOwner, {
           //Added to
           Toast.makeText(requireContext(), "Added to Favorite successfully", Toast.LENGTH_SHORT).show()
           favButton.setText("Favorite Contact")
       })

       deleteButton.setOnClickListener {
           viewModel.deleteContact(contactId)
       }

       editButton.setOnClickListener {
           //Edit the button
           val bundle = bundleOf("EDIT" to contactId)
           findNavController().navigate(R.id.action_contactDetailsFragment_to_contactManipulateFragment, bundle)
       }

       favButton.setOnClickListener {
           viewModel.updateFav(contactId, true)
       }
   }

}