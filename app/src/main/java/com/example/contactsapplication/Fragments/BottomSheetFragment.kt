package com.example.contactsapplication.Fragments

import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.example.contactsapplication.Adapter.contactDM
import com.example.contactsapplication.R
import com.example.contactsapplication.databinding.FragmentBottomSheetBinding

import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentBottomSheetBinding? = null
    var saveContact: ((contactDM) -> Unit)? = null

    private var imageUri : Uri? = null

    // Registers a photo picker activity launcher in single-select mode.
    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        // Callback is invoked after the user selects a media item or closes the photo picker.
        if (uri != null) {
            _binding!!.NoImage.setImageURI(uri)
            imageUri = uri
            Log.d("PhotoPicker", "Selected URI: $uri")
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resetViews()

        watcher(_binding!!.UserEmailEditText, _binding!!.UserEmailID)
        watcher(_binding!!.UserNameEditText, _binding!!.UserNameID)
        watcher(_binding!!.UserPhoneEditText, _binding!!.UserPhoneID)

        _binding!!.SaveButton.setOnClickListener {
            // 1. Get the data from your EditText fields
            val name = _binding!!.UserNameEditText.text.toString().trim()
            val email = _binding!!.UserEmailEditText.text.toString().trim()
            val phone = _binding!!.UserPhoneEditText.text.toString().trim()

            // 2. Optional: Add validation (e.g., check if name is empty)
            if (name.isBlank()) {
                _binding!!.UserNameEditText.error = "Name cannot be empty"
                return@setOnClickListener // Don't proceed if validation fails
            }
            if (email.isBlank()) {
                _binding!!.UserEmailEditText.error = "Email cannot be empty"
                return@setOnClickListener // Don't proceed if validation fails
            }
            if (phone.isBlank()) {
                _binding!!.UserPhoneEditText.error = "Phone cannot be empty"
                return@setOnClickListener // Don't proceed if validation fails
            }

            // 3. Create the contactDM object
            val contactToSave = contactDM(
                userName = name, email = email, phone = phone , image = imageUri
            )

            Log.d("BottomSheetFragment", "Contact to save: $contactToSave")

            // 4. Invoke the saveContact lambda with the new contact
            saveContact?.invoke(contactToSave)

            dismiss() // Dismiss the bottom sheet
        }

        // Handle Image Click to select User Image
        _binding!!.NoImage.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }

    private fun watcher(editText: EditText, textInputLayout: TextView){

        editText.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    textInputLayout.text = p0?.trim().toString()
                    Log.d("BottomSheetWatcher", "Setting text for ${textInputLayout.id}: '${textInputLayout.text}'")
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    return
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    return
                }

            }
        )
    }

    private fun resetViews() {
        // Clear EditText fields
        _binding!!.UserNameEditText.text?.clear()
        _binding!!.UserEmailEditText.text?.clear()
        _binding!!.UserPhoneEditText.text?.clear()

        // Remove error messages
        _binding!!.UserNameEditText.error = null
        _binding!!.UserEmailEditText.error = null
        _binding!!.UserPhoneEditText.error = null

        // Reset TextViews that preview EditText content (if they should be blank initially)
        _binding!!.UserNameID.text = getString(R.string.user_name)
        _binding!!.UserEmailID.text = getString(R.string.example_email_com)
        _binding!!.UserPhoneID.text = getString(R.string._2000000000)

        // Reset ImageView to the default image
        _binding!!.NoImage.setImageResource(R.drawable.no_image) // Set your default placeholder
        imageUri = null // Reset the stored image URI

        Log.d("BottomSheetFragment", "Views have been reset")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}