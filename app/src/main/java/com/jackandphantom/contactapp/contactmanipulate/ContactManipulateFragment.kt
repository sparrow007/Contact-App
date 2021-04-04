package com.jackandphantom.contactapp.contactmanipulate

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.jackandphantom.contactapp.ContactApplication
import com.jackandphantom.contactapp.R
import com.jackandphantom.contactapp.data.model.Contact
import kotlinx.android.synthetic.main.contact_manipulate_fragment.*
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject


class ContactManipulateFragment : Fragment() {

    private val PICK_IMAGE_REQUEST: Int = 1

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<ContactManipulateViewModel> { viewModelFactory }

    private lateinit var contactImage: ImageView
    private lateinit var contactName: EditText
    private lateinit var contactPhone: EditText
    private lateinit var createButton: Button

    private var image:String = ""
    private var contactId:Int = -1
    private var contact: Contact?= null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as ContactApplication).appComponent
            .contactManipulateComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.contact_manipulate_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactImage = view.findViewById(R.id.imageView)
        contactName = view.findViewById(R.id.contact_name)
        contactPhone = view.findViewById(R.id.textView)
        createButton = view.findViewById(R.id.button)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            contactId = it.getInt("EDIT")
        }
        if (contactId != -1) {
            viewModel.fetchContact(contactId)
        }

        setListener()
    }

    private fun setListener() {
        imageView.setOnClickListener {
            chooseImage()
        }

        createButton.setOnClickListener {
            val name = contactName.text.toString()
            val phone = contactPhone.text.toString()
            if (contactId != -1) {
                contact?.let {
                    if (image.isEmpty()) image = it.image
                }
                viewModel.updateContact(contactId, name, phone, image)

            }
            else
            viewModel.insertContact(name, phone, image)
        }

        viewModel.dataInserted.observe(viewLifecycleOwner, {
            if (it) {
                Toast.makeText(requireContext(), "Successfully added!!", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
        })


        viewModel.contactData.observe(viewLifecycleOwner, {
            contact = it
            contactName.setText(it.name)
            contactPhone.setText(it.phone)
            createButton.setText("Update Contact")
            try {
                contactImage.setImageURI(Uri.parse(it.image))
            }catch (e: Exception) {
                e.printStackTrace()
            }
        })
    }

    private fun chooseImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_OPEN_DOCUMENT
        startActivityForResult(
            Intent.createChooser(intent, "Select Picture"),
            PICK_IMAGE_REQUEST
        ) //activity result method call
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === PICK_IMAGE_REQUEST && resultCode === RESULT_OK && data != null &&data.data != null) {
            val uri: Uri = data.data!!
//            Uri.fromFile(File())
           image = uri.toString()
            Glide.with(requireContext()).load(uri).into(contactImage)

        }
    }


}