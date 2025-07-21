package com.example.contactsapplication.Fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.contactsapplication.Adapter.contactDM
import com.example.contactsapplication.databinding.FragmentBottomSheetBinding


class BottomSheetFragment : DialogFragment() {
    private var _binding: FragmentBottomSheetBinding? = null
    var saveContact: ((contactDM) -> Unit)? = null

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
                // Optionally show an error to the user, e.g., on _binding!!.UserNameEditText
                _binding!!.UserNameEditText.error = "Name cannot be empty"
                return@setOnClickListener // Don't proceed if validation fails
            }
            // Add more validation for email and phone if needed

            // 3. Create the contactDM object
            val contactToSave = contactDM(
                userName = name, email = email, phone = phone,
                image = null
            ) // Adjust field names based on your contactDM class

            // 4. Invoke the saveContact lambda with the new contact
            saveContact?.invoke(contactToSave) //  Use safe call ?. and invoke()

            dismiss() // Dismiss the bottom sheet
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
}